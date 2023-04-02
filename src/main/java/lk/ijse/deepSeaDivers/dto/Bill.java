package lk.ijse.deepSeaDivers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Bill {
    private String billId;
    private String billDate;
    private Double sumOfTheBill;
    private String supplierId;
    private String paidStatus;
}
