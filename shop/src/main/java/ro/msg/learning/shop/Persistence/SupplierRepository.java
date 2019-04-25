package ro.msg.learning.shop.Persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.Model.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
}
