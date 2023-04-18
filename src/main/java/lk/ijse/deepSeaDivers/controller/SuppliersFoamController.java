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
import lk.ijse.deepSeaDivers.dto.Fishsupplier;
import lk.ijse.deepSeaDivers.dto.Order;
import lk.ijse.deepSeaDivers.dto.tm.FishSupplierTM;
import lk.ijse.deepSeaDivers.model.CustomerModel;
import lk.ijse.deepSeaDivers.model.FishSupplierModel;
import lk.ijse.deepSeaDivers.model.LabourModel;
import lk.ijse.deepSeaDivers.model.OrderModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class SuppliersFoamController implements Initializable {
    @FXML
    private JFXButton btnOwner;

    @FXML
    private TableColumn<FishSupplierTM, String> colSupplierAddress;

    @FXML
    private TableColumn<FishSupplierTM, String> colSupplierBankNumber;

    @FXML
    private TableColumn<FishSupplierTM, String> colSupplierContactNumber;

    @FXML
    private TableColumn<FishSupplierTM, String> colSupplierId;

    @FXML
    private TableColumn<FishSupplierTM, String> colSupplierName;

    @FXML
    private Label lblCustomer1;

    @FXML
    private Label lblCustomer2;

    @FXML
    private Label lblCustomer3;

    @FXML
    private Label lblCustomer4;

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
    private TableView<FishSupplierTM> tblSupplier;

    @FXML
    private TextField txtSupplierAddress;

    @FXML
    private TextField txtSupplierBankNumber;

    @FXML
    private TextField txtSupplierContactNumber;

    @FXML
    private TextField txtSupplierId;

    @FXML
    private TextField txtSupplierName;

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
        try {
            Fishsupplier fishsupplier = FishSupplierModel.fishSupplierSearch(txtSupplierId.getText());
            txtSupplierName.setText(fishsupplier.getSupplierName());
            txtSupplierAddress.setText(fishsupplier.getSupplierAddress());
            txtSupplierContactNumber.setText(fishsupplier.getSupplierContactNumber());
            txtSupplierBankNumber.setText(fishsupplier.getSupplierBankNumber());

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Quarry error");
        }
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
        visualize();
        getAll();
        generateNextLabourId();
    }
    private void generateNextLabourId() {
        try {
            String nextId = FishSupplierModel.generateNextFishId();
            txtSupplierId.setText(nextId);
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

    public void btnUpdateOnAction(ActionEvent event) {
        if (txtSupplierName.getText().matches(RegExPatterns.getNamePattern().pattern())&&
                txtSupplierAddress.getText().matches(RegExPatterns.getCityPattern().pattern())&&
                txtSupplierContactNumber.getText().matches(RegExPatterns.getMobilePattern().pattern())&&
                txtSupplierBankNumber.getText().matches(RegExPatterns.getIntPattern().pattern())
        ) {  try {
            FishSupplierModel.fishSupplierUpdate(new Fishsupplier(txtSupplierId.getText(), txtSupplierName.getText(), txtSupplierAddress.getText(), txtSupplierContactNumber.getText() , txtSupplierBankNumber.getText()));
            new Alert(Alert.AlertType.CONFIRMATION, "Supplier Updated").show();
            txtSupplierName.setText("");
            txtSupplierAddress.setText("");
            txtSupplierBankNumber.setText("");
            txtSupplierContactNumber.setText("");
            getAll();
            generateNextLabourId();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Supplier Not updated").show();
        }
        }else new Alert(Alert.AlertType.ERROR,"Invalided Input").show();


    }

    public void btnDeleteOnAction(ActionEvent event) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to delete?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            try {
                FishSupplierModel.fishSupplierDelete(txtSupplierId.getText());
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Deleted Successfully").show();
                txtSupplierName.setText("");
                txtSupplierAddress.setText("");
                txtSupplierBankNumber.setText("");
                txtSupplierContactNumber.setText("");
                getAll();

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Supplier not deleted").show();
            }
        }
        generateNextLabourId();
    }

    public void btnSaveOnAction(ActionEvent event) {
        if (txtSupplierName.getText().matches(RegExPatterns.getNamePattern().pattern())&&
                txtSupplierAddress.getText().matches(RegExPatterns.getCityPattern().pattern())&&
                txtSupplierContactNumber.getText().matches(RegExPatterns.getMobilePattern().pattern())&&
                txtSupplierBankNumber.getText().matches(RegExPatterns.getIntPattern().pattern())
        ) {
            try {
                FishSupplierModel.fishSupplierSave(txtSupplierId.getText(), txtSupplierName.getText(), txtSupplierAddress.getText(), txtSupplierContactNumber.getText() , txtSupplierBankNumber.getText());
                new Alert(Alert.AlertType.CONFIRMATION, "Fish Supplier Saved Successfully").show();
                txtSupplierName.setText("");
                txtSupplierAddress.setText("");
                txtSupplierBankNumber.setText("");
                txtSupplierContactNumber.setText("");
                getAll();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Fish Supplier not Saved").show();
            }
            generateNextLabourId();
        }else new Alert(Alert.AlertType.ERROR,"Invalided Input").show();

    }
    public void visualize(){
        colSupplierId.setCellValueFactory(new PropertyValueFactory<FishSupplierTM,String>("supplierId"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<FishSupplierTM,String>("supplierName"));
        colSupplierAddress.setCellValueFactory(new PropertyValueFactory<FishSupplierTM,String>("supplierAddress"));
        colSupplierContactNumber.setCellValueFactory(new PropertyValueFactory<FishSupplierTM,String>("supplierContactNumber"));
        colSupplierBankNumber.setCellValueFactory(new PropertyValueFactory<FishSupplierTM,String>("supplierBankNumber"));

    }
    void getAll() {
        try {
            ObservableList<FishSupplierTM> obList = FXCollections.observableArrayList();
            List<Fishsupplier> fishsupplierList = FishSupplierModel.fishSupplierSearchAll();

            for (Fishsupplier fishsupplier : fishsupplierList) {
                obList.add(new FishSupplierTM(
                        fishsupplier.getSupplierId(),
                        fishsupplier.getSupplierName(),
                        fishsupplier.getSupplierAddress(),
                        fishsupplier.getSupplierContactNumber(),
                        fishsupplier.getSupplierBankNumber()
                ));
            }
            tblSupplier.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }
}
