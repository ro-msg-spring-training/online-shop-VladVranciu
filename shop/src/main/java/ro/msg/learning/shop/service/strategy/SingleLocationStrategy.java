package ro.msg.learning.shop.service.strategy;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.exception.OrderCannotBeCompletedException;
import ro.msg.learning.shop.exception.ProductNotFoundException;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.model.DTO.OrderInputObject;
import ro.msg.learning.shop.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
public class SingleLocationStrategy implements Strategy {

    private ProductRepository productRepository;
    private LocationRepository locationRepository;
    private CustomerRepository customerRepository;
    private StockRepository stockRepository;
    private OrderRepository orderRepository;


    public Order compute(OrderInputObject orderInputObject)throws OrderCannotBeCompletedException{
        Order order = new Order();
        List<OrderToCompute> orderToComputes=computeSingleLocation(orderInputObject);
        Location shippedFrom = orderToComputes.get(0).getLocation();
        order.setShippedFrom(shippedFrom);
        order.setCreatedAt(orderInputObject.getOrderTimeStamp());
        Customer customer = customerRepository.findById(1).get();
        order.setCustomer(customer);
        order.setShippedTo(orderInputObject.getAddress());
        List<OrderDetail> orderDetails = new ArrayList<>();
        List<Product> products=new ArrayList<>();
        List<Integer> quantities = new ArrayList<>();
        orderToComputes.stream().peek(o->{
            products.add(o.getProduct());
            quantities.add(o.getQuantity());
        }).collect(Collectors.toList());
        List<Product> finalProducts = products;
        products.stream().peek(p -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(p);
            orderDetail.setQuantity(quantities.get(finalProducts.indexOf(p)));

            Stock stock = stockRepository.findStockByLocationAndProduct(shippedFrom.getId(), p.getId());
            stock.setQuantity(stock.getQuantity() - quantities.get(finalProducts.indexOf(p)));
            stockRepository.save(stock);
            orderDetails.add(orderDetail);
        }).collect(Collectors.toList());
        order.setOrderDetails(orderDetails);
        orderRepository.save(order);
        return order;
    }

    public List<OrderToCompute> computeSingleLocation(OrderInputObject orderInputObject) throws OrderCannotBeCompletedException {
        List<OrderToCompute> orderToComputes = new ArrayList<>();
        List<Location> allLocations = new ArrayList<>();
        orderInputObject.getProducts().stream().peek(p -> {
            if(!productRepository.findById(p.getProductId()).isPresent())
                throw new ProductNotFoundException(p.getProductId());
            List<Location> aux = locationRepository.findSingleLocation(p.getQuantity(), productRepository.findById(p.getProductId()).get());
            if (aux == null || aux.isEmpty())
                allLocations.addAll(new ArrayList<>());
            else
                allLocations.addAll(locationRepository.findSingleLocation(p.getQuantity(), productRepository.findById(p.getProductId()).get()));
        }).collect(Collectors.toList());
        Map<Location, Long> counts = allLocations.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        counts.entrySet().stream().filter(e -> e.getValue().equals(orderInputObject.getProducts().size()))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
        if (counts.isEmpty())
            throw new OrderCannotBeCompletedException();
        Map.Entry<Location, Long> entry = counts.entrySet().iterator().next();
        if (entry.getKey() != null) {
            orderInputObject.getProducts().stream().peek(p-> orderToComputes.add(new OrderToCompute(entry.getKey(), productRepository.findById(p.getProductId()).get(), p.getQuantity()))).collect(Collectors.toList());
            return orderToComputes;
        } else
            throw new OrderCannotBeCompletedException();
    }


}
