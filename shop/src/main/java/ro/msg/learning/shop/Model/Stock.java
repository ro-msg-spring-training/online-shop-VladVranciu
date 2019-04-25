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
public class Stock {
    @Id
    private Integer id;
    @ManyToOne
    @JoinColumn(name="idproduct",referencedColumnName = "id",nullable = false)
    private Product product;
    @ManyToOne
    @JoinColumn(name = "idlocation",referencedColumnName = "id",nullable = false)
    private Location location;
    private Integer quantity;
}
