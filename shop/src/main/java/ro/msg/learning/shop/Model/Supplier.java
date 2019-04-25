package ro.msg.learning.shop.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
    @Id
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "supplier",
            cascade = CascadeType.ALL)
    @JsonIgnore
    List<Product> productsSupplier = new ArrayList<>();

    public Supplier(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
