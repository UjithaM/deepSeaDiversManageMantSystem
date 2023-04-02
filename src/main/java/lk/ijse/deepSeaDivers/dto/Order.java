package lk.ijse.deepSeaDivers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Order {
    private String orderId;
    private String orderDate;
    private String orderCompleteStatus;
    private String custId;
}
