package lk.ijse.deepSeaDivers.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.ijse.deepSeaDivers.db.DBConnection;
import lk.ijse.deepSeaDivers.dto.Customer;
import lk.ijse.deepSeaDivers.dto.Order;
import lk.ijse.deepSeaDivers.dto.tm.OrderTM;
import lk.ijse.deepSeaDivers.model.CustomerModel;
import lk.ijse.deepSeaDivers.model.OrderModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.xmlbeans.impl.soap.Detail;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class OrdersFoamController implements Initializable {

    @FXML
    private TableColumn<OrderTM, JFXButton> colDetails;

    @FXML
    private TableColumn<OrderTM, String> colCustomerID;

    @FXML
    private TableColumn<OrderTM, String> colCustomerName;

    @FXML
    private TableColumn<OrderTM, String> colOrderCompleteStatus;

    @FXML
    private TableColumn<OrderTM, String> colOrderDate;

    @FXML
    private TableColumn<OrderTM, String> colOrderID;
    @FXML
    private TableView<OrderTM> tblOrder;

    static String orderIdForDetails;
    @FXML
    private JFXButton btnOwner;

    @FXML
    private JFXComboBox<String> cmbCustomerId;

    @FXML
    private Label lblCustomer1;

    @FXML
    private Label lblCustomer2;

    @FXML
    private Label lblCustomer3;

    @FXML
    private Label lblCustomer4;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblOrderDate1;

    @FXML
    private Label lblOrderDate2;

    @FXML
    private Label lblOrderDate3;

    @FXML
    private Label lblOrderDate4;

    @FXML
    private Label lblOrderId;

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
    private JFXToggleButton toogleBtnOrderCompleted;

    @FXML
    private TextField txtOrderId;

    @FXML
    private TextField txtOrderDate;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateNextOrderId();
        setUpcomingOrder();
        loadCustomerIds();
        generateDate();
        visualize();
        getAll();

    }
    private void generateDate(){
        txtOrderDate.setText(String.valueOf(LocalDate.now()));
    }
    private void generateNextOrderId() {
        try {
            String nextId = OrderModel.generateNextOrderId();
            txtOrderId.setText(nextId);
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

    public void btnLabourIdSearchOnAction(ActionEvent event) {
        try {
            Order order=OrderModel.orderSearch(txtOrderId.getText());
            txtOrderDate.setText(order.getOrderDate());
            cmbCustomerId.setValue(order.getCustId());
            lblCustomerName.setText("Customer Name : "+CustomerModel.customerSearch(order.getCustId()).getName());
            toogleBtnOrderCompleted.setSelected(order.getOrderCompleteStatus() == "yes" ? true:false);

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid Order Id.").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent event) {
        try {
            OrderModel.orderUpdate(new Order(txtOrderId.getText(), txtOrderDate.getText(), toogleBtnOrderCompleted.isSelected() == true ? "yes":"no", cmbCustomerId.getValue() ));
            generateNextOrderId();
            generateDate();
            cmbCustomerId.setValue("");
            toogleBtnOrderCompleted.setSelected(false);
            new Alert(Alert.AlertType.CONFIRMATION, "Order updated successfully.").show();
            getAll();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "order Not updated").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent event) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to delete?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            try {
                OrderModel.orderDelete(txtOrderId.getText());
                generateNextOrderId();
                generateDate();
                cmbCustomerId.setValue("");
                toogleBtnOrderCompleted.setSelected(false);
                new Alert(Alert.AlertType.CONFIRMATION, "Order Deleted successfully").show();
                getAll();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Order Not Deleted").show();
            }
        }
    }

    public void btnSaveOnAction(ActionEvent event) {
        try {
            OrderModel.orderSave(txtOrderId.getText(), txtOrderDate.getText(), toogleBtnOrderCompleted.isSelected() == true ? "yes":"no", cmbCustomerId.getValue() );
            generateNextOrderId();
            generateDate();
            cmbCustomerId.setValue("");
            toogleBtnOrderCompleted.setSelected(false);
            new Alert(Alert.AlertType.CONFIRMATION, "Order save successfully").show();
            getAll();
        } catch (SQLException e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "order Not Save successfully").show();
        }
    }private void loadCustomerIds() {
        try {
            List<Customer> ids = CustomerModel.customerSearchAll();
            ObservableList<String> obList = FXCollections.observableArrayList();

            for (Customer customer : ids) {
                obList.add(customer.getId());
            }
            cmbCustomerId.setItems(obList);

        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    public void btnOrderDetailsOnAction(ActionEvent event) {
        orderIdForDetails = txtOrderId.getText();
        try {
            Parent adad = FXMLLoader.load(getClass().getResource("/view/OrderDetails.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Order Details");
            stage.centerOnScreen();
            stage.setScene(new Scene(adad));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void visualize(){
        colOrderID.setCellValueFactory(new PropertyValueFactory<OrderTM,String>("orderId"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<OrderTM,String>("orderDate"));
        colOrderCompleteStatus.setCellValueFactory(new PropertyValueFactory<OrderTM,String>("orderCompleteStatus"));
        colCustomerID.setCellValueFactory(new PropertyValueFactory<OrderTM,String>("customerId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<OrderTM,String>("customerName"));
        colDetails.setCellValueFactory(new PropertyValueFactory<OrderTM,JFXButton>("orderDetails"));
    }
    void getAll() {
        try {
            ObservableList<OrderTM> obList = FXCollections.observableArrayList();
            List<Order> orderList = OrderModel.orderList();

            for (Order order : orderList) {
                JFXButton button = new JFXButton("Details");
                button.setStyle("-fx-background-color:  linear-gradient(to right, #4e54c8, #8f94fb)");
                setDetailsBtnOnAction(button);
                obList.add(new OrderTM(
                        order.getOrderId(),
                        order.getOrderDate(),
                        order.getOrderCompleteStatus(),
                        order.getCustId(),
                        CustomerModel.customerSearch(order.getCustId()).getName(),
                        button
                ));
            }
            tblOrder.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }

    private void setDetailsBtnOnAction(JFXButton button) {
        button.setOnAction((e) -> {
            orderIdForDetails = txtOrderId.getText();
            try {
                Parent adad = FXMLLoader.load(getClass().getResource("/view/OrderDetails.fxml"));

                Stage stage = new Stage();
                stage.setTitle("Order Details");
                stage.centerOnScreen();
                stage.setScene(new Scene(adad));
                stage.show();
            } catch (IOException action) {
                throw new RuntimeException(action);
            }
        });
    }

    public void btnReportOnAction(ActionEvent event)  {
        try {
            JasperDesign jasperDesign = JRXmlLoader.load("D:\\DeepSeaDiversManagmentSystem\\src\\main\\resources\\Reports\\Order.jrxml");
            String sql = "SELECT * FROM orders";

            JRDesignQuery updateQuary = new JRDesignQuery();
            updateQuary.setText(sql);

            jasperDesign.setQuery(updateQuary);

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());

            JasperViewer.viewReport(jasperPrint);
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
