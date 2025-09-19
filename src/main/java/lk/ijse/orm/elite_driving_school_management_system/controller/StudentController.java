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
import lk.ijse.orm.elite_driving_school_management_system.bo.custom.StudentBO;
import lk.ijse.orm.elite_driving_school_management_system.dto.StudentDTO;
import lk.ijse.orm.elite_driving_school_management_system.tm.StudentTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class StudentController implements Initializable {

    StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BoTypes.STUDENT);

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
    private TableColumn<StudentTM,String> colAddress;

    @FXML
    private TableColumn<StudentTM,String> colAge;

    @FXML
    private TableColumn<StudentTM,String> colEmail;

    @FXML
    private TableColumn<StudentTM,String> colFirstName;

    @FXML
    private TableColumn<StudentTM,String> colLastName;

    @FXML
    private TableColumn<StudentTM,String> colPhone;

    @FXML
    private TableColumn<StudentTM,String> colStudentID;

    @FXML
    private TableColumn<StudentTM,String> colregDate;

    @FXML
    private Label lblCourseID;

    @FXML
    private Label lblLessonID;

    @FXML
    private Label lblPaymentID;

    @FXML
    private Label lblStudentID;

    @FXML
    private DatePicker registrationDatePicker;

    @FXML
    private TableView<StudentTM> tblStudent;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtPhone;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colregDate.setCellValueFactory(new PropertyValueFactory<>("regDate"));

        try{
            resetPage();
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Oops!...Something went wrong.").show();
        }
    }

    private void resetPage() {

       try {
           loadTableData();

           loadNextId();

           btnSave.setDisable(false);
           btnDelete.setDisable(true);
           btnUpdate.setDisable(true);

           txtFirstName.clear();
           txtLastName.clear();
           txtEmail.clear();
           txtPhone.clear();
           registrationDatePicker.setValue(null);
           txtAddress.clear();
       }catch (Exception e){
           e.printStackTrace();
           new Alert(Alert.AlertType.ERROR,"Oops!...Something went wrong.").show();
       }


    }

    private void loadNextId() throws Exception {
        Long nextId = studentBO.getNextId();
        lblStudentID.setText(String.valueOf(nextId));

    }

    private void loadTableData() throws Exception {
        ArrayList<StudentDTO> studentDTOS = (ArrayList<StudentDTO>) studentBO.getAllStudent();
        ObservableList<StudentTM> studentTMS = FXCollections.observableArrayList();

        for (StudentDTO dto : studentDTOS) {
            studentTMS.add(new StudentTM(
                    dto.getStudentId(),
                    dto.getFirstName(),
                    dto.getLastName(),
                    dto.getEmail(),
                    dto.getPhone(),
                    dto.getAge(),
                    dto.getRegDate(),
                    dto.getAddress()

            ));
            tblStudent.setItems(studentTMS);
        }
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         Optional<ButtonType> result = alert.showAndWait();

         if(result.isPresent() && result.get() == ButtonType.YES){
             Long studentId = Long.valueOf(lblStudentID.getText());
             try{
                 boolean isDelete = studentBO.deleteStudent(studentId);
                 if(isDelete){
                     resetPage();
                     new Alert(Alert.AlertType.INFORMATION,"Deleted Successfully!").show();
                 }else {
                     new Alert(Alert.AlertType.ERROR,"Something went wrong...").show();
                 }

             } catch (Exception e) {
                 throw new RuntimeException(e);
             }
         }


    }


    @FXML
    void onClickTable(MouseEvent event) {
        StudentTM studentTM = tblStudent.getSelectionModel().getSelectedItem();

        if(studentTM != null){
            lblStudentID.setText(String.valueOf(studentTM.getStudentId()));
            txtFirstName.setText(studentTM.getFirstName());
            txtLastName.setText(studentTM.getLastName());
            txtEmail.setText(studentTM.getEmail());
            txtPhone.setText(studentTM.getPhone());
            txtAge.setText(String.valueOf(studentTM.getAge()));
            txtAddress.setText(studentTM.getAddress());
            registrationDatePicker.setValue(studentTM.getRegDate());
            txtAddress.setText(studentTM.getAddress());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(true);
        }

    }

    @FXML
    void resetOnAction(ActionEvent event) {
        resetPage();

    }

    @FXML
    void saveOnAction(ActionEvent event) throws Exception {
        long id = Long.parseLong(lblStudentID.getText());
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String age = txtAge.getText();
        String regDate = registrationDatePicker.getValue().toString();
        String address = txtAddress.getText();

        StudentDTO studentDTO = new StudentDTO(firstName,
                lastName,
                email,
                phone,
                age,
                regDate,
                address
        );
        boolean isSave = studentBO.saveStudent(studentDTO);

        if (isSave) {
            loadTableData();
            new Alert(Alert.AlertType.INFORMATION,"Student has been saved.").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Something went wrong.").show();
        }





    }

    @FXML
    void updateOnAction(ActionEvent event) {
        try {
            long id = Long.parseLong(lblStudentID.getText());
            String firstName = txtFirstName.getText();
            String lastName = txtLastName.getText();
            String email = txtEmail.getText();
            String phone = txtPhone.getText();
            String age = txtAge.getText();
            String regDate = registrationDatePicker.getValue().toString();
            String address = txtAddress.getText();

            StudentDTO studentDTO = new StudentDTO(
                    id, firstName, lastName, email, phone, age, regDate, address
            );

            boolean isUpdated = studentBO.updateStudent(studentDTO);

            if (isUpdated) {
                resetPage();
                new Alert(Alert.AlertType.INFORMATION, "Student updated successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Update failed!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error occurred while updating!").show();
        }
    }


}
