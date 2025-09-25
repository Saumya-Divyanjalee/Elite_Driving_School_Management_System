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
        navigateTo("/lk.ijse.orm.elite_driving_school_management_system.resources/view/StudentDashboard.fxml");

    }

    public void navigateTo(String x){
        try{
            ancMainContainer.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(x));

            if(pane instanceof Region){
                Region region = (Region)pane;
                region.prefWidthProperty().bind(ancMainContainer.widthProperty());
                region.prefHeightProperty().bind(ancMainContainer.heightProperty());
            }
            ancMainContainer.getChildren().add(pane);

        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR,"Oops! Page Not Found").show();
            e.printStackTrace();
        }

    }
    @FXML
    void lessonOnAction(ActionEvent event) {
        navigateTo("view/Lesson.fxml");

    }

    @FXML
    void logoutOnAction(ActionEvent event) {
        navigateTo("view/Logout.fxml");

    }

    @FXML
    void paymentOnAction(ActionEvent event) {
        navigateTo("view/Payment.fxml");

    }

    @FXML
    void studentOnAction(ActionEvent event) {
        navigateTo("view/Student.fxml");

    }

}
