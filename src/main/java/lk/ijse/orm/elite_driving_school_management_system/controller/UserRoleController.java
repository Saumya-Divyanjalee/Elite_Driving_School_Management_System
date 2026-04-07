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
import lk.ijse.orm.elite_driving_school_management_system.bo.custom.UserBO;
import lk.ijse.orm.elite_driving_school_management_system.dto.UserDTO;
import lk.ijse.orm.elite_driving_school_management_system.tm.UserTM;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserRoleController implements Initializable {

    @FXML private Button btnDelete;
    @FXML private Button btnReset;
    @FXML private Button btnSave;
    @FXML private Button btnUpdate;
    @FXML private ComboBox<String> cmbRole;
    @FXML private TableColumn<?, ?> colEmail;
    @FXML private TableColumn<?, ?> colMobile;
    @FXML private TableColumn<?, ?> colPassword;
    @FXML private TableColumn<?, ?> colRole;
    @FXML private TableColumn<?, ?> colUserId;
    @FXML private TableColumn<?, ?> colUserName;
    @FXML private TableView<UserTM> tblUser;
    @FXML private TextField txtEmail;
    @FXML private TextField txtMobile;
    @FXML private TextField txtPassword;
    @FXML private TextField txtUserId;
    @FXML private TextField txtUserName;

    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BoTypes.USER);

    @FXML
    void deleteOnAction(ActionEvent event) {
        //   Ask for confirmation before deleting
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete this user?", ButtonType.YES, ButtonType.NO);
        confirm.setTitle("Confirm Delete");
        confirm.setHeaderText(null);
        confirm.showAndWait();

        if (confirm.getResult() == ButtonType.NO) return;

        try {
            if (userBO.deleteUser(txtUserId.getText())) {
                new Alert(Alert.AlertType.INFORMATION, "User deleted successfully.").showAndWait();
                loadTableData();
                resetPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "User not found or already deleted.").showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error deleting user: " + e.getMessage()).showAndWait();
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        UserTM userTM = tblUser.getSelectionModel().getSelectedItem();
        if (userTM != null) {
            txtUserId.setText(String.valueOf(userTM.getUserId()));
            txtUserName.setText(userTM.getUsername());
            txtMobile.setText(userTM.getMobile());
            txtEmail.setText(userTM.getEmail());
            txtPassword.setText(userTM.getPassword());
            cmbRole.getSelectionModel().select(userTM.getRole());

            //   Enable Update and Delete when a row is selected, disable Save
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
            //   Validate all required fields
            if (txtUserName.getText().trim().isEmpty()) {
                showWarning("Username is required.");
                return;
            }
            if (txtPassword.getText().trim().isEmpty()) {
                showWarning("Password is required.");
                return;
            }
            if (txtEmail.getText().trim().isEmpty()) {
                showWarning("Email is required.");
                return;
            }
            if (cmbRole.getSelectionModel().getSelectedItem() == null) {
                showWarning("Please select a role.");
                return;
            }

            UserDTO userDTO = new UserDTO(
                    txtUserName.getText().trim(),
                    txtMobile.getText().trim(),
                    txtEmail.getText().trim(),
                    txtPassword.getText().trim(),
                    cmbRole.getSelectionModel().getSelectedItem()
            );

            if (userBO.saveUser(userDTO)) {
                new Alert(Alert.AlertType.INFORMATION, "User saved successfully!").showAndWait();
                loadTableData();
                resetPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save user!").showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error saving user: " + e.getMessage()).showAndWait();
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        try {
            if (txtUserId.getText().trim().isEmpty()) {
                showWarning("Please select a user from the table first.");
                return;
            }
            if (txtUserName.getText().trim().isEmpty()) {
                showWarning("Username is required.");
                return;
            }
            if (txtPassword.getText().trim().isEmpty()) {
                showWarning("Password is required.");
                return;
            }
            if (cmbRole.getSelectionModel().getSelectedItem() == null) {
                showWarning("Please select a role.");
                return;
            }

            long id = Long.parseLong(txtUserId.getText().trim());
            UserDTO userDTO = new UserDTO(
                    id,
                    txtUserName.getText().trim(),
                    txtMobile.getText().trim(),
                    txtEmail.getText().trim(),
                    txtPassword.getText().trim(),
                    cmbRole.getSelectionModel().getSelectedItem()
            );

            if (userBO.updateUser(userDTO)) {
                new Alert(Alert.AlertType.INFORMATION, "User updated successfully!").showAndWait();
                loadTableData();
                resetPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update user!").showAndWait();
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid user ID format!").showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error updating user: " + e.getMessage()).showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //   Map table columns to UserTM fields
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("username"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

        //   Initial button states — Save enabled, Update/Delete disabled until row selected
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        btnSave.setDisable(false);

        cmbRole.setItems(FXCollections.observableArrayList("ADMIN", "RECEPTIONIST"));

        loadTableData();
    }

    //   Clear all fields and reset button states
    private void resetPage() {
        txtUserId.clear();
        txtUserName.clear();
        txtEmail.clear();
        txtMobile.clear();
        txtPassword.clear();
        cmbRole.getSelectionModel().clearSelection();
        tblUser.getSelectionModel().clearSelection();

        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    private void loadTableData() {
        try {
            List<UserDTO> list = userBO.findAllUser();
            ObservableList<UserTM> listTM = FXCollections.observableArrayList();
            for (UserDTO userDTO : list) {
                listTM.add(new UserTM(
                        userDTO.getUserId(),
                        userDTO.getUsername(),
                        userDTO.getMobile(),
                        userDTO.getEmail(),
                        userDTO.getPassword(),
                        userDTO.getRole()
                ));
            }
            tblUser.setItems(listTM);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error loading users: " + e.getMessage()).showAndWait();
        }
    }

    private void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validation");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}