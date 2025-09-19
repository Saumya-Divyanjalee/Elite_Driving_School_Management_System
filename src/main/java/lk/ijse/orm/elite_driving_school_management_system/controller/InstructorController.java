package lk.ijse.orm.elite_driving_school_management_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.orm.elite_driving_school_management_system.bo.BOFactory;
import lk.ijse.orm.elite_driving_school_management_system.bo.BoTypes;
import lk.ijse.orm.elite_driving_school_management_system.bo.custom.InstructorBO;
import lk.ijse.orm.elite_driving_school_management_system.dto.InstructorDTO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Instructor;
import lk.ijse.orm.elite_driving_school_management_system.tm.InstructorTM;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class InstructorController implements Initializable {


    public TextField telephone;
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
    private TableColumn<InstructorTM,String> colAddress;

    @FXML
    private TableColumn<InstructorTM,String> colEmail;

    @FXML
    private TableColumn<InstructorTM,String> colInstructorID;

    @FXML
    private TableColumn<InstructorTM,String> colLessonID;

    @FXML
    private TableColumn<InstructorTM,String> colName;

    @FXML
    private TableColumn<InstructorTM,String> colStudentID;

    @FXML
    private TableColumn<InstructorTM,String> colphone;

    @FXML
    private Label lblLessonID;

    @FXML
    private Label lblStudentID;

    @FXML
    private Label lblinsID;

    @FXML
    private TableView<InstructorTM> tblInstructor;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtphone;


    InstructorBO instructorBO = (InstructorBO) BOFactory.getInstance().getBO(BoTypes.INSTRUCTOR);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colInstructorID.setCellValueFactory(new PropertyValueFactory<>("instructorId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("instructorName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colphone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colLessonID.setCellValueFactory(new PropertyValueFactory<>("lessonId"));

        try{
//            loadTableData();
            loadNextId();
            resetPage();
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong...").show();
        }
    }

//    private void loadTableData()throws Exception{
//        List<InstructorDTO> instructorList = instructorBO.
//    }
    private void loadNextId(){}
    private void resetPage(){}

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
