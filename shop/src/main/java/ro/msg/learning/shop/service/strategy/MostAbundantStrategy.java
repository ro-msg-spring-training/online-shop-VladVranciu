package ro.msg.learning.shop.service.strategy;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.exception.OrderCannotBeCompletedException;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.model.DTO.OrderInputObject;
import ro.msg.learning.shop.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ro.msg.learning.shop.model.StrategyEnum.MOSTABUNDANT;


@NoArgsConstructor
@AllArgsConstructor
public class MostAbundantStrategy implements Strategy {

    private ProductRepository productRepository;
    private LocationRepository locationRepository;

    public StrategyEnum getStrategy(){
        return MOSTABUNDANT;
    }

    public List<OrderToCompute> compute(OrderInputObject orderInputObject) throws OrderCannotBeCompletedException {
        List<OrderToCompute> orderToComputes = new ArrayList<>();
        List<Location> allLocations=new ArrayList<>();
        orderInputObject.getProducts().stream().peek(p-> allLocations.add(locationRepository.findMostAbundantLocation(p.getQuantity(),productRepository.findById(p.getProductId()).get()).get(0))).collect(Collectors.toList());

        if(allLocations.size()!=orderInputObject.getProducts().size() || allLocations.contains(null))
            throw new OrderCannotBeCompletedException();

        allLocations.stream().peek(p->{
            orderToComputes.add(new OrderToCompute(p,productRepository.findById(orderInputObject.getProducts().get(allLocations.indexOf(p)).getProductId()).get(),orderInputObject.getProducts().get(allLocations.indexOf(p)).getQuantity()));
        }).collect(Collectors.toList());

        return orderToComputes;
    }
}
