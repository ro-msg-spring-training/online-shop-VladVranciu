package ro.msg.learning.shop.service.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.exception.OrderCannotBeCompletedException;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.model.DTO.OrderInputObject;
import ro.msg.learning.shop.repository.CustomerRepository;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.ArrayList;
import java.util.List;


public class MostAbundantStrategy implements Strategy {
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    private static final int idCustomer = 1;

    @Override
    public List<Order> compute(OrderInputObject orderInputObject) throws OrderCannotBeCompletedException {
        List<Order> orders = new ArrayList<>();
        List<OrderToCompute> orderToComputes = getOrderToCompute(orderInputObject);
        List<Location> locations = new ArrayList<>();
        Address address = orderInputObject.getAddress();
        for (OrderToCompute orderToCompute : orderToComputes)
            locations.add(orderToCompute.getLocation());
        for (OrderToCompute orderToCompute : orderToComputes) {
            if (!locations.isEmpty()) {
                Order order = new Order();
                order.setShippedFrom(orderToCompute.getLocation());
                order.setCreatedAt(orderInputObject.getOrderTimeStamp());
                order.setCustomer(customerRepository.findById(idCustomer).get());
                order.setShippedTo(address);
                List<OrderDetail> orderDetails = new ArrayList<>();
                List<Product> products = new ArrayList<>();
                List<Integer> quantities = new ArrayList<>();
                Location location = orderToCompute.getLocation();
                for (Location l : locations) {
                    if (l.equals(location)) {
                        for (OrderToCompute toCompute : orderToComputes) {
                            if (toCompute.getLocation().equals(location)) {
                                if (!products.contains(toCompute.getProduct())) {
                                    products.add(toCompute.getProduct());
                                    quantities.add(toCompute.getQuantity());
                                }
                            }
                        }
                    }
                }
                List<Location> aux = new ArrayList<>();
                for (Location l : locations) {
                    if (!l.equals(location))
                        aux.add(l);
                }
                locations = aux;
                for (Product p : products) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setProduct(p);
                    orderDetail.setQuantity(quantities.get(products.indexOf(p)));
                    orderDetail.setOrder(order);
                    for (Stock stock : stockRepository.findAll()) {
                        if (stock.getLocation().equals(location)) {
                            if (stock.getProduct().equals(p)) {
                                stock.setQuantity(stock.getQuantity() - orderDetail.getQuantity());
                                stockRepository.save(stock);
                            }
                        }
                    }
                    orderDetails.add(orderDetail);
                }
                order.setOrderDetails(orderDetails);
                orderRepository.save(order);
                orders.add(order);

            }
        }
        if(orders.isEmpty())
            throw new OrderCannotBeCompletedException();
        else
            return orders;
    }

    public List<OrderToCompute> getOrderToCompute(OrderInputObject orderInputObject) throws OrderCannotBeCompletedException {
        List<OrderToCompute> orderToComputes = new ArrayList<>();
        for (ProductInputObject p : orderInputObject.getProducts()) {
            Integer maxq = -1;
            Location location = null;
            for (Stock s : stockRepository.findAll()) {
                if (s.getProduct().getId().equals(p.getProductId()))
                    if (s.getQuantity() > maxq) {
                        maxq = s.getQuantity();
                        location = s.getLocation();
                    }
            }
            orderToComputes.add(new OrderToCompute(location, productRepository.findById(p.getProductId()).get(), p.getQuantity()));
        }
        if (orderToComputes.size() == orderInputObject.getProducts().size())
            return orderToComputes;
        else
            throw new OrderCannotBeCompletedException();
    }
}
