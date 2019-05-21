package ro.msg.learning.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name="ADDRESS")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String country;
    private String city;
    private String county;
    @Column(name = "STREETADDRESS")
    private String streetAddress;


    public Address(String country, String city, String county, String streetAddress) {
        this.country = country;
        this.city = city;
        this.county = county;
        this.streetAddress = streetAddress;
    }

    public Address(Integer id, String country, String city, String county, String streetAddress) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.county = county;
        this.streetAddress = streetAddress;
    }

    @OneToOne(mappedBy = "address",orphanRemoval=true,cascade = CascadeType.ALL)
    @JsonIgnore
    private Location location;

    @OneToMany(mappedBy = "shippedTo",orphanRemoval = true,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order> order;

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(country, address.country) &&
                Objects.equals(city, address.city) &&
                Objects.equals(county, address.county) &&
                Objects.equals(streetAddress, address.streetAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, county, streetAddress);
    }
}
