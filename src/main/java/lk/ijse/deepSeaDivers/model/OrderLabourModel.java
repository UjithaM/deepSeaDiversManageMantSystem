package lk.ijse.deepSeaDivers.model;


import lk.ijse.deepSeaDivers.dto.Labour;
import lk.ijse.deepSeaDivers.util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class OrderLabourModel {
    public static boolean save(String oId, List<Labour> labourList) throws SQLException {
        for(Labour labour :  labourList) {
            if(!save(oId, labour)) {
                return false;
            }
        }
        return true;
    }

    private static boolean save(String oId, Labour labour) throws SQLException {
        String sql = "INSERT INTO orders_labour(orderId, labourId) VALUES (?, ?)";

        String labourId = labour.getLabourId();

        return CrudUtil.execute(sql, oId, labourId );
    }

}
