package lk.ijse.deepSeaDivers.model;

import lk.ijse.deepSeaDivers.dto.Driver;
import lk.ijse.deepSeaDivers.dto.Labour;
import lk.ijse.deepSeaDivers.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LabourModel {
    public static boolean labourSave(String labourId, String labourName, String labourAddress, String labourContactNumber) throws SQLException {
        String sql = "INSERT INTO labour(labourId, labourName, labourAddress, labourContactNumber) VALUES(?, ?, ?, ?)";

        return CrudUtil.execute(sql, labourId, labourName, labourAddress, labourContactNumber);
    }

    public static boolean labourDelete(String labourId) throws SQLException {
        String sql = "DELETE FROM labour WHERE labourId = ?";

        return CrudUtil.execute(sql, labourId);
    }

    public static Labour labourSearch(String id) throws SQLException {
        String sql = "SELECT * FROM labour WHERE labourId = ?";
        ResultSet resultSet = CrudUtil.execute(sql, id);

        if(resultSet.next()) {
            String labourId = resultSet.getString(1);
            String labourName = resultSet.getString(2);
            String labourAddress = resultSet.getString(3);
            String labourContactNumber = resultSet.getString(4);

            return new Labour(labourId, labourName, labourAddress, labourContactNumber);
        }
        return null;
    }
    public static boolean labourUpdate(Labour labour) throws SQLException {
        String sql = "UPDATE labour SET labourName = ?, labourAddress = ?, labourContactNumber = ? WHERE labourId = ?";
        return CrudUtil.execute(sql, labour.getLabourName(), labour.getLabourAddress(), labour.getLabourContactNumber(), labour.getLabourId());
    }
}
