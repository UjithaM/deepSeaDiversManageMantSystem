package lk.ijse.deepSeaDivers.controller;

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
import lk.ijse.deepSeaDivers.dto.Labour;
import lk.ijse.deepSeaDivers.dto.Order;
import lk.ijse.deepSeaDivers.dto.tm.LabourTM;
import lk.ijse.deepSeaDivers.model.CustomerModel;
import lk.ijse.deepSeaDivers.model.LabourModel;
import lk.ijse.deepSeaDivers.model.OrderModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class LaborsFoamController implements Initializable {

    @FXML
    private TableColumn<LabourTM, String> colLabourAddress;

    @FXML
    private TableColumn<LabourTM, String> colLabourContactNumber;

    @FXML
    private TableColumn<LabourTM, String> colLabourID;

    @FXML
    private TableColumn<LabourTM, String> colLabourName;

    @FXML
    private TableView<LabourTM> tblLabour;

    @FXML
    private TextField txtLabourAddress;

    @FXML
    private TextField txtLabourContactNumber;

    @FXML
    private TextField txtLabourId;

    @FXML
    private TextField txtLabourName;

    @FXML
    private Pane pane1;

    @FXML
    private Pane pane2;

    @FXML
    private Pane pane3;

    @FXML
    private Pane pane4;
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
    private AnchorPane root;

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
        generateNextLabourId();
        visualize();
        getAll();
    }
    private void generateNextLabourId() {
        try {
            String nextId = LabourModel.generateNextLabourID();
            txtLabourId.setText(nextId);
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

        if (txtLabourName.getText().matches(RegExPatterns.getNamePattern().pattern()) &&
                txtLabourAddress.getText().matches(RegExPatterns.getCityPattern().pattern()) &&
                txtLabourContactNumber.getText().matches(RegExPatterns.getMobilePattern().pattern())
        ) {
            try {
                LabourModel.labourUpdate(new Labour(txtLabourId.getText(), txtLabourName.getText(), txtLabourAddress.getText(), txtLabourContactNumber.getText()));
                new Alert(Alert.AlertType.CONFIRMATION, "Labour Updated successfully").show();
                txtLabourName.setText("");
                txtLabourAddress.setText("");
                txtLabourContactNumber.setText("");
                generateNextLabourId();
                getAll();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Labour not Updated.").show();
            }

        }else new Alert(Alert.AlertType.ERROR,"Invalided Input").show();
    }

    public void btnDeleteOnAction(ActionEvent event) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to delete?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
             try {
                 LabourModel.labourDelete(txtLabourId.getText());
                 txtLabourName.setText("");
                 txtLabourAddress.setText("");
                 txtLabourContactNumber.setText("");
                 new Alert(Alert.AlertType.CONFIRMATION, "Labour deleted successfully").show();
                 generateNextLabourId();
                 getAll();
             } catch (SQLException e) {
                 new Alert(Alert.AlertType.ERROR, "Labour not deleted").show();
             }
        }
    }

    public void btnSaveOnAction(ActionEvent event) {

        if (txtLabourName.getText().matches(RegExPatterns.getNamePattern().pattern()) &&
                txtLabourAddress.getText().matches(RegExPatterns.getCityPattern().pattern()) &&
                txtLabourContactNumber.getText().matches(RegExPatterns.getMobilePattern().pattern())
        ) {try {
            LabourModel.labourSave(txtLabourId.getText(), txtLabourName.getText(), txtLabourAddress.getText(), txtLabourContactNumber.getText());
            new Alert(Alert.AlertType.CONFIRMATION, "Labour Added successfully").show();
            txtLabourName.setText("");
            txtLabourAddress.setText("");
            txtLabourContactNumber.setText("");
            generateNextLabourId();
            getAll();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Labour Not Saved").show();
        }
        }else new Alert(Alert.AlertType.ERROR,"Invalided Input").show();
    }

    public void btnLabourIdSearchOnAction(ActionEvent event) {
        try {
            Labour labour= LabourModel.labourSearch(txtLabourId.getText());
            txtLabourContactNumber.setText(labour.getLabourContactNumber());
            txtLabourName.setText(labour.getLabourName());
            txtLabourAddress.setText(labour.getLabourAddress());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
        }
    }
    public void visualize(){
        colLabourID.setCellValueFactory(new PropertyValueFactory<LabourTM,String>("labourID"));
        colLabourName.setCellValueFactory(new PropertyValueFactory<LabourTM,String>("labourName"));
        colLabourAddress.setCellValueFactory(new PropertyValueFactory<LabourTM,String>("labourAddress"));
        colLabourContactNumber.setCellValueFactory(new PropertyValueFactory<LabourTM,String>("labourContactNumber"));
    }
    void getAll() {
        try {
            ObservableList<LabourTM> obList = FXCollections.observableArrayList();
            List<Labour> labourList = LabourModel.labourSearchAll();

            for (Labour labour : labourList) {
                obList.add(new LabourTM(
                        labour.getLabourId(),
                        labour.getLabourName(),
                        labour.getLabourAddress(),
                        labour.getLabourContactNumber()
                ));
            }
            tblLabour.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }
}
