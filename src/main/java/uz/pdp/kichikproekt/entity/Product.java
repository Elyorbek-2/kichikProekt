package uz.pdp.kichikproekt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.kichikproekt.entity.templeted.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends AbsEntity {
    private Integer id;
    @ManyToOne(optional = false)
    private Category category;
    @OneToOne
    private Attachment attachment;

    private Integer code;
    @ManyToOne(optional = false)
    private Measurement measurement;

}
