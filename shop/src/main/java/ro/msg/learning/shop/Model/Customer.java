package ro.msg.learning.shop.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;

    @OneToMany(mappedBy = "customer",orphanRemoval=true)
    @JsonIgnore
    private List<Order> orders=new ArrayList<>();

}
