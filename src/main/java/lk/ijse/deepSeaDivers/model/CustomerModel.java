package lk.ijse.deepSeaDivers.model;

import lk.ijse.deepSeaDivers.dto.Customer;
import lk.ijse.deepSeaDivers.util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {
    public static boolean customerSave(String custId, String custName, String custAddress, String custEmail) throws SQLException {
        String sql = "INSERT INTO customer(custId, custName, custAddress, custEmail) VALUES(?, ?, ?, ?)";

        return CrudUtil.execute(sql, custId, custName, custAddress, custEmail);
    }
    public static boolean customerDelete(String custId) throws SQLException {
        String sql = "DELETE FROM customer WHERE custId = ?";

        return CrudUtil.execute(sql, custId);
    }
    public static Customer customerSearch(String id) throws SQLException {
        String sql = "SELECT * FROM customer WHERE custId = ?";
        ResultSet resultSet = CrudUtil.execute(sql, id);

        if(resultSet.next()) {
            String custId = resultSet.getString(1);
            String custName = resultSet.getString(2);
            String custAddress = resultSet.getString(3);
            String custEmail = resultSet.getNString(4);

            return new Customer(custId, custName, custAddress, custEmail );
        }
        return null;
    }
    public static boolean customerUpdate(Customer customer) throws SQLException {
        String sql = "UPDATE customer SET custName = ?, custAddress = ?, custEmail = ? WHERE custId = ?";
        return CrudUtil.execute(sql, customer.getName(), customer.getAddress(), customer.getEmail(), customer.getId());
    }
    public static List<Customer> customerSearchAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM customer");
        List<Customer> dataList = new ArrayList<>();

        while (resultSet.next()) {
            String custId = resultSet.getString(1);
            String custName = resultSet.getString(2);
            String custAddress = resultSet.getString(3);
            String custEmail = resultSet.getString(4);

            var customer = new Customer(custId, custName, custAddress, custEmail);
            dataList.add(customer);
        }
        return dataList;
    }
}
