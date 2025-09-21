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
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class LessonSchedulingController implements Initializable {

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
    private Label lblInstructorID;

    @FXML
    private Label lblStudentID;

    @FXML
    private Label lblcourseID;

    @FXML
    private Label lbllessonID;

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

        try{
            loadTableData();
            loadNextId();
            resetPage();
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
        }

    }

    private void loadTableData() throws Exception {
        List<LessonDTO> lessonDTOList = lessonBO.getAllLesson();
        ObservableList<LessonTM> observableList = FXCollections.observableArrayList();
        for (LessonDTO dto : lessonDTOList) {
            observableList.add(new LessonTM(
                    dto.getLessonId(),
                    dto.getLessonName(),
                    dto.getStartTime(),
                    dto.getEndTime()

            ));

        }
    }

    private void loadNextId() {
        try{
            Long lastId = lessonBO.getNextIdLesson();
            long newIdNum = (lastId == null? 1: lastId + 1);
            String nextId = String.format("L%3d", newIdNum);
            lblcourseID.setText(nextId);
        } catch (Exception e) {
           e.printStackTrace();
           new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
        }
    }

    private void resetPage() {
        try{
            txtName.clear();
            txtendtime.clear();
            txtstarttime.clear();
            datepicker.setValue(null);
            loadTableData();
            loadNextId();

            btnSave.setDisable(false);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong! reset Page ").show();
        }
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to delete this lesson?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.YES){
            try{
                boolean deleted = lessonBO.deleteLesson(Long.valueOf(lbllessonID.getText()));
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
            lbllessonID.setText(String.valueOf(lessonTM.getLessonId()));
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
        LessonDTO lessonDTO = new LessonDTO(
                lbllessonID.getText(),
                txtName.getText(),
                txtstarttime.getText(),
                txtendtime.getText()
        );

        try {
            boolean saved = lessonBO.saveLesson(lessonDTO);
            if(saved){
                resetPage();
                new Alert(Alert.AlertType.INFORMATION,"Saved Successfully!").show();

            }else {
                new Alert(Alert.AlertType.ERROR,"Something went wrong! Not Saving").show();
            }
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong! Save Issue").show();
        }


    }

    @FXML
    void updateOnAction(ActionEvent event) {
        LessonDTO lessonDTO = new LessonDTO(
                lbllessonID.getText(),
                txtName.getText(),
                txtstarttime.getText(),
                txtendtime.getText()

        );

        try{
            boolean updated = lessonBO.updateLesson(lessonDTO);
            if(updated){
                resetPage();
                new Alert(Alert.AlertType.INFORMATION,"Updated Successfully!").show();

            }else {
                new Alert(Alert.AlertType.ERROR,"Something went wrong! Update Issue").show();

            }
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong! Not work update button").show();
        }

    }


}
