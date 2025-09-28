package lk.ijse.orm.elite_driving_school_management_system.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import lk.ijse.orm.elite_driving_school_management_system.bo.BOFactory;
import lk.ijse.orm.elite_driving_school_management_system.bo.custom.StudentBO;
import lk.ijse.orm.elite_driving_school_management_system.dto.StudentDTO;
import lk.ijse.orm.elite_driving_school_management_system.tm.StudentTM;
import lk.ijse.orm.elite_driving_school_management_system.util.RegexUtil;

import javax.security.auth.login.LoginException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class StudentController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private TableColumn<?, ?> colregisterfee;

    @FXML
    private DatePicker dateRegistration;

    @FXML
    private HBox imagehbox;

    @FXML
    private TableView<StudentTM> tblStudents;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtRegisterFee;

    @FXML
    private TextField txtStudentId;

    StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BoTypes.STUDENT);

    @FXML
    void clickOnAction(MouseEvent event) {
        StudentTM selected = tblStudents.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txtStudentId.setText(String.valueOf(selected.getStudentID()));
            txtName.setText(selected.getStudentName());
            txtEmail.setText(selected.getStudentEmail());
            txtPhone.setText(selected.getStudentPhone());
            txtAddress.setText(selected.getStudentAddress());
            txtRegisterFee.setText(selected.getRegisterFee());

            // Converts any java.util.Date into a LocalDate
            if (selected.getRegisterDate() != null) {
                dateRegistration.setValue(selected.getRegisterDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            }

            // Enable update and delete buttons when a student is selected
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
            btnSave.setDisable(true);
        }
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        // Validate that a student is selected
        if (txtStudentId.getText() == null || txtStudentId.getText().trim().isEmpty()) {
            errorMsg("Please select a student to delete!");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete this student?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            try {
                if (studentBO.deleteStudent(txtStudentId.getText().trim())) {
                    infoMsg("Student deleted successfully!");
                    loadStudentTable();
                    resetPage();
                } else {
                    errorMsg("Error deleting student!");
                }
            } catch (Exception e) {
                errorMsg("Error deleting student: " + e.getMessage());
            }
        }
    }

    @FXML
    void resetOnAction(ActionEvent event) {
        resetPage();
    }

    @FXML
    void saveOnAction(ActionEvent event) {
        try {
            RegexUtil.validateRequired(txtName.getText(), "Name");
            if (!RegexUtil.isValidName(txtName.getText())) {
                throw new LoginException("Invalid Name format!");
            }

            RegexUtil.validateRequired(txtEmail.getText(), "Email");
            if (!RegexUtil.isValidEmail(txtEmail.getText())) {
                throw new LoginException("Invalid Email format!");
            }

            RegexUtil.validateRequired(txtPhone.getText(), "Phone");
            if (!RegexUtil.isValidPhone(txtPhone.getText())) {
                throw new LoginException("Invalid Phone format!");
            }

            RegexUtil.validateRequired(txtAddress.getText(), "Address");
            if (!RegexUtil.isValidAddress(txtAddress.getText())) {
                throw new LoginException("Invalid Address format!");
            }

            RegexUtil.validateRequired(txtRegisterFee.getText(), "Register Fee");
            if (!RegexUtil.isValidFee(txtRegisterFee.getText())) {
                throw new LoginException("Invalid Register Fee format!");
            }

            if (dateRegistration.getValue() == null) {
                throw new LoginException("Registration Date is required!");
            }

            StudentDTO dto = new StudentDTO(
                    txtName.getText(),
                    txtEmail.getText(),
                    txtPhone.getText(),
                    txtAddress.getText(),
                    txtRegisterFee.getText(),
                    Date.valueOf(dateRegistration.getValue())
            );
            if (studentBO.saveStudent(dto)) {
                infoMsg("Student added successfully!");
                loadStudentTable();
                resetPage();
            } else {
                errorMsg("Error saving student!");
            }
        } catch (Exception e) {
            errorMsg("Error saving student: " + e.getMessage());
        }
    }

    private void resetPage() {
        txtStudentId.clear();
        txtName.clear();
        txtEmail.clear();
        txtPhone.clear();
        txtAddress.clear();
        txtRegisterFee.clear();
        dateRegistration.setValue(null);

        // Reset button states
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        btnSave.setDisable(false);
    }

    private void infoMsg(String s) {
        new Alert(Alert.AlertType.INFORMATION, s).show();
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        // Validate that a student is selected
        if (txtStudentId.getText() == null || txtStudentId.getText().trim().isEmpty()) {
            errorMsg("Please select a student to update!");
            return;
        }

        try {
            RegexUtil.validateRequired(txtName.getText(), "Name");
            if (!RegexUtil.isValidName(txtName.getText())) {
                throw new LoginException("Invalid Name format!");
            }
            RegexUtil.validateRequired(txtEmail.getText(), "Email");
            if (!RegexUtil.isValidEmail(txtEmail.getText())) {
                throw new LoginException("Invalid Email format!");
            }
            RegexUtil.validateRequired(txtPhone.getText(), "Phone");
            if (!RegexUtil.isValidPhone(txtPhone.getText())) {
                throw new LoginException("Invalid Phone format!");
            }
            RegexUtil.validateRequired(txtAddress.getText(), "Address");
            if (!RegexUtil.isValidAddress(txtAddress.getText())) {
                throw new LoginException("Invalid Address format!");
            }
            RegexUtil.validateRequired(txtRegisterFee.getText(), "Register Fee");
            if (!RegexUtil.isValidFee(txtRegisterFee.getText())) {
                throw new LoginException("Invalid Register Fee format!");
            }
            if (dateRegistration.getValue() == null) {
                throw new LoginException("Registration Date is required!");
            }

            long id = Long.parseLong(txtStudentId.getText().trim());
            StudentDTO dto = new StudentDTO(
                    id,
                    txtName.getText(),
                    txtEmail.getText(),
                    txtPhone.getText(),
                    txtAddress.getText(),
                    txtRegisterFee.getText(),
                    Date.valueOf(dateRegistration.getValue())
            );
            if (studentBO.updateStudent(dto)) {
                infoMsg("Student updated successfully!");
                loadStudentTable();
                resetPage();
            } else {
                errorMsg("Error updating student!");
            }
        } catch (NumberFormatException e) {
            errorMsg("Invalid student ID format!");
        } catch (Exception e) {
            errorMsg("Error updating student: " + e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("studentEmail"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("studentPhone"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("studentAddress"));
        colregisterfee.setCellValueFactory(new PropertyValueFactory<>("registerFee"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("registerDate"));

        // Initialize button states
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        loadStudentTable();
    }

    private void loadStudentTable() {
        try {
            List<StudentDTO> studentDTOS = studentBO.findAll();
            ObservableList<StudentTM> list = FXCollections.observableArrayList();
            for (StudentDTO dto : studentDTOS) {
                list.add(new StudentTM(
                        dto.getStudentID(),
                        dto.getStudentName(),
                        dto.getStudentEmail(),
                        dto.getStudentPhone(),
                        dto.getStudentAddress(),
                        dto.getRegisterFee(),
                        dto.getRegisterDate()
                ));
            }
            tblStudents.setItems(list);
        } catch (Exception e) {
            errorMsg("Error loading students: " + e.getMessage());
        }
    }

    private void errorMsg(String s) {
        new Alert(Alert.AlertType.ERROR, s).show();
    }
}