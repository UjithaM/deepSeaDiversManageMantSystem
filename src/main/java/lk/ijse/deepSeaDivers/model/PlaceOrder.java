package lk.ijse.deepSeaDivers.model;

import lk.ijse.deepSeaDivers.db.DBConnection;
import lk.ijse.deepSeaDivers.dto.Fish;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PlaceOrder {
    public static boolean placeOrder(String oId,String orderDate, String orderComplitionStatus, String cusId, List<Fish> fishDTOList) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();

            con.setAutoCommit(false);

            boolean isSaved = OrderModel.orderSave(oId,orderDate, orderComplitionStatus, cusId);
            if (isSaved) {
                boolean isUpdated = FishModel.updateQty(fishDTOList);
                if (isUpdated) {
                    boolean isOrderDetailSaved = OrderFishModel.save(oId, fishDTOList);
                    if (isOrderDetailSaved) {
                        con.commit();
                        return true;
                    }
                }
            }
            return false;
        } catch (SQLException er) {
            er.printStackTrace();
            con.rollback();
            return false;
        } finally {
            con.setAutoCommit(true);
        }
    }
}
