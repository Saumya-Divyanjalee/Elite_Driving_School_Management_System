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
import lk.ijse.orm.elite_driving_school_management_system.bo.custom.PaymentBO;
import lk.ijse.orm.elite_driving_school_management_system.dto.PaymentDTO;
import lk.ijse.orm.elite_driving_school_management_system.tm.PaymentTM;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {

    //   Form fields
    @FXML private TextField txtPaymentId;   //   read-only, shows auto-generated ID
    @FXML private TextField txtAmount;
    @FXML private TextField txtDescription;
    @FXML private DatePicker datepicker;
    @FXML private ComboBox<String> cmbTime;
    @FXML private ComboBox<String> cmbStudentId;
    @FXML private ComboBox<String> cmbCourseId;
    @FXML private ComboBox<String> cmbUserId;

    //   Buttons
    @FXML private Button btnSave;
    @FXML private Button btnUpdate;
    @FXML private Button btnDelete;
    @FXML private Button btnReset;

    //   Table
    @FXML private TableView<PaymentTM> tblPayment;
    @FXML private TableColumn<?, ?> colPaymentID;
    @FXML private TableColumn<?, ?> colAmount;
    @FXML private TableColumn<?, ?> colDescription;
    @FXML private TableColumn<?, ?> colDate;
    @FXML private TableColumn<?, ?> colTime;
    @FXML private TableColumn<?, ?> colStudentID;
    @FXML private TableColumn<?, ?> colCourseID;
    @FXML private TableColumn<?, ?> colUserID;

    PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BoTypes.PAYMENT);


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Bind table columns to PaymentTM fields
        colPaymentID.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colCourseID.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colUserID.setCellValueFactory(new PropertyValueFactory<>("userId"));

        //   txtPaymentId is read-only — user cannot type in it
        txtPaymentId.setEditable(false);
        txtPaymentId.setStyle("-fx-background-color: #eef2ff; -fx-border-color: #c5cae9; " +
                "-fx-border-radius: 6; -fx-background-radius: 6; -fx-padding: 6 12; " +
                "-fx-text-fill: #0d47a1; -fx-font-weight: bold;");

        // Time options
        cmbTime.setItems(FXCollections.observableArrayList(
                "8:00 AM","8:30 AM","9:00 AM","9:30 AM","10:00 AM","10:30 AM",
                "11:00 AM","11:30 AM","12:00 PM","12:30 PM","1:00 PM","1:30 PM",
                "2:00 PM","2:30 PM","3:00 PM","3:30 PM","4:00 PM","4:30 PM",
                "5:00 PM","5:30 PM","6:00 PM"
        ));

        // Initial button states
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        btnSave.setDisable(false);

        loadTableData();
        loadNextId();        //  auto-generate ID on load
        loadStudentIDs();
        loadCourseIDs();
        loadUserIds();
    }

    //  SAVE
    @FXML
    void saveOnAction(ActionEvent event) {
        if (!validateInput(false)) return;

        try {
            PaymentDTO dto = new PaymentDTO(
                    null,
                    txtAmount.getText().trim(),
                    txtDescription.getText().trim(),
                    Date.valueOf(datepicker.getValue()),
                    cmbTime.getValue(),
                    cmbStudentId.getValue(),
                    cmbCourseId.getValue(),
                    cmbUserId.getValue()
            );

            if (paymentBO.savePayment(dto)) {
                showAlert(Alert.AlertType.INFORMATION, "  Payment saved successfully!");
                loadTableData();
                resetPage();
            } else {
                showAlert(Alert.AlertType.ERROR, "  Failed to save payment.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error saving payment: " + e.getMessage());
        }
    }

    //  UPDATE
    @FXML
    void updateOnAction(ActionEvent event) {
        if (!validateInput(true)) return;

        try {
            PaymentDTO dto = new PaymentDTO(
                    txtPaymentId.getText(),
                    txtAmount.getText().trim(),
                    txtDescription.getText().trim(),
                    Date.valueOf(datepicker.getValue()),
                    cmbTime.getValue(),
                    cmbStudentId.getValue(),
                    cmbCourseId.getValue(),
                    cmbUserId.getValue()
            );

            if (paymentBO.updatePayment(dto)) {
                showAlert(Alert.AlertType.INFORMATION, "  Payment updated successfully!");
                loadTableData();
                resetPage();
            } else {
                showAlert(Alert.AlertType.ERROR, "  Failed to update payment.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error updating payment: " + e.getMessage());
        }
    }

    //   DELETE
    @FXML
    void deleteOnAction(ActionEvent event) {
        String paymentId = txtPaymentId.getText();
        if (paymentId == null || paymentId.trim().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Please select a payment from the table first.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Delete payment #" + paymentId + "? This cannot be undone.",
                ButtonType.YES, ButtonType.NO);
        confirm.setHeaderText(null);
        Optional<ButtonType> result = confirm.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            try {
                if (paymentBO.deletePayment(paymentId)) {
                    showAlert(Alert.AlertType.INFORMATION, "  Payment deleted successfully!");
                    loadTableData();
                    resetPage();
                } else {
                    showAlert(Alert.AlertType.ERROR, "  Payment not found.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error deleting payment: " + e.getMessage());
            }
        }
    }

    //  RESET
    @FXML
    void resetOnAction(ActionEvent event) {
        resetPage();
    }

    //   TABLE CLICK
    @FXML
    void onClickTable(MouseEvent event) {
        PaymentTM selected = tblPayment.getSelectionModel().getSelectedItem();
        if (selected != null) {
            //   Populate form from selected row
            txtPaymentId.setText(String.valueOf(selected.getPaymentId()));
            txtAmount.setText(String.valueOf(selected.getAmount()));
            txtDescription.setText(selected.getDescription());

            if (selected.getDate() != null) {
                datepicker.setValue(selected.getDate().toInstant()
                        .atZone(java.time.ZoneId.systemDefault()).toLocalDate());
            }
            cmbTime.setValue(selected.getTime());
            cmbStudentId.setValue(selected.getStudentId());
            cmbCourseId.setValue(selected.getCourseId());
            cmbUserId.setValue(selected.getUserId());

            //  Switch button states
            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

    //   HELPERS

    //   Auto-generate next payment ID and show in txtPaymentId
    private void loadNextId() {
        try {
            Long nextId = paymentBO.getNextPaymentId();
            txtPaymentId.setText(String.valueOf(nextId));
        } catch (Exception e) {
            e.printStackTrace();
            txtPaymentId.setText("1");
        }
    }

    private void resetPage() {
        txtAmount.clear();
        txtDescription.clear();
        datepicker.setValue(null);
        cmbTime.getSelectionModel().clearSelection();
        cmbStudentId.getSelectionModel().clearSelection();
        cmbCourseId.getSelectionModel().clearSelection();
        cmbUserId.getSelectionModel().clearSelection();
        tblPayment.getSelectionModel().clearSelection();

        // Reset button states
        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        //   Load fresh next ID after save/delete
        loadNextId();
    }

    private void loadTableData() {
        try {
            List<PaymentDTO> list = paymentBO.findAllPayment();
            ObservableList<PaymentTM> items = FXCollections.observableArrayList();
            for (PaymentDTO p : list) {
                items.add(new PaymentTM(
                        p.getPaymentId(),
                        p.getAmount(),
                        p.getDescription(),
                        p.getDate(),
                        p.getTime(),
                        p.getStudentId(),
                        p.getCourseId(),
                        p.getUserId()
                ));
            }
            tblPayment.setItems(items);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Failed to load payments: " + e.getMessage());
        }
    }

    private void loadStudentIDs() {
        try {
            cmbStudentId.setItems(FXCollections.observableArrayList(
                    paymentBO.getAllStudentId()).sorted());
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void loadCourseIDs() {
        try {
            cmbCourseId.setItems(FXCollections.observableArrayList(
                    paymentBO.getAllCourseId()).sorted());
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void loadUserIds() {
        try {
            cmbUserId.setItems(FXCollections.observableArrayList(
                    paymentBO.getAllUserId()).sorted());
        } catch (Exception e) { e.printStackTrace(); }
    }

    //   requireId=true for update/delete, false for save
    private boolean validateInput(boolean requireId) {
        if (requireId && (txtPaymentId.getText() == null || txtPaymentId.getText().trim().isEmpty())) {
            showAlert(Alert.AlertType.WARNING, "Please select a payment from the table.");
            return false;
        }
        if (txtAmount.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Amount is required.");
            txtAmount.requestFocus();
            return false;
        }
        try {
            double amt = Double.parseDouble(txtAmount.getText().trim());
            if (amt <= 0) {
                showAlert(Alert.AlertType.WARNING, "Amount must be greater than 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.WARNING, "Amount must be a valid number (e.g. 15000.00).");
            txtAmount.requestFocus();
            return false;
        }
        if (datepicker.getValue() == null) {
            showAlert(Alert.AlertType.WARNING, "Please select a date.");
            return false;
        }
        if (cmbTime.getValue() == null) {
            showAlert(Alert.AlertType.WARNING, "Please select a time.");
            return false;
        }
        if (cmbStudentId.getValue() == null) {
            showAlert(Alert.AlertType.WARNING, "Please select a student.");
            return false;
        }
        if (cmbCourseId.getValue() == null) {
            showAlert(Alert.AlertType.WARNING, "Please select a course.");
            return false;
        }
        return true;
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}