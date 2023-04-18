package lk.ijse.deepSeaDivers.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FishSupplierTM {
    private String supplierId;
    private String supplierName;
    private String supplierAddress;
    private String supplierContactNumber;
    private String supplierBankNumber;
}
