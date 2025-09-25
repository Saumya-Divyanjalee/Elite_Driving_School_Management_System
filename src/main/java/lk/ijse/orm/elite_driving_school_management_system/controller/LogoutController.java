package lk.ijse.orm.elite_driving_school_management_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LogoutController {

    @FXML
    private Button idCancel;

    @FXML
    private Button idlogout;

    @FXML
    void cancelOnAction(ActionEvent event) {
        Stage stage = (Stage) idCancel.getScene().getWindow();
        stage.close();

    }

    @FXML
    void logoutOnAction(ActionEvent event) {

        try {
            // Close the current window
            Stage stage = (Stage) idlogout.getScene().getWindow();
            stage.close();

            // Load login window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
            Parent root = loader.load();

            Stage loginStage = new Stage();
            loginStage.setTitle("Login");
            loginStage.setScene(new Scene(root));
            loginStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to logout!").show();
        }
    }

}
