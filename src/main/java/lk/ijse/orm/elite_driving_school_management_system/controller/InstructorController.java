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
import lk.ijse.orm.elite_driving_school_management_system.bo.custom.InstructorBO;
import lk.ijse.orm.elite_driving_school_management_system.dto.InstructorDTO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Instructor;
import lk.ijse.orm.elite_driving_school_management_system.tm.InstructorTM;

import java.net.URL;
import java.util.List;
import java.util.Optional;
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
            loadTableData();
            loadNextId();
            resetPage();
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong...").show();
        }
    }

    private void loadTableData() throws Exception {
        List<InstructorDTO> instructorList = instructorBO.getAllInstructor();
        ObservableList<InstructorTM> observableList = FXCollections.observableArrayList();
        for (InstructorDTO dto : instructorList) {
            observableList.add(new InstructorTM(
                    dto.getInstructorId(),
                    dto.getInstructorName(),
                    dto.getAddress(),
                    dto.getPhone(),
                    dto.getEmail()
            ));
        }
        tblInstructor.setItems(observableList);
    }

    private void loadNextId() {
        try {
            Long lastId = instructorBO.getNextIdInstructor();
            long newIdNum = (lastId == null ? 1 : lastId + 1);
            String nextId = String.format("I%03d", newIdNum);
            lblinsID.setText(nextId);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load next Instructor ID").show();
        }
    }

    private void resetPage(){
        try{
            txtName.clear();
            txtAddress.clear();
            txtEmail.clear();
            telephone.clear();
            txtphone.clear();
            loadTableData();
            loadNextId();

            btnSave.setDisable(false);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);


        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong...").show();
        }
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to delete this instructor?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get()==ButtonType.YES){
            try {
                boolean deleted = instructorBO.deleteInstructor(Long.valueOf(lblinsID.getText()));
                if(deleted){
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION,"Deleted Successfully...").show();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Something went wrong...").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR,"Something went wrong...").show();
            }

        }

    }



    @FXML
    void onClickTable(MouseEvent event) {
        InstructorTM instructorTM = tblInstructor.getSelectionModel().getSelectedItem();
        if (instructorTM != null) {
            lblinsID.setText(String.valueOf(instructorTM.getInstructorId()));
            txtName.setText(instructorTM.getInstructorName());
            txtAddress.setText(instructorTM.getAddress());
            telephone.setText(instructorTM.getPhone());
            txtEmail.setText(instructorTM.getEmail());

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
        if(!validateInput())return;
        InstructorDTO instructorDTO = new InstructorDTO(
                lblinsID.getText(),
                txtName.getText(),
                txtAddress.getText(),
                telephone.getText(),
                txtEmail.getText()
        );
        try{
            boolean saved = instructorBO.saveInstructor(instructorDTO);
            if(saved){
                resetPage();
                new Alert(Alert.AlertType.INFORMATION,"Successfully saved...").show();

            }else{
                new Alert(Alert.AlertType.ERROR,"Something went wrong...").show();
            }
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong...").show();
        }


    }

    @FXML
    void updateOnAction(ActionEvent event) {
        if(!validateInput())return;
        InstructorDTO instructorDTO = new InstructorDTO(
                lblinsID.getText(),
                txtName.getText(),
                txtAddress.getText(),
                telephone.getText(),
                txtEmail.getText()
        );

        try {
            boolean updated = instructorBO.updateInstructor(instructorDTO);
            if(updated){
                resetPage();
                new Alert(Alert.AlertType.INFORMATION,"Successfully updated...").show();

            }else {
                new Alert(Alert.AlertType.ERROR,"Something went wrong...").show();
            }
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong...").show();
        }

    }


    private boolean validateInput() {
        if (txtName.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter the name of your student.").show();
            return false;

        }
        if (txtAddress.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter your address.").show();
            return false;
        }
        if (txtEmail.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter your email.").show();
            return false;
        }
        if (txtphone.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter your phone number.").show();
            return false;
        }
        return true;
    }

}
