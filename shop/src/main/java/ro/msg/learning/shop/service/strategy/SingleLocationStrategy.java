package ro.msg.learning.shop.service.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.exception.OrderCannotBeCompletedException;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.model.DTO.OrderInputObject;
import ro.msg.learning.shop.repository.*;

import java.util.ArrayList;
import java.util.List;


public class SingleLocationStrategy implements Strategy {
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;
    private static final int idCustomer = 1;


    @Override
    public List<Order> compute(OrderInputObject orderInputObject) throws OrderCannotBeCompletedException {
        List<Order> orders=new ArrayList<>();
        List<OrderToCompute> orderToCompute = getOrderToCompute(orderInputObject);
        Location shippedFrom = orderToCompute.get(0).getLocation();
        Order order = new Order();
        order.setShippedFrom(shippedFrom);
        order.setCreatedAt(orderInputObject.getOrderTimeStamp());
        Customer customer = customerRepository.findById(idCustomer).get();
        order.setCustomer(customer);
        order.setShippedTo(orderInputObject.getAddress());
        List<OrderDetail> orderDetails = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        List<Integer> quantities = new ArrayList<>();
        for (OrderToCompute o : orderToCompute) {
            products.add(o.getProduct());
            quantities.add(o.getQuantity());
        }
        for (Product p : products) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(p);
            orderDetail.setQuantity(quantities.get(products.indexOf(p)));
            for (Stock stock : stockRepository.findAll()) {
                if (stock.getLocation().getId().equals(shippedFrom.getId()))
                    if (stock.getProduct().equals(p)) {
                        stock.setQuantity(stock.getQuantity() - quantities.get(products.indexOf(p)));
                        stockRepository.save(stock);
                    }
            }
            orderDetails.add(orderDetail);
        }

        order.setOrderDetails(orderDetails);
        orderRepository.save(order);
        orders.add(order);
        return orders;

    }

    private List<OrderToCompute> getOrderToCompute(OrderInputObject orderInputObject)throws OrderCannotBeCompletedException{
        List<OrderToCompute> orderToComputes=new ArrayList<>();
        for (Location l : locationRepository.findAll()) {
            List<Integer> productIds = new ArrayList<>();
            for (ProductInputObject p : orderInputObject.getProducts()) {
                for (Stock s : stockRepository.findAll()) {
                    if (s.getProduct().getId().equals(p.getProductId()))
                        if (s.getLocation().equals(l))
                            if (s.getQuantity() >= p.getQuantity()) {
                                productIds.add(p.getProductId());
                            }
                }
            }
            if(productIds.size()==orderInputObject.getProducts().size()){
                for(ProductInputObject p:orderInputObject.getProducts()){
                    orderToComputes.add(new OrderToCompute(l,productRepository.findById(p.getProductId()).get(),p.getQuantity()));
                }
                return orderToComputes;
            }

        }
        throw new OrderCannotBeCompletedException();
    }
}
