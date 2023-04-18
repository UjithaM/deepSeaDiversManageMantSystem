package lk.ijse.deepSeaDivers.model;

import lk.ijse.deepSeaDivers.dto.Bill;
import lk.ijse.deepSeaDivers.dto.Driver;
import lk.ijse.deepSeaDivers.dto.Labour;
import lk.ijse.deepSeaDivers.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public static String generateNextLabourID() throws SQLException {

        String sql = "SELECT labourId FROM labour ORDER BY labourId DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);
        if(resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }
    public static String splitOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] strings = currentOrderId.split("L0");
            System.out.println(strings.length);
            int id = Integer.parseInt(strings[1]);
            id++;
            if (Integer.toString(id).trim().length() == 1) {
                return "L00" + id;
            }
            return "L0" + id;
        }
        return "L001";
    }
    public static List<Labour> labourSearchAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM labour");
        List<Labour> dataList = new ArrayList<>();

        while (resultSet.next()) {
            String labourId = resultSet.getString(1);
            String labourName = resultSet.getString(2);
            String labourAddress = resultSet.getString(3);
            String labourContactNumber = resultSet.getString(4);

            var Labour = new Labour(labourId, labourName, labourAddress, labourContactNumber);
            dataList.add(Labour);
        }
        return dataList;
    }
}
