package lk.ijse.orm.elite_driving_school_management_system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class AppInitializer extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppInitializer.class.getResource("/view/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Elite Driving School Management System");
        stage.setScene(scene);

        //   Start maximized (full screen window, not borderless)
        stage.setMaximized(true);

        //   Set minimum size so UI doesn't break if window is resized
        stage.setMinWidth(1100);
        stage.setMinHeight(700);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}