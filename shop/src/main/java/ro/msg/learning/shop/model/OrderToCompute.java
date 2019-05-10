package ro.msg.learning.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderToCompute {

    private Location location;
    private Product product;
    private Integer quantity;

    @Override
    public String toString() {
        return "OrderToCompute{" +
                ", quantity=" + quantity +
                '}';
    }
}
