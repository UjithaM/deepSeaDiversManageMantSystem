package lk.ijse.deepSeaDivers.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.deepSeaDivers.dto.OrderDTO;
import lk.ijse.deepSeaDivers.dto.tm.OrderDetailsTM;
import lk.ijse.deepSeaDivers.model.FishModel;
import lk.ijse.deepSeaDivers.model.OrderFishModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class BillDetailsController implements Initializable {
    @FXML
    private JFXComboBox<?> cmbFishCode;

    @FXML
    private TableColumn<OrderDetailsTM, String> colFishCommonName;

    @FXML
    private TableColumn<OrderDetailsTM, String> colFishId;

    @FXML
    private TableColumn<OrderDetailsTM, Integer> colFishQuantity;

    @FXML
    private Label lblFishName;

    @FXML
    private Label lblqtyOnHand;

    @FXML
    private TableView<OrderDetailsTM> tblOrderDetails;

    @FXML
    private TextField txtQty;

    @FXML
    void cmbFishOnAction(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        visualize();
        getAll();
    }
    public void visualize(){
        colFishId.setCellValueFactory(new PropertyValueFactory<OrderDetailsTM,String>("fishId"));
        colFishCommonName.setCellValueFactory(new PropertyValueFactory<OrderDetailsTM,String>("fishCommonName"));
        colFishQuantity.setCellValueFactory(new PropertyValueFactory<OrderDetailsTM,Integer>("fishQuantity"));
    }
    void getAll() {
        try {
            ObservableList<OrderDetailsTM> obList = FXCollections.observableArrayList();
            List<OrderDTO> orderDTOS = OrderFishModel.orderAllSearch(OrdersFoamController.orderIdForDetails);

            for(OrderDTO orderDTO : orderDTOS) {
                obList.add(new OrderDetailsTM(
                        orderDTO.getFishId(),
                        FishModel.fishSearch(orderDTO.getFishId()).getFishCOMMONNAME(),
                        orderDTO.getFishQuantity()
                ));
            }
            tblOrderDetails.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }
}
