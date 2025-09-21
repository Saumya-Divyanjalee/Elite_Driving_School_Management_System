package lk.ijse.orm.elite_driving_school_management_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lk.ijse.orm.elite_driving_school_management_system.bo.BOFactory;
import lk.ijse.orm.elite_driving_school_management_system.bo.BoTypes;
import lk.ijse.orm.elite_driving_school_management_system.bo.custom.PaymentBO;
import lk.ijse.orm.elite_driving_school_management_system.tm.PaymentTM;

public class PaymentController {

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
    private Label lblPaymentID;

    @FXML
    private Label lblStudentID;

    @FXML
    private Label lblUserId;

    @FXML
    private Label lblcourseID;

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
    void mailOnAction(ActionEvent event) {

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
