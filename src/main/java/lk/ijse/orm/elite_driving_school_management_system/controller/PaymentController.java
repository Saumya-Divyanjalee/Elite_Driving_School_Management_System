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
import lk.ijse.orm.elite_driving_school_management_system.bo.custom.PaymentBO;
import lk.ijse.orm.elite_driving_school_management_system.dto.PaymentDTO;
import lk.ijse.orm.elite_driving_school_management_system.tm.PaymentTM;
import lk.ijse.orm.elite_driving_school_management_system.util.RegexUtil;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {

    @FXML
    private TextField txtPaymentId, txtAmount, txtDescription, txtTime;

    @FXML
    private ComboBox<String> cmbUserId, cmbStudentId, cmbCourseId, cmbMethod;

    @FXML
    private DatePicker datePayment;

    @FXML
    private TableView<PaymentTM> tblPayment;

    @FXML
    private TableColumn<PaymentTM, String> colPaymentID, colAmount, colDescription, colDate,
            colTime, colStudentID, colCourseID, colUserID, colMethod;

    @FXML
    private Button btnSave, btnUpdate, btnDelete, btnReset;

    PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BoTypes.PAYMENT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colPaymentID.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colCourseID.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        colUserID.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colMethod.setCellValueFactory(new PropertyValueFactory<>("method"));

        cmbMethod.setItems(FXCollections.observableArrayList("Cash", "Card"));
        loadTableData();
        loadStudentIds();
        loadCourseIds();
    }

    private void loadStudentIds() {
        try {
            List<String> sIds = paymentBO.getAllStudentId();
            cmbStudentId.setItems(FXCollections.observableArrayList(sIds));
        } catch (Exception e) {
            showError("Error loading student IDs: " + e.getMessage());
        }
    }

    private void loadCourseIds() {
        try {
            List<String> cIds = paymentBO.getAllCourseId();
            cmbCourseId.setItems(FXCollections.observableArrayList(cIds));
        } catch (Exception e) {
            showError("Error loading course IDs: " + e.getMessage());
        }
    }

    private void loadTableData() {
        try {
            List<PaymentDTO> all = paymentBO.findAll();
            ObservableList<PaymentTM> list = FXCollections.observableArrayList();
            for (PaymentDTO dto : all) {
                list.add(new PaymentTM(
                        dto.getPaymentId(),
                        dto.getDate(),
                        dto.getMethod(),
                        dto.getAmount(),
                        dto.getDescription(),
                        dto.getTime(),
                        dto.getStudentID(),
                        dto.getCourseID(),
                        dto.getUserId()
                ));
            }
            tblPayment.setItems(list);
        } catch (Exception e) {
            showError("Error loading payments: " + e.getMessage());
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        PaymentTM selected = tblPayment.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txtPaymentId.setText(String.valueOf(selected.getPaymentId()));
            datePayment.setValue(selected.getDate().toLocalDate());
            txtAmount.setText(selected.getAmount());
            txtDescription.setText(selected.getDescription());
            txtTime.setText(selected.getTime());
            cmbMethod.getSelectionModel().select(selected.getMethod());
            cmbStudentId.getSelectionModel().select(String.valueOf(selected.getStudentID()));
            cmbCourseId.getSelectionModel().select(String.valueOf(selected.getCourseID()));
            cmbUserId.getSelectionModel().select(selected.getUserId());
        }
    }

    @FXML
    void saveOnAction(ActionEvent event) {
        try {
            RegexUtil.validateRequired(txtAmount.getText(), "Amount");
            RegexUtil.validateRequired(txtDescription.getText(), "Description");
            RegexUtil.validateRequired(txtTime.getText(), "Time");
            RegexUtil.validateRequired(cmbMethod.getValue(), "Payment Method");
            RegexUtil.validateRequired(cmbStudentId.getValue(), "Student");
            RegexUtil.validateRequired(cmbCourseId.getValue(), "Course");

            if (!RegexUtil.isValidFee(txtAmount.getText())) {
                showError("Invalid Amount (must be a number).");
                return;
            }
            if (!RegexUtil.isValidDescription(txtDescription.getText())) {
                showError("Invalid Description (letters/numbers only).");
                return;
            }
            if (!RegexUtil.isValidTime(txtTime.getText())) {
                showError("Invalid Time (HH:mm).");
                return;
            }

            PaymentDTO dto = new PaymentDTO(
                    Date.valueOf(datePayment.getValue()),
                    cmbMethod.getValue(),
                    txtAmount.getText(),
                    txtDescription.getText(),
                    txtTime.getText(),
                    Long.parseLong(cmbStudentId.getValue()),
                    Long.parseLong(cmbCourseId.getValue())
            );

            if (paymentBO.savePayment(dto)) {
                showInfo("Payment added successfully!");
                loadTableData();
                resetPage();
            }
        } catch (Exception e) {
            showError("Error saving payment: " + e.getMessage());
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        try {
            if (txtPaymentId.getText().isEmpty()) {
                showError("Select a payment to update!");
                return;
            }

            if (!RegexUtil.isValidFee(txtAmount.getText())) {
                showError("Invalid Amount (must be a number).");
                return;
            }
            if (!RegexUtil.isValidDescription(txtDescription.getText())) {
                showError("Invalid Description.");
                return;
            }
            if (!RegexUtil.isValidTime(txtTime.getText())) {
                showError("Invalid Time (HH:mm).");
                return;
            }

            PaymentDTO dto = new PaymentDTO(
                    Long.parseLong(txtPaymentId.getText()),
                    Date.valueOf(datePayment.getValue()),
                    cmbMethod.getValue(),
                    txtAmount.getText(),
                    txtDescription.getText(),
                    txtTime.getText(),
                    Long.parseLong(cmbStudentId.getValue()),
                    Long.parseLong(cmbCourseId.getValue()),
                    cmbUserId.getValue()
            );

            if (paymentBO.updatePayment(dto)) {
                showInfo("Payment updated successfully!");
                loadTableData();
                resetPage();
            }
        } catch (Exception e) {
            showError("Error updating payment: " + e.getMessage());
        }
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        try {
            if (txtPaymentId.getText().isEmpty()) {
                showError("Select a payment to delete!");
                return;
            }
            long id = Long.parseLong(txtPaymentId.getText());
            if (paymentBO.deletePayment(id)) {
                showInfo("Payment deleted successfully!");
                loadTableData();
                resetPage();
            } else {
                showError("Payment not found or cannot be deleted!");
            }
        } catch (Exception e) {
            showError("Error deleting payment: " + e.getMessage());
        }
    }

    @FXML
    void resetOnAction(ActionEvent event) {
        resetPage();
    }

    private void resetPage() {
        txtPaymentId.clear();
        datePayment.setValue(null);
        txtAmount.clear();
        txtDescription.clear();
        txtTime.clear();
        cmbMethod.getSelectionModel().clearSelection();
        cmbStudentId.getSelectionModel().clearSelection();
        cmbCourseId.getSelectionModel().clearSelection();
        cmbUserId.getSelectionModel().clearSelection();
    }

    private void showError(String message) {
        new Alert(Alert.AlertType.ERROR, message).show();
    }

    private void showInfo(String message) {
        new Alert(Alert.AlertType.INFORMATION, message).show();
    }
}
