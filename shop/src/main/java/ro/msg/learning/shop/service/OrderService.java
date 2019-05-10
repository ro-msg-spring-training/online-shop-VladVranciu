package ro.msg.learning.shop.service;

import ro.msg.learning.shop.exception.OrderCannotBeCompletedException;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.model.DTO.OrderInputObject;

import java.util.List;

public interface OrderService {
    List<Order> createOrder(OrderInputObject orderInputObject)throws OrderCannotBeCompletedException;
    List<Order> findAll();
}
