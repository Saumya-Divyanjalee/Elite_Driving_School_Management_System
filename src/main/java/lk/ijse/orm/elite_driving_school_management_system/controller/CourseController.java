package lk.ijse.orm.elite_driving_school_management_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CourseController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnMail;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colCourseID;

    @FXML
    private TableColumn<?, ?> colCourseName;

    @FXML
    private TableColumn<?, ?> colInstructorID;

    @FXML
    private TableColumn<?, ?> colLessonID;

    @FXML
    private TableColumn<?, ?> colStudentID;

    @FXML
    private TableColumn<?, ?> colTimePeriod;

    @FXML
    private Label lblCourseID;

    @FXML
    private Label lblInstructorID;

    @FXML
    private Label lblLessonID;

    @FXML
    private Label lblStudentID;

    @FXML
    private TableView<?> tblCourse;

    @FXML
    private TextField txtCourseName;

    @FXML
    private TextField txtTimePeriod;

    @FXML
    void deleteOnAction(ActionEvent event) {

    }

    @FXML
    void mailOnAction(ActionEvent event) {

    }

    @FXML
    void onClickTable(MouseEvent event) {

    }

    @FXML
    void resetOnAction(ActionEvent event) {

    }

    @FXML
    void saveOnAction(ActionEvent event) {

    }

    @FXML
    void updateOnAction(ActionEvent event) {

    }

}
