package lk.ijse.deepSeaDivers.model;

import lk.ijse.deepSeaDivers.dto.Fish;
import lk.ijse.deepSeaDivers.util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class OrderFishModel {
    public static boolean save(String oId, List<Fish> fishList) throws SQLException {
        for(Fish fish :  fishList) {
            if(!save(oId, fish)) {
                return false;
            }
        }
        return true;
    }

    private static boolean save(String oId, Fish fish) throws SQLException {
        String sql = "INSERT INTO order_fish(orderId, fishId, fishQuantity) VALUES (?, ?, ?)";

        String fishId = fish.getFishId();
        Integer fishQuantity = fish.getFishOnHand();

        return CrudUtil.execute(sql, oId, fishId, fishQuantity);
    }
}
