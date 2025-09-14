package lk.ijse.orm.elite_driving_school_management_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.orm.elite_driving_school_management_system.tm.CourseTM;

import java.net.URL;
import java.util.ResourceBundle;

public class CourseController implements Initializable {

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
    private TableColumn<CourseTM,String> colCourseID;

    @FXML
    private TableColumn<CourseTM,String> colCourseName;

    @FXML
    private TableColumn<CourseTM,String> colInstructorID;

    @FXML
    private TableColumn<CourseTM,String> colLessonID;

    @FXML
    private TableColumn<CourseTM,String> colStudentID;

    @FXML
    private TableColumn<CourseTM,String> colTimePeriod;

    @FXML
    private Label lblCourseID;

    @FXML
    private Label lblInstructorID;

    @FXML
    private Label lblLessonID;

    @FXML
    private Label lblStudentID;

    @FXML
    private TableView<CourseTM> tblCourse;

    @FXML
    private TextField txtCourseName;

    @FXML
    private TextField txtTimePeriod;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colCourseID.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colTimePeriod.setCellValueFactory(new PropertyValueFactory<>("timePeriod"));
        colLessonID.setCellValueFactory(new PropertyValueFactory<>("lessonID"));
        colInstructorID.setCellValueFactory(new PropertyValueFactory<>("instructorID"));
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));

        try{
            resetPage();
        }
    }


    private  void resetPage(){
        loadNextId();
        loadTableData();
    }

    private void loadNextId(){
        String nextId = "
    }

    @FXML
    void deleteOnAction(ActionEvent event) {

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
