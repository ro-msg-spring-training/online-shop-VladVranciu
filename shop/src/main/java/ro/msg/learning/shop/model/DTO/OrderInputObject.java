package ro.msg.learning.shop.model.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.model.Address;
import ro.msg.learning.shop.model.ProductInputObject;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class OrderInputObject implements Serializable {

    private LocalDateTime orderTimeStamp;
    private Address address;
    private List<ProductInputObject> products;

}
