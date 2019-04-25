package ro.msg.learning.shop.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
public class OrderDetail {
    @Id
    private Integer id;
    @ManyToOne
    @JoinColumn(name="idorder",referencedColumnName= "id")
    private Order order;
    @ManyToOne
    @JoinColumn(name="idproduct",referencedColumnName = "id")
    private Product product;
    private Integer quantity;

}
