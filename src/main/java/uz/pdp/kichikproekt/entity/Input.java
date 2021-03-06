package uz.pdp.kichikproekt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Input {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "kun")
    private Timestamp kun;

    @ManyToOne
    private Warehouse warehouse;
    @ManyToOne
    private Supplier supplier;
    @ManyToOne
    private Currency currency;

    private Integer factureNumber;
    private Integer code;
}
