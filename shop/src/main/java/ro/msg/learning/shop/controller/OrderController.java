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
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.OrderService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private StockRepository stockRepository;

    @PostMapping("/orders")
    ResponseEntity<?> save(@RequestBody OrderInputObject orderInputObject)throws OrderCannotBeCompletedException{
        Order order=orderService.createOrder(orderInputObject);
        if(order==null)
           throw new OrderCannotBeCompletedException();
        else
            return new ResponseEntity<>(order,null, HttpStatus.OK);

    }

    @GetMapping("/stocks")
    ResponseEntity<?> getStocks(){
        return new ResponseEntity<>(stockRepository.findAll(),null,HttpStatus.OK);
    }
}
