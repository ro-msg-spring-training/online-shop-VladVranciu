package ro.msg.learning.shop.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Entity
@Data
public class Product {
    @Id
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Double weight;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idcategory", referencedColumnName = "id", nullable = true,insertable = false,
            updatable = false)
   // @JsonIgnore
    private ProductCategory category;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idsupplier", referencedColumnName = "id", nullable = true,insertable = false,
            updatable = false)
    //@JsonIgnore
    private Supplier supplier;
    private String imgUrl;

    @OneToMany(mappedBy = "product", orphanRemoval = true)
    @JsonIgnore
    List<Stock> stocks = new ArrayList<>();

    @OneToMany(mappedBy = "product", orphanRemoval = true)
    @JsonIgnore
    List<OrderDetail> orderDetails = new ArrayList<>();

}
