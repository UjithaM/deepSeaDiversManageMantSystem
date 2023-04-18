package lk.ijse.deepSeaDivers.model;


import lk.ijse.deepSeaDivers.dto.Fishsupplier;
import lk.ijse.deepSeaDivers.util.CrudUtil;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FishSupplierModel {
    public static boolean fishSupplierSave(String supplierId, String supplierName, String supplierAddress, String supplierContactNumber, String supplierBankNumber) throws SQLException {
        String sql = "INSERT INTO fishsupplier(supplierId, supplierName, supplierAddress, supplierContactNumber, supplierBankNumber) VALUES(?, ?, ?, ?, ?)";

        return CrudUtil.execute(sql, supplierId, supplierName, supplierAddress, supplierContactNumber, supplierBankNumber);
    }
    public static boolean fishSupplierDelete(String supplierId) throws SQLException {
        String sql = "DELETE FROM fishsupplier WHERE supplierId = ?";

        return CrudUtil.execute(sql, supplierId);
    }
    public static Fishsupplier fishSupplierSearch(String Id) throws SQLException {
        String sql = "SELECT * FROM fishsupplier WHERE supplierId = ?";
        ResultSet resultSet = CrudUtil.execute(sql, Id);

        if(resultSet.next()) {
            String supplierId = resultSet.getString(1);
            String supplierName = resultSet.getString(2);
            String supplierAddress = resultSet.getString(3);
            String supplierContactNumber = resultSet.getString(4);
            String supplierBankNumber = resultSet.getString(5);

            return new Fishsupplier(supplierId, supplierName, supplierAddress, supplierContactNumber, supplierBankNumber);
        }
        return null;
    }
    public static boolean fishSupplierUpdate(Fishsupplier fishSupplier) throws SQLException {
        String sql = "UPDATE fishsupplier SET supplierName = ?, supplierAddress = ?, supplierContactNumber = ?, supplierBankNumber  = ? WHERE supplierId = ?";
        return CrudUtil.execute(sql, fishSupplier.getSupplierName(), fishSupplier.getSupplierAddress(), fishSupplier.getSupplierContactNumber(), fishSupplier.getSupplierBankNumber(), fishSupplier.getSupplierId());
    }
    public static List<Fishsupplier> fishSupplierSearchAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM fishsupplier");
        List<Fishsupplier> dataList = new ArrayList<>();

        while (resultSet.next()) {
            String supplierId = resultSet.getString(1);
            String supplierName = resultSet.getString(2);
            String supplierAddress = resultSet.getString(3);
            String supplierContactNumber = resultSet.getString(4);
            String supplierBankNumber = resultSet.getString(5);

            var fishSupplier = new Fishsupplier(supplierId, supplierName, supplierAddress, supplierContactNumber, supplierBankNumber);
            dataList.add(fishSupplier);
        }
        return dataList;
    }
    public static List<String> getSupplierIds() throws SQLException {
        String sql = "SELECT supplierId FROM fishsupplier";

        List<String> ids = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            ids.add(resultSet.getString(1));
        }
        return ids;
    }
    public static String generateNextFishId() throws SQLException {

        String sql = "SELECT supplierId FROM fishsupplier ORDER BY supplierId DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);
        if(resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }
    public static String splitOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] strings = currentOrderId.split("S0");
            System.out.println(strings.length);
            int id = Integer.parseInt(strings[1]);
            id++;
            if (Integer.toString(id).trim().length() == 1) {
                return "S00" + id;
            }
            return "S0" + id;
        }
        return "S001";
    }
}
