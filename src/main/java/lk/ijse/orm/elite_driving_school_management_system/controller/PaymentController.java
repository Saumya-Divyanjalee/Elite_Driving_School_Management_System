package lk.ijse.orm.elite_driving_school_management_system.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.orm.elite_driving_school_management_system.bo.BOFactory;
import lk.ijse.orm.elite_driving_school_management_system.bo.BoTypes;
import lk.ijse.orm.elite_driving_school_management_system.bo.custom.PaymentBO;

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

    }

    @FXML
    void onClickTable(MouseEvent event) {

    }

    @FXML
    void resetOnAction(ActionEvent event) {

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
    }
}
