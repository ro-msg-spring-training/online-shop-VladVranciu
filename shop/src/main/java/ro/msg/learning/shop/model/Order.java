package ro.msg.learning.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "ORDER_TABLE")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shippedfrom", referencedColumnName = "id")
    private Location shippedFrom;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idcustomer", referencedColumnName = "id")
    private Customer customer;
    @Column(name="createdat",columnDefinition = "DATE")
    private LocalDateTime createdAt;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval=true)
    @JoinColumn(name = "shippedto",referencedColumnName = "id")
    private Address shippedTo;

    @OneToMany(mappedBy = "product",orphanRemoval=true,cascade = CascadeType.ALL)
    List<OrderDetail> orderDetails = new ArrayList<>();
}
