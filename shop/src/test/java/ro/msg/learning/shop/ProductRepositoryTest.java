package ro.msg.learning.shop;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.SupplierRepository;

import java.math.BigDecimal;


public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Test
    public void test1(){
        Product product1=new Product();
        product1.setId(5);
        ProductCategory productCategory=new ProductCategory(1,"DA","desc");
        product1.setProductCategory(productCategory);
        product1.setDescription("Descriere");
        product1.setName("produs1");
        product1.setPrice(new BigDecimal(2));
        Supplier supplier=new Supplier(1,"name");
        product1.setSupplier(supplier);
        product1.setWeight(5d);

        productRepository.save(product1);
        Product product2=productRepository.findById(5).get();
        Assert.assertEquals(product1,product2);
    }
}
