package lk.ijse.deepSeaDivers.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;

public class DashboardFormController {

    @FXML
    private JFXButton btnOwner;
    @FXML
    private JFXButton btnBillsHaveToPaid;

    @FXML
    private JFXButton btnFishCountOnHand;

    @FXML
    private JFXButton btnOrdersCompleted;
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
    private LineChart<?, ?> lineChart;

    @FXML
    void btnBillsOnAction(ActionEvent event) {
        System.out.println("Bills");
    }

    @FXML
    void btnCustomersOnAction(ActionEvent event) {
        System.out.println("Custoer");
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {
        System.out.println("das");
    }

    @FXML
    void btnDriversOnAction(ActionEvent event) {
        System.out.println("drive");
    }

    @FXML
    void btnFishStockOnAction(ActionEvent event) {
        System.out.println("Fish");
    }

    @FXML
    void btnLaborsOnAction(ActionEvent event) {
        System.out.println("Labor");
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) {
        System.out.println("log");
    }

    @FXML
    void btnOrdersOnAction(ActionEvent event) {
        System.out.println("order");
    }

    @FXML
    void btnSuppliersOnAction(ActionEvent event) {
        System.out.println("su[[[[[");
    }
    @FXML
    void btnBillsHaveToPaidOnAction(ActionEvent event) {

    }
    @FXML
    void btnFishCountOnHandOnAction(ActionEvent event) {

    }
    @FXML
    void btnOrderCompletedCountOnAction(ActionEvent event) {

    }
    @FXML
    void btnSeeDetails1OnAction(ActionEvent event) {

    }

    @FXML
    void btnSeeDetails2OnAction(ActionEvent event) {

    }

    @FXML
    void btnSeeDetails3OnAction(ActionEvent event) {

    }

    @FXML
    void btnSeeDetails4OnAction(ActionEvent event) {

    }

    @FXML
    void btnOwnerOnAction(ActionEvent event) {

    }
}
