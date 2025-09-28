package lk.ijse.orm.elite_driving_school_management_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

import java.net.URL;
import java.util.ResourceBundle;

public class ReceptionistDashboard  implements Initializable {


    public AnchorPane ancMainContainer;
    @FXML
    private AnchorPane ancpanal;

    @FXML
    private Button lessonId;

    @FXML
    private Button logoutId;

    @FXML
    private Button paymentId;

    @FXML
    private Button studentId;


    @Override
    public void initialize(URL location, ResourceBundle  resources) {
        // Don't load any initial page, let the user select what they want to view
        // The dashboard should start empty and load pages on demand
    }

    public void navigateTo(String x){
        try{
            ancMainContainer.getChildren().clear();
            // Fix the path by adding the leading slash and using the correct format
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/" + x));

            if(pane instanceof Region){
                Region region = (Region)pane;
                region.prefWidthProperty().bind(ancMainContainer.widthProperty());
                region.prefHeightProperty().bind(ancMainContainer.heightProperty());
            }
            ancMainContainer.getChildren().add(pane);

        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR,"Oops! Page Not Found: " + x).show();
            e.printStackTrace();
        }

    }

    @FXML
    void lessonOnAction(ActionEvent event) {
        navigateTo("Lesson.fxml");
    }

    @FXML
    void logoutOnAction(ActionEvent event) {
        navigateTo("Logout.fxml");
    }

    @FXML
    void paymentOnAction(ActionEvent event) {
        navigateTo("Payment.fxml");
    }

    @FXML
    void studentOnAction(ActionEvent event) {
        navigateTo("Student.fxml");
    }

}
