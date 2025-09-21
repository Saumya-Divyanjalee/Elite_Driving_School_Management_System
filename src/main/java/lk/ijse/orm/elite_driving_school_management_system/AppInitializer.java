package lk.ijse.orm.elite_driving_school_management_system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage primaryStage) throws IOException {
        URL resource = this.getClass().getResource("/view/Lesson.fxml");
        Parent load = FXMLLoader.load(resource);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("Elite Driving School System");
        Scene scene = new Scene(load);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();

    }

}
