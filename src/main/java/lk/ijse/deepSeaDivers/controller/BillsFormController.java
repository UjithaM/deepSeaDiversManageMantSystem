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
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import lk.ijse.deepSeaDivers.RegExPattern.RegExPatterns;
import lk.ijse.deepSeaDivers.dto.Bill;
import lk.ijse.deepSeaDivers.dto.Order;
import lk.ijse.deepSeaDivers.dto.tm.BillTM;
import lk.ijse.deepSeaDivers.dto.tm.CustomerTM;
import lk.ijse.deepSeaDivers.model.BillModel;
import lk.ijse.deepSeaDivers.model.CustomerModel;
import lk.ijse.deepSeaDivers.model.FishSupplierModel;
import lk.ijse.deepSeaDivers.model.OrderModel;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BillsFormController implements Initializable {

    @FXML
    private TableView<BillTM> tblBll;
    @FXML
    private JFXToggleButton toggleButtonPaidStatus;
    @FXML
    private DatePicker dateBill;

    @FXML
    private JFXButton btnOwner;

    @FXML
    private JFXComboBox<String> cmbCustomerId;

    @FXML
    private TableColumn colBillDate;

    @FXML
    private TableColumn colBillId;

    @FXML
    private TableColumn colPaidStatus;

    @FXML
    private TableColumn colSumOfTheBill;

    @FXML
    private TableColumn colSupplierId;

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
    private Label lblSupplierName;

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
    private TextField txtFieldBll;

    @FXML
    private TextField txtFieldSumOfTheBill;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpcomingOrder();
        loadSuppliersId();
        datapickerFromatChange();
        generateNextOrderId();
        visualize();
        getAll();
    }
    private void generateNextOrderId() {
        try {
            txtFieldBll.setText(BillModel.generateNextBillId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void datapickerFromatChange() {
        dateBill.setConverter(new StringConverter<LocalDate>() {
            String pattern = "yyyy-MM-dd";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            {
                dateBill.setPromptText(pattern.toLowerCase());
            }

            @Override public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
    }

    private void loadSuppliersId() {
        try {
            List<String> ids = FishSupplierModel.getSupplierIds();
            ObservableList<String> obList = FXCollections.observableArrayList();

            for (String id : ids) {
                obList.add(id);
            }
            cmbCustomerId.setItems(obList);

        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
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
        try {
            lblSupplierName.setText("Supplier Name: " +FishSupplierModel.fishSupplierSearch(cmbCustomerId.getValue()).getSupplierName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnNewCustomerOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Customer_foam.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Customer");
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
    @FXML
    void btnAddXlFileOnAction(ActionEvent event)  {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog((Stage) root.getScene().getWindow());
        String filename = selectedFile.getName();
        String extion = filename.substring(filename.lastIndexOf(".") + 1, filename.length());

        if (!extion.equals("xls") || !extion.equals("xlsx")) {
            new Alert(Alert.AlertType.ERROR, "File type is incorrect. please Choose an excel file!.... ").show();
            return;
        }
        try{
            FileInputStream fis = new FileInputStream(new File(String.valueOf(selectedFile.getPath())));
            System.out.println(selectedFile.getPath());
            //creating workbook instance that refers to .xls file
            HSSFWorkbook wb = new HSSFWorkbook(fis);
             //creating a Sheet object to retrieve the object
            HSSFSheet sheet = wb.getSheetAt(0);
            //evaluating cell type
            FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
            for (Row row : sheet)     //iteration over row using for each loop
            {
                for (Cell cell : row)    //iteration over cell using for each loop
                {
                    switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:   //field that represents numeric cell type
                        //getting the value of the cell as a number
                            System.out.print(cell.getNumericCellValue() + "\t\t");
                            break;
                        case Cell.CELL_TYPE_STRING:    //field that represents string cell type
                            //getting the value of the cell as a string
                            System.out.print(cell.getStringCellValue() + "\t\t");
                            break;
                    }
                }
                System.out.println();
            }
        }catch (Exception e){

        }
        try {
            FileInputStream file = new FileInputStream(new File(String.valueOf(selectedFile.getPath())));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterable =  sheet.iterator();
            while (rowIterable.hasNext()){
                Row row = rowIterable.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while(cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()){
                        case Cell.CELL_TYPE_STRING:
                            System.out.print(cell.getStringCellValue()+ "\t");
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print(cell.getNumericCellValue()+ "\t");
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:
                            System.out.print(cell.getBooleanCellValue() + "\t");
                            break;
                    }
                    System.out.println();
                }
            }

        }catch (Exception s){
            new Alert(Alert.AlertType.ERROR, "File type is incorrect. please select type \"XLS\" file.... ").show();
        }
    }


    public void btnBillSearchOnAction(ActionEvent event) {
        try {
            Bill bill= BillModel.billSearch(txtFieldBll.getText());
            dateBill.setValue(LocalDate.parse(bill.getBillDate()));
            cmbCustomerId.setValue(bill.getSupplierId());
            lblSupplierName.setText("Supplier Name: " +FishSupplierModel.fishSupplierSearch(bill.getSupplierId()).getSupplierName());
            txtFieldSumOfTheBill.setText(String.valueOf(bill.getSumOfTheBill()));


            if (bill.getPaidStatus().equals("yes")) {
                toggleButtonPaidStatus.setSelected(true);
            }else toggleButtonPaidStatus.setSelected(false);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Invalid Customer Id").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent event) {
        if (txtFieldSumOfTheBill.getText().matches(RegExPatterns.getIntPattern().pattern())
        ) {
            try {
                BillModel.billUpdate(new Bill(txtFieldBll.getText(), String.valueOf(dateBill.getValue()), Double.valueOf(txtFieldSumOfTheBill.getText()), cmbCustomerId.getValue(),  toggleButtonPaidStatus.isSelected() == true ? "yes": "no"));
                new Alert(Alert.AlertType.CONFIRMATION, "Bill updated ").show();
                txtFieldBll.setText(BillModel.generateNextBillId());
                dateBill.setValue(null);
                txtFieldSumOfTheBill.setText("");
                cmbCustomerId.setValue(null);
                toggleButtonPaidStatus.setSelected(false);
            } catch (SQLException e) {
                new Alert (Alert.AlertType.ERROR, "Bill not Updated.").show();
            }
            getAll();
        }else new Alert(Alert.AlertType.ERROR,"Invalided Input").show();

    }

    public void btnDeleteOnAction(ActionEvent event) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to delete?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            try {
                BillModel.billDelete(txtFieldBll.getText());
                dateBill.setValue(null);
                txtFieldSumOfTheBill.setText("");
                cmbCustomerId.setValue(null);
                toggleButtonPaidStatus.setSelected(false);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "something went wrong");
            }
        }
        getAll();
    }

    public void btnSaveOnAction(ActionEvent event) {
        if (txtFieldSumOfTheBill.getText().matches(RegExPatterns.getIntPattern().pattern())
        ) {
            try {
                BillModel.billSave(txtFieldBll.getText(), String.valueOf(dateBill.getValue()), Double.valueOf(txtFieldSumOfTheBill.getText()), cmbCustomerId.getValue(), toggleButtonPaidStatus.isSelected() == true ? "yes": "no");
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Added Successfully").show();
                txtFieldBll.setText(BillModel.generateNextBillId());
                dateBill.setValue(null);
                txtFieldSumOfTheBill.setText("");
                cmbCustomerId.setValue(null);
                toggleButtonPaidStatus.setSelected(false);
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "customer Not Added").show();
            }
            getAll();
        }else new Alert(Alert.AlertType.ERROR,"Invalided Input").show();
    }


    public void visualize(){
        colBillId.setCellValueFactory(new PropertyValueFactory<BillTM,String>("billId"));
        colBillDate.setCellValueFactory(new PropertyValueFactory<BillTM,String>("billDate"));
        colSumOfTheBill.setCellValueFactory(new PropertyValueFactory<BillTM,Double>("sumOfTheBill"));
        colSupplierId.setCellValueFactory(new PropertyValueFactory<BillTM, String>("supplierID"));
        colPaidStatus.setCellValueFactory(new PropertyValueFactory<BillTM, String>("paidStatus"));
    }
    void getAll() {
        try {
            ObservableList<BillTM> obList = FXCollections.observableArrayList();
            List<Bill> billList = BillModel.billSearchAll();

            for(Bill bill : billList) {
                obList.add(new BillTM(
                        bill.getBillId(),
                        bill.getBillDate(),
                        bill.getSumOfTheBill(),
                        bill.getSupplierId(),
                        bill.getPaidStatus()
                ));
            }
            tblBll.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }
}
