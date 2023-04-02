package lk.ijse.deepSeaDivers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Fish {
    private String fishId;
    private String fishCOMMONNAME;
    private String fishSCIENTIFICNAME;
    private Double unitPrice;
    private Integer fishOnHand;
}
