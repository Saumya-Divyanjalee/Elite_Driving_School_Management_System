package lk.ijse.orm.elite_driving_school_management_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminDashboard implements Initializable {

    public Button userId;
    public Button instructorId;
    public Button courseId;
    public Button studentId;
    public Button lessonId;
    public Button paymentId;
    public Button logoutId;
    public AnchorPane ancMainContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //  Load Student page as the default landing page after admin login
        navigateTo("/view/Student.fxml");
    }

    private void navigateTo(String fxmlPath) {
        try {
            //   Check that the resource actually exists before loading
            URL resource = getClass().getResource(fxmlPath);
            if (resource == null) {
                new Alert(Alert.AlertType.ERROR,
                        "Page not found: " + fxmlPath + "\nCheck that the file exists in /resources/view/").showAndWait();
                return;
            }

            FXMLLoader loader = new FXMLLoader(resource);
            Parent pane = loader.load();

            //  Bind the loaded page to fill the main content area
            if (pane instanceof Region) {
                Region region = (Region) pane;
                region.prefWidthProperty().bind(ancMainContainer.widthProperty());
                region.prefHeightProperty().bind(ancMainContainer.heightProperty());
            }

            ancMainContainer.getChildren().clear();
            ancMainContainer.getChildren().add(pane);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,
                    "Failed to load page: " + fxmlPath + "\nError: " + e.getMessage()).showAndWait();
        }
    }

    public void userOnAction(ActionEvent actionEvent) {
        navigateTo("/view/User-Role.fxml");
    }

    public void instructorOnAction(ActionEvent actionEvent) {
        navigateTo("/view/Instructor.fxml");
    }

    public void courseOnAction(ActionEvent actionEvent) {
        navigateTo("/view/Course.fxml");
    }

    public void studentOnAction(ActionEvent actionEvent) {
        navigateTo("/view/Student.fxml");
    }

    public void lessonOnAction(ActionEvent actionEvent) {
        navigateTo("/view/Lesson.fxml");
    }

    public void paymentOnAction(ActionEvent actionEvent) {
        navigateTo("/view/Payment.fxml");
    }

    public void logoutOnAction(ActionEvent actionEvent) {
        navigateTo("/view/Logout.fxml");
    }
}