package lk.ijse.deepSeaDivers.model;

import lk.ijse.deepSeaDivers.dto.Bill;
import lk.ijse.deepSeaDivers.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillModel {

    public static boolean billSave(String billId, String billDate, Double sumOfTheBill, String supplierId, String paidStatus) throws SQLException {
        String sql = "INSERT INTO bill(billId, billDate, sumOfTheBill, supplierId, paidStatus) VALUES(?, ?, ?, ?, ?)";

        return CrudUtil.execute(sql, billId, billDate, sumOfTheBill, supplierId, paidStatus);
    }
    public static boolean billDelete(String billId) throws SQLException {
        String sql = "DELETE FROM bill WHERE billId = ?";

        return CrudUtil.execute(sql, billId);
    }
    public static Bill billSearch(String billId) throws SQLException {
        String sql = "SELECT * FROM bill WHERE billId = ?";
        ResultSet resultSet = CrudUtil.execute(sql, billId);

        if(resultSet.next()) {
            String Id = resultSet.getString(1);
            String billDate = resultSet.getString(2);
            Double sumOfTheBill = resultSet.getDouble(3);
            String supplierId = resultSet.getString(4);
            String paidStatus = resultSet.getString(5);

            return new Bill(Id, billDate, sumOfTheBill, supplierId, paidStatus);
        }
        return null;
    }
    public static boolean billUpdate(Bill bill) throws SQLException {
        String sql = "UPDATE bill SET billDate = ?, sumOfTheBill = ?, supplierId = ?, paidStatus = ? WHERE billId = ?";
        return CrudUtil.execute(sql, bill.getBillDate(), bill.getSumOfTheBill(), bill.getSupplierId(), bill.getPaidStatus(), bill.getBillId());
    }
}
