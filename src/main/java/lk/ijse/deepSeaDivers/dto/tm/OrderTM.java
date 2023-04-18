package lk.ijse.deepSeaDivers.dto.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderTM {
    private String orderId;
    private String orderDate;
    private String orderCompleteStatus;
    private String customerId;
    private String customerName;
    private JFXButton orderDetails;
}
