package uz.pdp.kichikproekt.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.kichikproekt.entity.templeted.AbsEntity;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Measurement extends AbsEntity {

}
