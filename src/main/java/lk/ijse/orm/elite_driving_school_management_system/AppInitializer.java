package lk.ijse.orm.elite_driving_school_management_system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage primaryStage) throws Exception {
        URL resource = this.getClass().getResource("view/Login.fxml");
        Parent load = FXMLLoader.load(resource);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("Elite Driving School System");
        primaryStage.getIcons().add(new Image("assets/logo.png"));
        Scene scene = new Scene(load);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();

    }

}
