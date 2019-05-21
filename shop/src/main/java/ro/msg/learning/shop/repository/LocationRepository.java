package ro.msg.learning.shop.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Product;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location,Integer> {

    @Query(value = "select l from Location l inner join Stock s on l.id = s.location and s.quantity>= :quantity and s.product=:product")
    List<Location> findSingleLocation(@Param("quantity") Integer quantity, @Param("product") Product product);

    @Query(value = "select l from Location l inner join Stock s on l.id = s.location and s.quantity>= :quantity and s.product=:product order by s.quantity desc")
    List<Location> findMostAbundantLocation(@Param("quantity") Integer quantity, @Param("product") Product product);
}
