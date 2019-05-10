package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.exception.OrderCannotBeCompletedException;
import ro.msg.learning.shop.model.DTO.OrderInputObject;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.service.OrderService;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;


    @PostMapping("/orders")
    ResponseEntity<?> save(@RequestBody OrderInputObject orderInputObject)throws OrderCannotBeCompletedException{
        if(orderService.createOrder(orderInputObject).isEmpty())
           throw new OrderCannotBeCompletedException();
        else
            return new ResponseEntity<>(orderInputObject,null, HttpStatus.OK);

    }

    @GetMapping("/orders")
    List<Order> orderList(){
        return orderService.findAll();
    }
}
