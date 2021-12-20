package uz.pdp.kichikproekt.payload;

import lombok.Data;

@Data
public class OutputProductDto {
    private Integer productId;
    private Integer amount;
    private double price;
    private Integer outputId;
}
