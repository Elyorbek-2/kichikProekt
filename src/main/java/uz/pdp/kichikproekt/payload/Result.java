package uz.pdp.kichikproekt.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result {
    private String massege;
    private boolean succes;
    private Object object;

    public Result(String massege, boolean succes) {
        this.massege = massege;
        this.succes = succes;
    }
}
