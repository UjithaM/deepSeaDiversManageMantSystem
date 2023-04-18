package lk.ijse.deepSeaDivers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lk.ijse.deepSeaDivers.db.DBConnection;
import lk.ijse.deepSeaDivers.dto.Fish;
import lk.ijse.deepSeaDivers.model.FishModel;
import lk.ijse.deepSeaDivers.model.PlaceOrder;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Dashboard_form.fxml"));
        stage.setTitle("Dashboard");    
        stage.centerOnScreen();
        stage.setScene(new Scene(root));

        stage.show();
    }
}
