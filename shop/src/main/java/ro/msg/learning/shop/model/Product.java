package ro.msg.learning.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Entity
@Data
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Double weight;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idcategory", referencedColumnName = "id")
    private ProductCategory productCategory;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idsupplier", referencedColumnName = "id")
    private Supplier supplier;

    @Column(name = "IMAGEURL")
    private String imgUrl;

    @OneToMany(mappedBy = "product", orphanRemoval = true,cascade = CascadeType.ALL)
            @JsonIgnore
    List<Stock> stocks = new ArrayList<>();

    @OneToMany(mappedBy = "product", orphanRemoval = true,cascade = CascadeType.ALL)
            @JsonIgnore
    List<OrderDetail> orderDetails = new ArrayList<>();

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", productCategory=" + productCategory +
                ", supplier=" + supplier +
                ", imgUrl='" + imgUrl + '\'' +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
