package ro.msg.learning.shop.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class ProductCategory {
    @Id
    private Integer id;
    private String name;
    private String description;

    @OneToMany(
            mappedBy = "category", cascade = CascadeType.ALL
    )
    @JsonIgnore
    private List<Product> productsCategory = new ArrayList<>();

}
