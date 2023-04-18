package lk.ijse.deepSeaDivers.model;

import lk.ijse.deepSeaDivers.dto.Bill;
import lk.ijse.deepSeaDivers.dto.Customer;
import lk.ijse.deepSeaDivers.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

        if (resultSet.next()) {
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

    public static String generateNextBillId() throws SQLException {
        String sql = "SELECT billId FROM bill ORDER BY billId DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    public static String splitOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] strings = currentOrderId.split("B00");
            int id = Integer.parseInt(strings[1]);
            id++;
            if (Integer.toString(id).trim().length() == 1) {
                return "B00" + id;
            }
            return "B0" + id;
        }
        return "B001";
    }
    public static List<Bill> billSearchAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM bill");
        List<Bill> dataList = new ArrayList<>();

        while (resultSet.next()) {
            String billId = resultSet.getString(1);
            String billDate = resultSet.getString(2);
            Double sumOfTheBill = resultSet.getDouble(3);
            String supplierId = resultSet.getString(4);
            String paidStatus = resultSet.getString(5);

            var Bill = new Bill(billId, billDate, sumOfTheBill, supplierId, paidStatus);
            dataList.add(Bill);
        }
        return dataList;
    }
}