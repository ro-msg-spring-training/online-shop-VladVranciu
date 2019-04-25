package ro.msg.learning.shop.Model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.Model.OrderDetail;
import ro.msg.learning.shop.Model.ProductCategory;
import ro.msg.learning.shop.Model.Stock;
import ro.msg.learning.shop.Model.Supplier;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements Serializable {

    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Double weight;
    @JsonIgnore
    private ProductCategory category;
    private String supplierName;
    private Integer supplierId;
    private String imgUrl;

    @JsonIgnore
    private List<Stock> stocks;
    @JsonIgnore
    private List<OrderDetail> orderDetails;

}
