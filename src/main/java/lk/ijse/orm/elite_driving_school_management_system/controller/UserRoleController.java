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

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbRole;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colMobile;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private TableColumn<?, ?> colRole;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private TableColumn<?, ?> colUserName;

    @FXML
    private TableView<UserTM> tblUser;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtUserName;


    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BoTypes.USER);

    @FXML
    void deleteOnAction(ActionEvent event) {
        try{
            if(userBO.deleteUser(txtUserId.getText())){
                new Alert(Alert.AlertType.INFORMATION, "User deleted successfully", ButtonType.OK).show();
                loadTableData();
                resetPage();
            }
        }catch(Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong", ButtonType.OK).show();
        }

    }

    @FXML
    void onClickTable(MouseEvent event) {
        UserTM userTM = (UserTM) tblUser.getSelectionModel().getSelectedItem();
        if (userTM != null) {
            txtUserId.setText(String.valueOf(userTM.getUserId()));
            txtUserName.setText(userTM.getUsername());
            txtMobile.setText(userTM.getMobile());
            txtEmail.setText(userTM.getEmail());
            txtPassword.setText(userTM.getPassword());
            cmbRole.getSelectionModel().select(Integer.parseInt(userTM.getRole()));
        }

    }

    @FXML
    void resetOnAction(ActionEvent event) {
        resetPage();

    }

    @FXML
    void saveOnAction(ActionEvent event) {
        try {
            UserDTO userDTO = new UserDTO(
                    txtUserName.getText(),
                    txtMobile.getText(),
                    txtEmail.getText(),
                    txtPassword.getText(),
                    cmbRole.getSelectionModel().getSelectedItem()
            );

            if(userBO.saveUser(userDTO)){
                new Alert(Alert.AlertType.INFORMATION,"Save User...").show();
                loadTableData();
                resetPage();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong...").show();
        }

    }

    private void resetPage() {
        txtUserId.clear();
        txtUserName.clear();
        txtEmail.clear();
        txtMobile.clear();
        txtPassword.clear();
        cmbRole.getSelectionModel().clearSelection();
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        try {
            long id = Long.parseLong(txtUserId.getText());
            UserDTO userDTO = new UserDTO(
                    id,
                    txtUserName.getText(),
                    txtMobile.getText(),
                    txtEmail.getText(),
                    txtPassword.getText(),
                    (String) cmbRole.getSelectionModel().getSelectedItem()

            );

            if(userBO.updateUser(userDTO)){
                new Alert(Alert.AlertType.INFORMATION,"Update User...").show();
                loadTableData();
                resetPage();

            }
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "Something went wrong...").show();
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("username"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));


        cmbRole.setItems(FXCollections.observableArrayList("ADMIN", "RECEPTIONIST").sorted());

        loadTableData();
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

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "Error Loading Users....", ButtonType.OK).show();
        }
    }
}
