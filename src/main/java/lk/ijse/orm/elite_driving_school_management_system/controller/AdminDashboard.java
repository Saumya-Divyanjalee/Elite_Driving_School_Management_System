package lk.ijse.orm.elite_driving_school_management_system.controller;

import javafx.event.ActionEvent;
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

public class AdminDashboard implements Initializable {
    public Button userId;
    public Button instructorId;
    public Button courseId;
    public Button studentId;
    public Button lessonId;
    public Button paymentId;
    public Button secureId;
    public Button logoutId;
    public AnchorPane ancMainContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        navigateTo("/views/lesson.fxml");
    }

    private void navigateTo(String s){
        try{
            ancMainContainer.getChildren().clear();
            Parent pane = FXMLLoader.load(getClass().getResource(s));

            if(pane instanceof Region){
                Region region = (Region)pane;
                region.prefWidthProperty().bind(ancMainContainer.widthProperty());
                region.prefHeightProperty().bind(ancMainContainer.heightProperty());
            }
            ancMainContainer.getChildren().add(pane);
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR,"Ooops! Page Not Found").show();
            e.printStackTrace();
        }
    }
    public void userOnAction(ActionEvent actionEvent) {
        navigateTo("/views/User.fxml");
    }
    public void instructorOnAction(ActionEvent actionEvent) {
        navigateTo("/views/Instructor.fxml");
    }
    public void courseOnAction(ActionEvent actionEvent) {
        navigateTo("/views/Course.fxml");
    }
    public void studentOnAction(ActionEvent actionEvent) {
        navigateTo("/views/Student.fxml");
    }
    public void lessonOnAction(ActionEvent actionEvent) {
        navigateTo("/views/Lesson.fxml");
    }
    public void paymentOnAction(ActionEvent actionEvent) {
        navigateTo("/views/Payment.fxml");
    }
    public void secureOnAction(ActionEvent actionEvent) {
        navigateTo("/views/Secure.fxml");
    }
    public void logoutOnAction(ActionEvent actionEvent) {
        navigateTo("/views/Logout.fxml");
    }
}
