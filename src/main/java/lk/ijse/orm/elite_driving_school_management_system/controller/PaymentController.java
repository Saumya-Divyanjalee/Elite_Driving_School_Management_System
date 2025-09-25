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

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<?> cmbCourseId;

    @FXML
    private ComboBox<String> cmbStudentId;

    @FXML
    private ComboBox<?> cmbUserId;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colCourseID;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colPaymentID;

    @FXML
    private TableColumn<?, ?> colStudentID;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private TableColumn<?, ?> colUserID;

    @FXML
    private DatePicker datepicker;

    @FXML
    private TableView<?> tblPayment;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtPaymentId;

    @FXML
    private TextField txtTime;

    PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BoTypes.PAYMENT);

    @FXML
    void deleteOnAction(ActionEvent event) {
        try{
            String paymentId = txtPaymentId.getText();
            if(paymentId.isEmpty()){
                new Alert(Alert.AlertType.ERROR, "Please Enter Payment ID!").show();
                return;
            }
            if (paymentId.isEmpty()){
                new Alert(Alert.AlertType.ERROR, "Please Enter Payment ID!").show();
                loadTableData();
                resetPage();
            }else {
                new Alert(Alert.AlertType.ERROR, "Not Found").show();
            }
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "Not Found").show();
        }

    }

    private void resetPage() {
        txtPaymentId.clear();
        txtAmount.clear();
        txtDescription.clear();
        datepicker.setValue(null);
        txtTime.clear();
        cmbStudentId.getSelectionModel().clearSelection();
        cmbCourseId.getSelectionModel().clearSelection();
        cmbUserId.getSelectionModel().clearSelection();


    }

    @FXML
    void onClickTable(MouseEvent event) {
        PaymentTM paymentTM = (PaymentTM) tblPayment.getSelectionModel().getSelectedItem();
        if(paymentTM == null){
            txtPaymentId.setText(String.valueOf(paymentTM.getPaymentId()));
            txtAmount.setText(String.valueOf(paymentTM.getAmount()));
            txtDescription.setText(String.valueOf(paymentTM.getDescription()));
            datepicker.setValue(null);
            txtTime.setText(String.valueOf(paymentTM.getTime()));
            cmbCourseId.getSelectionModel().clearSelection();
            cmbUserId.getSelectionModel().clearSelection();
            cmbStudentId.getSelectionModel().clearSelection();
        }

    }

    @FXML
    void resetOnAction(ActionEvent event) {
        resetPage();

    }

    @FXML
    void saveOnAction(ActionEvent event) {


    }

    @FXML
    void updateOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colPaymentID.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colCourseID.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colUserID.setCellValueFactory(new PropertyValueFactory<>("userId"));

        loadTableData();
        loadStudentIDs();
        loadCoursIDs();
        loadUserIds();
    }

    private void loadUserIds() {
        try {
            List<String> uIds = paymentBO.getAllUserId();
            cmbUserId.setItems(FXCollections.observableArrayList(uIds));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadCoursIDs() {
        try {
            List<String> lIds = paymentBO.getAllCourseId();
            cmbCourseId.setItems(FXCollections.observableArrayList(lIds));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadStudentIDs() {
        try{
            List<String> sIds = paymentBO.getAllStudentId();
            cmbStudentId.setItems(FXCollections.observableArrayList(sIds).sorted());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadTableData() {
        try{
            List<PaymentDTO> paymentDTOList = paymentBO.findAllPayment();
            ObservableList<PaymentTM>  paymentTM = FXCollections.observableArrayList();
            for(PaymentDTO p : paymentDTOList){
                paymentDTOList.add(new PaymentTM(
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
            tblPayment.setItems(paymentTM);
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK).show();
        }
    }
}
