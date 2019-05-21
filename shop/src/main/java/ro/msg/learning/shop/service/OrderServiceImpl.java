package ro.msg.learning.shop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.config.StrategyConfiguration;
import ro.msg.learning.shop.exception.OrderCannotBeCompletedException;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.model.DTO.OrderInputObject;
import ro.msg.learning.shop.repository.*;

import ro.msg.learning.shop.service.strategy.Strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private Strategy strategy;

    @Autowired
    private StrategyConfiguration strategyConfiguration;

    private static final int idCustomer = 1;


    @Override
    public Order createOrder(OrderInputObject orderInputObject) throws OrderCannotBeCompletedException {
        List<OrderToCompute> list = strategy.compute(orderInputObject);
        Order order = new Order();
        switch (strategy.getStrategy()) {
            case SINGLELOCATION:
                order = computeSingleLocation(list, orderInputObject);
                break;
            case MOSTABUNDANT:
                order = computeMostAbundant(list, orderInputObject);
                break;

        }
        return order;
    }

    private Order computeSingleLocation(List<OrderToCompute> orderToComputes, OrderInputObject orderInputObject) {
        Order order = new Order();
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

    private Order computeMostAbundant(List<OrderToCompute> orderToComputes, OrderInputObject orderInputObject) {
        Order order = new Order();
        order.setShippedTo(orderInputObject.getAddress());
        order.setCreatedAt(orderInputObject.getOrderTimeStamp());
        order.setShippedFrom(orderToComputes.get(0).getLocation());
        order.setCustomer(customerRepository.findById(1).get());
        List<OrderDetail> orderDetails=new ArrayList<>();
        List<Location> locations = new ArrayList<>();
        orderToComputes.stream().peek(o ->
                locations.add(o.getLocation())
        ).collect(Collectors.toList());
        orderToComputes.stream().peek(o->{
            if(!locations.isEmpty()){
                Location location=o.getLocation();
                List<Product> products=new ArrayList<>();
                List<Integer> quantities=new ArrayList<>();
                locations.stream().peek(l->{
                    if(l.equals(location)){
                        if(!products.contains(o.getProduct())){
                            products.add(o.getProduct());
                            quantities.add(o.getQuantity());
                        }
                    }
                }).collect(Collectors.toList());
                locations.stream().filter(l->!l.equals(location)).collect(Collectors.toList());
                products.stream().peek(product -> {
                    OrderDetail orderDetail=new OrderDetail();
                    orderDetail.setProduct(product);
                    orderDetail.setQuantity(quantities.get(products.indexOf(product)));
                    Stock stock=stockRepository.findStockByLocationAndProduct(location.getId(), product.getId());
                    stock.setQuantity(stock.getQuantity()-orderDetail.getQuantity());
                    stockRepository.save(stock);
                    orderDetail.setOrder(order);
                    orderDetails.add(orderDetail);
                }).collect(Collectors.toList());

            }

        }).collect(Collectors.toList());
        order.setOrderDetails(orderDetails);
        orderRepository.save(order);
        return order;

    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
