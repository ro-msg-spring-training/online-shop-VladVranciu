package ro.msg.learning.shop.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Integer id){
        super("Could not found product "+id);
    }
}
