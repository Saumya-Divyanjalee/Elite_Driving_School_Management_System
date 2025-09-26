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
import lk.ijse.orm.elite_driving_school_management_system.bo.custom.InstructorBO;
import lk.ijse.orm.elite_driving_school_management_system.dto.InstructorDTO;
import lk.ijse.orm.elite_driving_school_management_system.tm.InstructorTM;
import lk.ijse.orm.elite_driving_school_management_system.util.RegexUtil;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class InstructorController implements Initializable {

    @FXML
    private Button btnDelete, btnReset, btnSave, btnUpdate;

    @FXML
    private TableColumn<InstructorTM, Long> colInstructorID;
    @FXML
    private TableColumn<InstructorTM, String> colName;
    @FXML
    private TableColumn<InstructorTM, String> colAddress;
    @FXML
    private TableColumn<InstructorTM, String> colPhone;
    @FXML
    private TableColumn<InstructorTM, String> colEmail;
    @FXML
    private TableColumn<InstructorTM, String> colAvailability;

    @FXML
    private TableView<InstructorTM> tblInstructor;

    @FXML
    private TextField txtintrId, txtName, txtAddress, telephone, txtEmail;

    @FXML
    private ComboBox<String> cmbAvailability;

    private InstructorBO instructorBO = (InstructorBO) BOFactory.getInstance().getBO(BOFactory.BoTypes.INSTRUCTOR);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colInstructorID.setCellValueFactory(new PropertyValueFactory<>("instructorId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("instructorName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));

        cmbAvailability.setItems(FXCollections.observableArrayList("Available", "Not Available"));

        loadTableData();
    }

    private void loadTableData() {
        try {
            List<InstructorDTO> dtos = instructorBO.findAllInstructor();
            ObservableList<InstructorTM> listInstructor = FXCollections.observableArrayList();
            for (InstructorDTO dto : dtos) {
                listInstructor.add(new InstructorTM(
                        dto.getInstructorId(),
                        dto.getInstructorName(),
                        dto.getAddress(),
                        dto.getPhone(),
                        dto.getEmail(),
                        dto.getAvailability()
                ));
            }
            tblInstructor.setItems(listInstructor);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Oops! Not Loading Instructors data for table").show();
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        InstructorTM selectedItem = tblInstructor.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            txtintrId.setText(String.valueOf(selectedItem.getInstructorId()));
            txtName.setText(selectedItem.getInstructorName());
            txtAddress.setText(selectedItem.getAddress());
            telephone.setText(selectedItem.getPhone());
            txtEmail.setText(selectedItem.getEmail());
            cmbAvailability.setItems(FXCollections.observableArrayList(List.of(selectedItem.getAvailability())));
            cmbAvailability.getSelectionModel().select(0);
        }
    }

    @FXML
    void saveOnAction(ActionEvent event) {
        try {
            validateInputs();
            InstructorDTO instructorDTO = new InstructorDTO(
                    txtName.getText(),
                    txtAddress.getText(),
                    telephone.getText(),
                    txtEmail.getText(),
                    cmbAvailability.getSelectionModel().getSelectedItem()
            );
            if (instructorBO.saveInstructor(instructorDTO)) {
                new Alert(Alert.AlertType.INFORMATION, "Instructor Saved Successfully").show();
                loadTableData();
                resetPage();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error Saving Instructor: " + e.getMessage()).show();
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        try {
            validateInputs();
            long id = Long.parseLong(txtintrId.getText());
            InstructorDTO dto = new InstructorDTO(
                    id,
                    txtName.getText(),
                    txtAddress.getText(),
                    telephone.getText(),
                    txtEmail.getText(),
                    cmbAvailability.getSelectionModel().getSelectedItem()
            );
            if (instructorBO.updateInstructor(dto)) {
                new Alert(Alert.AlertType.INFORMATION, "Instructor Updated Successfully").show();
                loadTableData();
                resetPage();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error Updating Instructor: " + e.getMessage()).show();
        }
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        try {
            if (instructorBO.deleteInstructor(txtintrId.getText())) {
                new Alert(Alert.AlertType.INFORMATION, "Instructor Deleted Successfully").show();
                loadTableData();
                resetPage();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error Deleting Instructor: " + e.getMessage()).show();
        }
    }

    @FXML
    void resetOnAction(ActionEvent event) {
        resetPage();
    }

    private void resetPage() {
        txtintrId.clear();
        txtName.clear();
        txtAddress.clear();
        telephone.clear();
        txtEmail.clear();
        cmbAvailability.getSelectionModel().clearSelection();
    }

    private void validateInputs() throws Exception {
        RegexUtil.validateRequired(txtName.getText(), "Name");
        RegexUtil.validateRequired(txtAddress.getText(), "Address");
        RegexUtil.validateRequired(telephone.getText(), "Phone");
        RegexUtil.validateRequired(txtEmail.getText(), "Email");
        RegexUtil.validateRequired(cmbAvailability.getSelectionModel().getSelectedItem(), "Availability");

        if (!RegexUtil.isValidName(txtName.getText())) throw new Exception("Invalid Name");
        if (!RegexUtil.isValidAddress(txtAddress.getText())) throw new Exception("Invalid Address");
        if (!RegexUtil.isValidPhone(telephone.getText())) throw new Exception("Invalid Phone");
        if (!RegexUtil.isValidEmail(txtEmail.getText())) throw new Exception("Invalid Email");
    }
}
