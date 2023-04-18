package lk.ijse.deepSeaDivers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDTO {
        private String orderId;
        private String fishId;
        private Integer fishQuantity;
}
