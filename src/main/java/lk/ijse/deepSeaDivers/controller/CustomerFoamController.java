package lk.ijse.deepSeaDivers.controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ObservableValue;
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
import javafx.util.Callback;
import lk.ijse.deepSeaDivers.RegExPattern.RegExPatterns;
import lk.ijse.deepSeaDivers.dto.Customer;
import lk.ijse.deepSeaDivers.dto.Order;
import lk.ijse.deepSeaDivers.dto.tm.CustomerTM;
import lk.ijse.deepSeaDivers.model.CustomerModel;
import lk.ijse.deepSeaDivers.model.OrderModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerFoamController implements Initializable {
    public TableColumn colCustomerId;
    public TableColumn colCustomerName;
    public TableColumn colCustomerAddress;
    public TableColumn colCustomerEmail;
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
    private TextField txtFieldCustomerAddress;

    @FXML
    private TextField txtFieldCustomerEmail;

    @FXML
    private TextField txtFieldCustomerName;

    @FXML
    private TextField txtFieldCustomer;

    @FXML
    private TableView<CustomerTM> tblCustomer;




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
        generateNextCustomerId();
        getAll();
        visualize();
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
    private void generateNextCustomerId() {
        try {
            String nextId = CustomerModel.generateNextCustomerId();
            txtFieldCustomer.setText(nextId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnUpdateOnAction(ActionEvent event)  {
        if (txtFieldCustomerName.getText().matches(RegExPatterns.getNamePattern().pattern()) &&
                txtFieldCustomerEmail.getText().matches(RegExPatterns.getEmailPattern().pattern()) &&
                txtFieldCustomerAddress.getText().matches(RegExPatterns.getCityPattern().pattern())
        ) {
            try {
                CustomerModel.customerUpdate(new Customer(txtFieldCustomer.getText(), txtFieldCustomerName.getText(), txtFieldCustomerAddress.getText(), txtFieldCustomerEmail.getText()));
                new Alert(Alert.AlertType.CONFIRMATION).show();
                txtFieldCustomerName.setText("");
                txtFieldCustomerAddress.setText("");
                txtFieldCustomerEmail.setText("");
                getAll();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Customer Not Updated").show();
            }
            getAll();
        }else new Alert(Alert.AlertType.ERROR,"Invalided Input").show();

    }
    @FXML
    void btnDeleteOnAction(ActionEvent actionEvent) {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Delete?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {

                try {
                    CustomerModel.customerDelete(txtFieldCustomer.getText());
                    txtFieldCustomer.setText(CustomerModel.generateNextCustomerId());
                    txtFieldCustomerName.setText("");
                    txtFieldCustomerAddress.setText("");
                    txtFieldCustomerEmail.setText("");
                    getAll();
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer deleted successfully").show();

                } catch (SQLException a) {
                    new Alert(Alert.AlertType.ERROR, "Customer Not Deleted").show();
                }
            }
    }
    @FXML
    void btnCustomerSaveOnAction(ActionEvent event) {
        if (txtFieldCustomerName.getText().matches(RegExPatterns.getNamePattern().pattern()) &&
                txtFieldCustomerEmail.getText().matches(RegExPatterns.getEmailPattern().pattern()) &&
                txtFieldCustomerAddress.getText().matches(RegExPatterns.getCityPattern().pattern())
        ) {
            try {
                CustomerModel.customerSave(txtFieldCustomer.getText(), txtFieldCustomerName.getText(), txtFieldCustomerAddress.getText(), txtFieldCustomerEmail.getText());
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Added Successfully").show();
                txtFieldCustomer.setText(CustomerModel.generateNextCustomerId());
                txtFieldCustomerName.setText("");
                txtFieldCustomerAddress.setText("");
                txtFieldCustomerEmail.setText("");
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "customer Not Added").show();
            }
            getAll();
        }else new Alert(Alert.AlertType.ERROR,"Invalided Input").show();

    }
    @FXML
    void btnCustomerSearchOnAction(ActionEvent event) {
        try {
            Customer customer= CustomerModel.customerSearch(txtFieldCustomer.getText());
            txtFieldCustomerName.setText(customer.getName());
            txtFieldCustomerAddress.setText(customer.getAddress());
            txtFieldCustomerEmail.setText(customer.getEmail());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Invalid Customer Id").show();
        }
    }
    void getAll() {
        try {
            ObservableList<CustomerTM> obList = FXCollections.observableArrayList();
            List<Customer> cusList = CustomerModel.customerSearchAll();

            for(Customer customer : cusList) {
                obList.add(new CustomerTM(
                        customer.getId(),
                        customer.getName(),
                        customer.getAddress(),
                        customer.getEmail()
                ));
            }
            tblCustomer.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }

    public void visualize(){
        colCustomerId.setCellValueFactory(new PropertyValueFactory<CustomerTM,String>("custId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<CustomerTM,String>("custName"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<CustomerTM,String>("custAddress"));
        colCustomerEmail.setCellValueFactory(new PropertyValueFactory<CustomerTM, String>("custEmail"));

    }
}
