package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Product;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    @Query(value = "select u from Customer u where u.username=:username and u.password=:password")
    Customer login(@Param("username") String username, @Param("password") String password);

    @Query(value = "select u from Customer u where u.username=:username")
    Customer login(@Param("username") String username);
}
