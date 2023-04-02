package lk.ijse.deepSeaDivers.model;

import lk.ijse.deepSeaDivers.dto.Fish;
import lk.ijse.deepSeaDivers.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FishModel {
    public static boolean fishSave(String fishId, String fishCOMMONNAME, String fishSCIENTIFICNAME, Double unitPrice, Integer fishOnHand) throws SQLException {
        String sql = "INSERT INTO fish(fishId, fishCOMMONNAME, fishSCIENTIFICNAME, unitPrice, fishOnHand) VALUES(?, ?, ?, ?, ?)";

        return CrudUtil.execute(sql, fishId, fishCOMMONNAME, fishSCIENTIFICNAME, unitPrice, fishOnHand);
    }
    public static boolean fishDelete(String fishId) throws SQLException {
        String sql = "DELETE FROM fish WHERE fishId = ?";

        return CrudUtil.execute(sql, fishId);
    }
    public static Fish fishSearch(String Id) throws SQLException {
        String sql = "SELECT * FROM fish WHERE fishId = ?";
        ResultSet resultSet = CrudUtil.execute(sql, Id);

        if(resultSet.next()) {
            String fishId = resultSet.getString(1);
            String fishCOMMONNAME = resultSet.getString(2);
            String fishSCIENTIFICNAME = resultSet.getString(3);
            Double unitPrice = resultSet.getDouble(4);
            Integer fishOnHand = resultSet.getInt(5);

            return new Fish(fishId, fishCOMMONNAME, fishSCIENTIFICNAME, unitPrice, fishOnHand);
        }
        return null;
    }
    public static boolean fishUpdate(Fish fish) throws SQLException {
        String sql = "UPDATE fish SET fishCOMMONNAME = ?, fishSCIENTIFICNAME = ?, unitPrice = ?, fishOnHand = ? WHERE fishId = ?";
        return CrudUtil.execute(sql, fish.getFishCOMMONNAME(), fish.getFishSCIENTIFICNAME(), fish.getUnitPrice(), fish.getFishOnHand(), fish.getFishId());
    }
    public static List<Fish> fishSearchAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM fish");
        List<Fish> dataList = new ArrayList<>();

        while (resultSet.next()) {
            String fishId = resultSet.getString(1);
            String fishCOMMONNAME = resultSet.getString(2);
            String fishSCIENTIFICNAME = resultSet.getString(3);
            Double unitPrice = resultSet.getDouble(4);
            Integer fishOnHand = resultSet.getInt(5);

            var fish = new Fish(fishId, fishCOMMONNAME, fishSCIENTIFICNAME, unitPrice, fishOnHand);
            dataList.add(fish);
        }
        return dataList;
    }

    public static boolean updateQty(List<Fish> fishDTOList) throws SQLException {
        for (Fish fish : fishDTOList) {
            if(!updateQty(fish)) {
                return false;
            }
        }
        return true;

    }
    private static boolean updateQty(Fish fish) throws SQLException {
        String sql = "UPDATE fish SET fishOnHand = (fish.fishOnHand - ?) WHERE fishId = ?";

        Integer fishOnHand = fish.getFishOnHand();
        String fishId = fish.getFishId();

        return CrudUtil.execute(sql,fishOnHand, fishId);
    }
}
