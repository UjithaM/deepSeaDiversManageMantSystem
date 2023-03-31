package lk.ijse.deepSeaDivers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

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