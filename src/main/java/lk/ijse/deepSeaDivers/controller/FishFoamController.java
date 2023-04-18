package lk.ijse.deepSeaDivers.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.ijse.deepSeaDivers.RegExPattern.RegExPatterns;
import lk.ijse.deepSeaDivers.dto.Fish;
import lk.ijse.deepSeaDivers.dto.Order;
import lk.ijse.deepSeaDivers.dto.tm.FishTM;
import lk.ijse.deepSeaDivers.model.CustomerModel;
import lk.ijse.deepSeaDivers.model.FishModel;
import lk.ijse.deepSeaDivers.model.OrderModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class FishFoamController implements Initializable {
    @FXML
    private TableColumn<FishTM, String> colFishCommonName;

    @FXML
    private TableColumn<FishTM, String> colFishId;

    @FXML
    private TableColumn<FishTM, Integer> colFishOnHand;

    @FXML
    private TableColumn<FishTM, String> colFishScientificName;

    @FXML
    private TableColumn<FishTM, Double> colUnitPrice;
    @FXML
    private TableView<FishTM> tblFish;
    @FXML
    private JFXButton btnOwner;

    @FXML
    private Label lblCustomer1;

    @FXML
    private Label lblCustomer2;

    @FXML
    private Label lblCustomer3;

    @FXML
    private Label lblCustomer4;

    @FXML
    private Label lblFishId;

    @FXML
    private Label lblOrderDate1;

    @FXML
    private Label lblOrderDate2;

    @FXML
    private Label lblOrderDate3;

    @FXML
    private Label lblOrderDate4;

    @FXML
    private Pane pane1;

    @FXML
    private Pane pane2;

    @FXML
    private Pane pane3;

    @FXML
    private Pane pane4;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtFieldFishId;

    @FXML
    private TextField txtFishCommanName;

    @FXML
    private TextField txtFishScientficName;

    @FXML
    private TextField txtFishUnitPrice;

    @FXML
    private TextField txtFishonHand;

    @FXML
    void btnBillsOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Bills_Foam.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Bill");
        stage.centerOnScreen();
    }

    @FXML
    void btnCustomersOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Customer_foam.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Customer");
        stage.centerOnScreen();
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Dashboard_form.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.centerOnScreen();
    }

    @FXML
    void btnDriversOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Drivers_Foam.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Driver");
        stage.centerOnScreen();
    }

    @FXML
    void btnFishStockOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Fish_Foam.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Driver");
        stage.centerOnScreen();
    }

    @FXML
    void btnLaborsOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Labors_Foam.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Order");
        stage.centerOnScreen();
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) {
        System.out.println("log");
    }

    @FXML
    void btnOrdersOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Orders_Foam.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Order");
        stage.centerOnScreen();
    }

    @FXML
    void btnSuppliersOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Suppliers_foam.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Order");
        stage.centerOnScreen();
    }

    public void codeSearchOnAction(ActionEvent event) {

    }
    public void btnSeeDetails4OnAction(ActionEvent event) {

    }

    public void btnSeeDetails3OnAction(ActionEvent event) {

    }

    public void btnSeeDetails2OnAction(ActionEvent event) {

    }

    public void btnOwnerOnAction(ActionEvent event) {

    }

    public void btnSeeDetails1OnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpcomingOrder();
        generateNextFishId();
        visualize();
        getAll();

    }
    private void generateNextFishId() {
        try {
            String nextId = FishModel.generateNextFishId();
            txtFieldFishId.setText(nextId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    void setUpcomingOrder() {
        try {
            List<Order> orderList  = OrderModel.orderList();
            Integer order = 0;
            pane1.setVisible(false);
            pane2.setVisible(false);
            pane3.setVisible(false);
            pane4.setVisible(false);

            for (Order od:orderList) {
                if (od.getOrderCompleteStatus() == "yes"){
                    continue;
                }
                if (order == 0) {
                    pane1.setVisible(true);
                    lblCustomer1.setText(CustomerModel.customerSearch(od.getCustId()).getName());
                    lblOrderDate1.setText("Date : "+od.getOrderDate());
                    order++;
                    continue;
                }
                if (order == 1) {
                    pane2.setVisible(true);
                    lblCustomer2.setText(CustomerModel.customerSearch(od.getCustId()).getName());
                    lblOrderDate2.setText("Date : "+od.getOrderDate());
                    order++;
                    continue;
                }
                if (order == 2) {
                    pane3.setVisible(true);
                    lblCustomer3.setText(CustomerModel.customerSearch(od.getCustId()).getName());
                    lblOrderDate3.setText("Date : "+od.getOrderDate());
                    order++;
                    continue;
                }
                if (order == 3) {
                    pane4.setVisible(true);
                    lblCustomer4.setText(CustomerModel.customerSearch(od.getCustId()).getName());
                    lblOrderDate4.setText("Date : "+od.getOrderDate());
                    order++;
                    continue;
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnFishSearchOnAction(ActionEvent event) {
        try {
            Fish fish = FishModel.fishSearch(txtFieldFishId.getText());
            txtFishCommanName.setText(fish.getFishCOMMONNAME());
            txtFishScientficName.setText(fish.getFishSCIENTIFICNAME());
            txtFishUnitPrice.setText(String.valueOf(fish.getUnitPrice()));
            txtFishonHand.setText(String.valueOf(fish.getFishOnHand()));

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "invalid ID").show();
        }
    }

    public void btnSaveOnAction(ActionEvent event) {

        if (txtFishCommanName.getText().matches(RegExPatterns.getNamePattern().pattern())&&
                txtFishScientficName.getText().matches(RegExPatterns.getNamePattern().pattern()) &&
                txtFishUnitPrice.getText().matches(RegExPatterns.getDoublePattern().pattern()) &&
                txtFishonHand.getText().matches(RegExPatterns.getIntPattern().pattern())
        ) {
            try {
                FishModel.fishSave(txtFieldFishId.getText(), txtFishCommanName.getText(), txtFishScientficName.getText(), Double.valueOf(txtFishUnitPrice.getText()), Integer.valueOf(txtFishonHand.getText()));
                new Alert(Alert.AlertType.CONFIRMATION, "Fish saved successfully ").show();
                generateNextFishId();
                txtFishCommanName.setText("");
                txtFishScientficName.setText("");
                txtFishUnitPrice.setText("");
                txtFishonHand.setText("");
                getAll();

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Fish not saved.").show();
            }
        }else new Alert(Alert.AlertType.ERROR,"Invalided Input").show();

    }

    public void btnDeleteOnAction(ActionEvent event) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to delete?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            try {
                FishModel.fishDelete(txtFieldFishId.getText());
                new Alert(Alert.AlertType.CONFIRMATION, "Fish deleted successfully").show();
                generateNextFishId();
                txtFishCommanName.setText("");
                txtFishScientficName.setText("");
                txtFishUnitPrice.setText("");
                txtFishonHand.setText("");
                getAll();

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Fish not deleted.").show();
            }
        }
    }


    public void btnUpdateOnAction(ActionEvent event) {

        if (txtFishCommanName.getText().matches(RegExPatterns.getNamePattern().pattern())&&
                txtFishScientficName.getText().matches(RegExPatterns.getNamePattern().pattern()) &&
                txtFishUnitPrice.getText().matches(RegExPatterns.getDoublePattern().pattern()) &&
                txtFishonHand.getText().matches(RegExPatterns.getIntPattern().pattern())
        ) {
            try {
                FishModel.fishUpdate(new Fish(txtFieldFishId.getText(), txtFishCommanName.getText(), txtFishScientficName.getText(), Double.valueOf(txtFishUnitPrice.getText()), Integer.valueOf(txtFishonHand.getText())));
                new Alert(Alert.AlertType.CONFIRMATION, "Fish Updated.").show();
                generateNextFishId();
                txtFishCommanName.setText("");
                txtFishScientficName.setText("");
                txtFishUnitPrice.setText("");
                txtFishonHand.setText("");
                getAll();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Fish Not Updated.").show();
            }
        }else new Alert(Alert.AlertType.ERROR,"Invalided Input").show();

    }
    public void visualize(){
        colFishId.setCellValueFactory(new PropertyValueFactory<FishTM,String>("fishID"));
        colFishCommonName.setCellValueFactory(new PropertyValueFactory<FishTM,String>("fishCommonName"));
        colFishScientificName.setCellValueFactory(new PropertyValueFactory<FishTM,String>("fishScientificName"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<FishTM,Double>("unitPrice"));
        colFishOnHand.setCellValueFactory(new PropertyValueFactory<FishTM,Integer>("fishOnHand"));
    }
    void getAll() {
        try {
            ObservableList<FishTM> obList = FXCollections.observableArrayList();
            List<Fish> fishList = FishModel.fishSearchAll();

            for (Fish fish : fishList) {
                obList.add(new FishTM(
                        fish.getFishId(),
                        fish.getFishCOMMONNAME(),
                        fish.getFishSCIENTIFICNAME(),
                        fish.getUnitPrice(),
                        fish.getFishOnHand()
                ));
            }
            tblFish.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }
}
