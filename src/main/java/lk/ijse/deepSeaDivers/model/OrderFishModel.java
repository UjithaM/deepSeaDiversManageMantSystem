package lk.ijse.deepSeaDivers.model;

import lk.ijse.deepSeaDivers.dto.Fish;
import lk.ijse.deepSeaDivers.dto.Labour;
import lk.ijse.deepSeaDivers.dto.OrderDTO;
import lk.ijse.deepSeaDivers.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public static List<OrderDTO> orderAllSearch(String orderId) throws SQLException {
        String sql  = "SELECT * FROM order_fish WHERE orderId = ?";
        ResultSet resultSet = CrudUtil.execute(sql, orderId);

        List<OrderDTO> dataList = new ArrayList<>();

        while (resultSet.next()) {
            String orderid = resultSet.getString(1);
            String fishId = resultSet.getString(2);
            Integer fishOnHAnd = resultSet.getInt(3);

            var orderDTO = new OrderDTO(orderid, fishId,fishOnHAnd);
            dataList.add(orderDTO);
        }
        return dataList;
    }
}
