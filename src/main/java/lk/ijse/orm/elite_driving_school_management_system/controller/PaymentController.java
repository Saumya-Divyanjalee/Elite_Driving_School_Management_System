package lk.ijse.orm.elite_driving_school_management_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import lk.ijse.orm.elite_driving_school_management_system.bo.BOFactory;
import lk.ijse.orm.elite_driving_school_management_system.bo.BoTypes;
import lk.ijse.orm.elite_driving_school_management_system.bo.custom.PaymentBO;
import lk.ijse.orm.elite_driving_school_management_system.tm.PaymentTM;

import java.net.URL;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {

    public TextField txtPaymentId;
    public ComboBox cmbUserId;
    public ComboBox cmbStudentId;
    public ComboBox cmbCourseId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    @FXML
    private Button btnDelete;



    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<PaymentTM,String> colAmount;

    @FXML
    private TableColumn<PaymentTM,String> colCourseID;

    @FXML
    private TableColumn<PaymentTM,String> colDate;

    @FXML
    private TableColumn<PaymentTM,String> colDescription;

    @FXML
    private TableColumn<PaymentTM,String> colPaymentID;

    @FXML
    private TableColumn<PaymentTM,String> colStudentID;

    @FXML
    private TableColumn<PaymentTM,String> colTime;

    @FXML
    private TableColumn<PaymentTM,String> colUserID;

    @FXML
    private DatePicker datepicker;


    @FXML
    private TableView<PaymentTM> tblPayment;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtDescription;

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


}
