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
        //   The logout page is loaded INSIDE the dashboard's AnchorPane.
        // Closing the stage here would kill the entire app.
        // Instead, we just do nothing — the user can click another sidebar button to navigate away.
        // If you want to go back to a default page, trigger that from the dashboard instead.
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Cancelled");
        info.setHeaderText(null);
        info.setContentText("Logout cancelled. You are still logged in.");
        info.showAndWait();
    }

    @FXML
    void logoutOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
            Parent root = loader.load();

            //   Get the real top-level Stage window and replace its scene
            // This works whether logout is embedded inside a dashboard pane or standalone
            Stage stage = (Stage) idlogout.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Login - Elite Driving School");
            stage.setResizable(false);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to logout: " + e.getMessage()).showAndWait();
        }
    }
}