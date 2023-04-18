package lk.ijse.deepSeaDivers.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDetailsTM {
    private String fishId;
    private String fishCommonName;
    private Integer fishQuantity;
}
