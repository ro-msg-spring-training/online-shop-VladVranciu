package ro.msg.learning.shop.exception;

public class OrderCannotBeCompletedException extends Exception {
    public OrderCannotBeCompletedException(){
        super("Order cannot be completed. Products are not in stock");
    }
}
