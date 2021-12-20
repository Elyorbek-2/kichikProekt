package uz.pdp.kichikproekt.payload;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class InputProductDto {
    private Integer productId;
    private Integer amount;
    private double price;
    private Timestamp expireDate;
    private Integer inputId;
}
