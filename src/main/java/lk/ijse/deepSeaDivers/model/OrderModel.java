package lk.ijse.deepSeaDivers.model;

import lk.ijse.deepSeaDivers.dto.Labour;
import lk.ijse.deepSeaDivers.dto.Order;
import lk.ijse.deepSeaDivers.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderModel {
    public static boolean orderSave(String orderId, String orderDate, String orderCompleteStatus, String custId) throws SQLException {
        String sql = "INSERT INTO orders(orderId, orderDate, orderCompleteStatus, custId) VALUES(?, ?, ?, ?)";

        return CrudUtil.execute(sql, orderId, orderDate, orderCompleteStatus, custId);
    }

    public static boolean orderDelete(String orderId) throws SQLException {
        String sql = "DELETE FROM orders WHERE orderId = ?";

        return CrudUtil.execute(sql, orderId);
    }

    public static Order orderSearch(String id) throws SQLException {
        String sql = "SELECT * FROM order WHERE orderId = ?";
        ResultSet resultSet = CrudUtil.execute(sql, id);

        if(resultSet.next()) {
            String orderId = resultSet.getString(1);
            String orderDate = resultSet.getString(2);
            String orderCompleteStatus = resultSet.getString(3);
            String custId = resultSet.getString(4);

            return new Order(orderId, orderDate, orderCompleteStatus, custId);
        }
        return null;
    }
    public static boolean orderUpdate(Order order) throws SQLException {
        String sql = "UPDATE order SET orderDate = ?, orderCompleteStatus = ?, custId = ? WHERE orderId = ?";
        return CrudUtil.execute(sql, order.getOrderDate(), order.getOrderCompleteStatus(), order.getCustId(), order.getOrderId());
    }
}
