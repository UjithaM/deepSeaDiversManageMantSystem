package lk.ijse.deepSeaDivers.model;

import lk.ijse.deepSeaDivers.dto.Customer;
import lk.ijse.deepSeaDivers.dto.Driver;
import lk.ijse.deepSeaDivers.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverModel {
    public static boolean driverSave(String driverId, String driverName, String driverContactNumber ) throws SQLException {
        String sql = "INSERT INTO driver(driverId, driverName, driverContactNumber) VALUES(?, ?, ?)";

        return CrudUtil.execute(sql, driverId, driverName, driverContactNumber);
    }
    public static boolean driverDelete(String driverId) throws SQLException {
        String sql = "DELETE FROM driver WHERE driverId = ?";

        return CrudUtil.execute(sql, driverId);
    }
    public static Driver driverSearch(String id) throws SQLException {
        String sql = "SELECT * FROM driver WHERE driverId = ?";
        ResultSet resultSet = CrudUtil.execute(sql, id);

        if(resultSet.next()) {
            String driverId = resultSet.getString(1);
            String driverName = resultSet.getString(2);
            String driverContactNumber = resultSet.getString(3);

            return new Driver(driverId, driverName, driverContactNumber);
        }
        return null;
    }
    public static boolean driverUpdate(Driver driver) throws SQLException {
        String sql = "UPDATE driver SET driverName = ?, driverContactNumber = ? WHERE driverId = ?";
        return CrudUtil.execute(sql, driver.getDriverName(), driver.getDriverContactNumber(), driver.getDriverId());
    }
    public static List<Driver> driverSearchAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM driver");
        List<Driver> dataList = new ArrayList<>();

        while (resultSet.next()) {
            String driverId = resultSet.getString(1);
            String driverName = resultSet.getString(2);
            String driverContactNumber = resultSet.getString(3);

            var driver = new Driver(driverId, driverName, driverContactNumber);
            dataList.add(driver);
        }
        return dataList;
    }
    public static String generateNextDriverId() throws SQLException {

        String sql = "SELECT driverId FROM driver ORDER BY driverId DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);
        if(resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }
    public static String splitOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] strings = currentOrderId.split("D0");
            System.out.println(strings.length);
            int id = Integer.parseInt(strings[1]);
            id++;
            if (Integer.toString(id).trim().length() == 1) {
                return "D00" + id;
            }
            return "D0" + id;
        }
        return "D001";
    }
}
