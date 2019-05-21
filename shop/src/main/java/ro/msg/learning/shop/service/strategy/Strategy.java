package ro.msg.learning.shop.service.strategy;

import ro.msg.learning.shop.exception.OrderCannotBeCompletedException;
import ro.msg.learning.shop.model.DTO.OrderInputObject;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.model.OrderToCompute;
import ro.msg.learning.shop.model.StrategyEnum;

import java.util.List;

public interface Strategy {
    //List<OrderToCompute> compute(OrderInputObject orderInputObject) throws Exception;
    List<OrderToCompute> compute(OrderInputObject orderInputObject) throws OrderCannotBeCompletedException;
    StrategyEnum getStrategy();
}
