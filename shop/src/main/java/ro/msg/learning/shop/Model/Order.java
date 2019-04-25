package ro.msg.learning.shop.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Order {
    @Id
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shippedFrom", referencedColumnName = "id")
    private Location shippedFrom;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idcustomer", referencedColumnName = "id")
    private Customer customer;
    private LocalDateTime createdAt;
    private String country;
    private String city;
    private String county;
    private String streetAddress;

    @OneToMany(mappedBy = "product",orphanRemoval=true)
    @JsonIgnore
    List<OrderDetail> orderDetails = new ArrayList<>();
}
