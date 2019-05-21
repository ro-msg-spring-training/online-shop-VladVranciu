package ro.msg.learning.shop;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.exception.OrderCannotBeCompletedException;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.model.DTO.OrderInputObject;
import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.service.OrderService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest(
        classes = ShopApplication.class)
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")

@AutoConfigureMockMvc
@Profile("integrationtest")
public class CreateNewOrderIntegrationTest {

    @Autowired
    private OrderService orderService;

    @Rule
    public ExpectedException expectedException= ExpectedException.none();

    @Test
    public void createNewOrderSuccessfully() throws OrderCannotBeCompletedException {
        List<ProductInputObject> productList = new ArrayList<>();
        productList.add(new ProductInputObject(1, 1));
        OrderInputObject orderInputObject = new OrderInputObject(LocalDateTime.now(), new Address("Romania", "Iasi", "Iasi", "address1"), productList);
        Assert.assertNotNull(orderService.createOrder(orderInputObject));
    }

    @Test
    public void failToCreateNewOrderDueToMissingStock()throws OrderCannotBeCompletedException{
        expectedException.expect(OrderCannotBeCompletedException.class);
        List<ProductInputObject> productList = new ArrayList<>();
        productList.add(new ProductInputObject(1, 4000));
        OrderInputObject orderInputObject = new OrderInputObject(LocalDateTime.now(), new Address("Romania", "Iasi", "Iasi", "address1"), productList);
        orderService.createOrder(orderInputObject);
    }










//    @Test
//    public void test1(){
//        Location loc1 = new Location(1, "location1", new Address(1,"la", "la", "la", "la"));
//        Address address= new Address(2,"la2", "la2", "la2", "la2");
//
//        Customer customer=new Customer(1,"lasname","firstname","username","password","mail@mail.com");
//
//        ProductCategory productCategory=new ProductCategory(1,"papuci","smc");
//        Supplier supplier=new Supplier(1,"Supplier1");
//        Product product1=new Product(1,"Vans","for feet",new BigDecimal(150),50d,productCategory,supplier);
//
//        OrderDetail orderDetail=new OrderDetail();
//        orderDetail.setProduct(product1);
//        orderDetail.setQuantity(44);
//        orderDetail.setId(1);
//
//
//        Order order=new Order();
//        order.setId(1);
//        order.setShippedFrom(loc1);
//        order.setShippedTo(address);
//        order.setCustomer(customer);
//        order.setCreatedAt(LocalDateTime.now());
//
//        orderDetail.setOrder(order);
//
//        order.setOrderDetails(Arrays.asList(orderDetail));
//
//        orderRepository.save(order);
//
//        Assert.assertEquals(orderRepository.findById(1).get().getId(),order.getId());
//        Assert.assertEquals(orderRepository.findById(1).get().getCreatedAt(),order.getCreatedAt());
//        Assert.assertEquals(orderRepository.findById(1).get().getShippedTo(),order.getShippedTo());
//
//    }
}
