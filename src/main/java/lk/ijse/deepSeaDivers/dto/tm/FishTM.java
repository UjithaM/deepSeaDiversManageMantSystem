package lk.ijse.deepSeaDivers.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FishTM {
    private String fishID;
    private String fishCommonName;
    private String fishScientificName;
    private Double unitPrice;
    private Integer fishOnHand;
}
