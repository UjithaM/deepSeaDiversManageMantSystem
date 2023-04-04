package lk.ijse.deepSeaDivers.controller;

import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.deepSeaDivers.dto.tm.PlaceOrderTM;
import lk.ijse.deepSeaDivers.model.OrderModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class OrdersFoamController implements Initializable {

    @FXML
    private TableView<PlaceOrderTM> tblOrder;
    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblOrderId;
    @FXML
    private AnchorPane root;

    @FXML
    private JFXToggleButton toogleBtnOrderCompleted;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateNextOrderId();
        lblDate.setText(String.valueOf("Date : "+LocalDate.now()));
    }
    private void generateNextOrderId() {
        try {
            String nextId = OrderModel.generateNextOrderId();
            lblOrderId.setText("Order Id : " + nextId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


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

    @FXML
    void cmbCustomerOnAction(ActionEvent event) {

    }

    public void btnNewCustomerOnAction(ActionEvent event) {

    }
}
