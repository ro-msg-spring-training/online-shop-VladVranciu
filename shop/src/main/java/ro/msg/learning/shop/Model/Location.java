package ro.msg.learning.shop.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class Location {
    @Id
    private Integer id;
    private String name;
    private String country;
    private String city;
    private String county;
    private String streetAddress;

    @OneToMany(mappedBy = "location",orphanRemoval=true)
    @JsonIgnore
    private List<Stock> stocks=new ArrayList<>();

    @OneToMany(mappedBy = "location",orphanRemoval=true)
    @JsonIgnore
    private List<Revenue> revenues=new ArrayList<>();

    @OneToMany(mappedBy = "shippedFrom")
    @JsonIgnore
    private List<Order> orders=new ArrayList<>();
}
