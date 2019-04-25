package ro.msg.learning.shop.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Revenue {
    @Id
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idlocation",referencedColumnName = "id", nullable = false)
    private Location location;
    private LocalDate date;
    private BigDecimal sum;
}
