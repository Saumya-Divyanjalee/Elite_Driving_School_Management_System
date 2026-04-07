package lk.ijse.orm.elite_driving_school_management_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

import java.net.URL;
import java.util.ResourceBundle;

public class ReceptionistDashboard implements Initializable {

    public AnchorPane ancMainContainer;

    @FXML
    private Button lessonId;

    @FXML
    private Button logoutId;

    @FXML
    private Button paymentId;

    @FXML
    private Button studentId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //   Load Student page as the default landing page after receptionist login
        navigateTo("/view/Student.fxml");
    }

    //   Accepts full path now (consistent with AdminDashboard)
    private void navigateTo(String fxmlPath) {
        try {
            URL resource = getClass().getResource(fxmlPath);
            if (resource == null) {
                new Alert(Alert.AlertType.ERROR,
                        "Page not found: " + fxmlPath + "\nCheck that the file exists in /resources/view/").showAndWait();
                return;
            }

            FXMLLoader loader = new FXMLLoader(resource);
            Parent pane = loader.load();

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

    @FXML
    void lessonOnAction(ActionEvent event) {
        navigateTo("/view/Lesson.fxml");
    }

    @FXML
    void logoutOnAction(ActionEvent event) {
        navigateTo("/view/Logout.fxml");
    }

    @FXML
    void paymentOnAction(ActionEvent event) {
        navigateTo("/view/Payment.fxml");
    }

    @FXML
    void studentOnAction(ActionEvent event) {
        navigateTo("/view/Student.fxml");
    }
}