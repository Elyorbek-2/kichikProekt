package uz.pdp.kichikproekt.payload;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class InputDto {
    private Timestamp timestamp;
    private Integer warehouseId;
    private Integer supplierId;
    private Integer currencyId;
    private Integer factureNumber;
}
