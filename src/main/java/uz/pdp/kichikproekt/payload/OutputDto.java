package uz.pdp.kichikproekt.payload;

import lombok.Data;

@Data
public class OutputDto {
    private Integer warehouseId;
    private Integer currencyId;
    private Integer clientId;
    private Integer factureNumber;
}
