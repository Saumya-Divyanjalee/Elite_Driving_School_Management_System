package lk.ijse.orm.elite_driving_school_management_system.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.orm.elite_driving_school_management_system.bo.BOFactory;
import lk.ijse.orm.elite_driving_school_management_system.bo.BoTypes;
import lk.ijse.orm.elite_driving_school_management_system.bo.custom.LessonBO;
import lk.ijse.orm.elite_driving_school_management_system.dto.LessonDTO;
import lk.ijse.orm.elite_driving_school_management_system.tm.LessonTM;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class LessonSchedulingController implements Initializable {

    public TextField txtLesson;
    public ComboBox cmbInstructor;
    public ComboBox cmbStudent;
    public ComboBox cmbCourse;
    public TableColumn colDate;
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
    private TableColumn<LessonTM,String> colCourseID;

    @FXML
    private TableColumn<LessonTM,String> colEndTime;

    @FXML
    private TableColumn<LessonTM,String> colInstructorID;

    @FXML
    private TableColumn<LessonTM,String> colName;

    @FXML
    private TableColumn<LessonTM,String> colStartTime;

    @FXML
    private TableColumn<LessonTM,String> colStudentID;

    @FXML
    private TableColumn<LessonTM,String> collessonID;

    @FXML
    private DatePicker datepicker;

    @FXML
    private TableView<LessonTM> tblLesson;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtendtime;

    @FXML
    private TextField txtstarttime;


    LessonBO lessonBO = (LessonBO) BOFactory.getInstance().getBO(BoTypes.LESSON);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        collessonID.setCellValueFactory(new PropertyValueFactory<>("lessonId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("lessonName"));
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colInstructorID.setCellValueFactory(new PropertyValueFactory<>("instructorId"));
        colCourseID.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("studentId"));

        try{
            loadTableData();
            loadCourseId();
            loadInstructorId();
            loadStudentId();
            resetPage();
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
        }

    }

    private void loadStudentId() {
        try {
            List<String> sId = lessonBO.getAllStudentIds();
            ObservableList<String> list = FXCollections.observableArrayList(sId);
            cmbStudent.setItems(list);

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong!-loadStudentId").show();
        }
    }

    private void loadInstructorId() {
        try {
            List<String> iId = lessonBO.getAllInstructorIds();
            ObservableList<String> list = FXCollections.observableArrayList(iId);
            cmbInstructor.setItems(list);
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong!-loadInstructorId").show();
        }
    }

    private void loadCourseId() {
        try {
            List<String> cId = lessonBO.getAllCourseIds();
            ObservableList<String> list = FXCollections.observableArrayList(cId);
            cmbCourse.setItems(list);

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong!-loadCourseId").show();
        }

    }

    private void loadTableData() throws Exception {
        try {
            List<LessonDTO> lessonDTOList = lessonBO.getAllLesson();

            ObservableList<LessonTM> observableList = FXCollections.observableArrayList();
        for (LessonDTO dto : lessonDTOList) {
            observableList.add(new LessonTM(
                    dto.getLessonId(),
                    dto.getLessonName(),
                    dto.getStartTime(),
                    dto.getEndTime(),
                    dto.getDate(),
                    dto.getInstructorId(),
                    dto.getCourseId(),
                    dto.getStudentId()


            ));
            tblLesson.setItems(observableList);

        }
    }catch (Exception e){
        new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
        }
    }



    private void resetPage() {
        txtLesson.clear();
        txtName.clear();
        txtendtime.clear();
        txtstarttime.clear();
        datepicker.setValue(null);
        cmbInstructor.getSelectionModel().clearSelection();
        cmbStudent.getSelectionModel().clearSelection();
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to delete this lesson?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.YES){
            try{
                boolean deleted = lessonBO.deleteLesson(String.valueOf(Long.valueOf(txtLesson.getText())));
                if(deleted){
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION,"Deleted Successfully!").show();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Something went wrong! Delete Issue").show();
                }
            }catch (Exception e){
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR,"Something went wrong! Not work Delete Button").show();
            }
        }

    }


    @FXML
    void onClickTable(MouseEvent event) {
        LessonTM lessonTM = tblLesson.getSelectionModel().getSelectedItem();
        if(lessonTM != null){
            txtLesson.setText(String.valueOf(lessonTM.getLessonId()));
            txtName.setText(lessonTM.getLessonName());
            txtstarttime.setText(lessonTM.getStartTime());
            txtendtime.setText(lessonTM.getEndTime());
            datepicker.setDisable(false);

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);

        }

    }

    @FXML
    void resetOnAction(ActionEvent event) {
        resetPage();

    }

    @FXML
    void saveOnAction(ActionEvent event) {
        try {
            LessonDTO dto = new LessonDTO(

                    txtName.getText(),
                    txtstarttime.getText(),
                    txtendtime.getText(),
                    datepicker.getValue().toString(),
                    cmbInstructor.getSelectionModel().getSelectedItem(),
                    cmbCourse.getSelectionModel().getSelectedItem(),
                    cmbStudent.getSelectionModel().getSelectedItem()
            );

            if (lessonBO.saveLesson(dto)) {
                new Alert(Alert.AlertType.INFORMATION, "Lesson added successfully!").show();
                loadTableData();
                resetPage();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error saving lesson!").show();
        }
    }




    @FXML
    void updateOnAction(ActionEvent event) {
        try {
            LessonDTO dto = new LessonDTO(
                    Long.parseLong(txtLesson.getText()),
                    txtName.getText(),
                    txtstarttime.getText(),
                    txtendtime.getText(),
                    datepicker.getValue().toString(),
                    cmbInstructor.getSelectionModel().getSelectedItem(),
                    cmbCourse.getSelectionModel().getSelectedItem(),
                    cmbStudent.getSelectionModel().getSelectedItem()
            );

            if (lessonBO.updateLesson(dto)) {
                new Alert(Alert.AlertType.INFORMATION, "Lesson updated successfully!").show();
                loadTableData();
                resetPage();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error updating lesson!").show();
        }

    }


}
