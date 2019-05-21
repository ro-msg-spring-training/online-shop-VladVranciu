package ro.msg.learning.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity(name = "ORDER_TABLE")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "shippedfrom", referencedColumnName = "id")
    private Location shippedFrom;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "idcustomer", referencedColumnName = "id")
    private Customer customer;
    @Column(name="createdat",columnDefinition = "DATE")
    private LocalDateTime createdAt;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval=true,fetch = FetchType.EAGER)
    @JoinColumn(name = "shippedto",referencedColumnName = "id")
    private Address shippedTo;

    @OneToMany(mappedBy = "product",orphanRemoval=true,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<OrderDetail> orderDetails = new ArrayList<>();

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Order order = (Order) o;
//        return Objects.equals(shippedFrom, order.shippedFrom) &&
//                Objects.equals(customer, order.customer) &&
//                Objects.equals(createdAt, order.createdAt) &&
//                Objects.equals(shippedTo, order.shippedTo) &&
//                Objects.equals(orderDetails, order.orderDetails);
//    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", shippedFrom=" + shippedFrom +
                ", customer=" + customer +
                ", createdAt=" + createdAt +
                ", shippedTo=" + shippedTo +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
