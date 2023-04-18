package lk.ijse.deepSeaDivers.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BillTM {
    private String billId;
    private String billDate;
    private Double sumOfTheBill;
    private String supplierID;
    private String paidStatus;
}
