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
import lk.ijse.orm.elite_driving_school_management_system.bo.custom.InstructorBO;
import lk.ijse.orm.elite_driving_school_management_system.dto.InstructorDTO;
import lk.ijse.orm.elite_driving_school_management_system.tm.InstructorTM;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class InstructorController implements Initializable {

    public ComboBox cmbAvailability;
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
    private TableColumn<?, ?> colAvailability;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colInstructorID;

    @FXML
    private TableColumn<?, ?> colLessonID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colStudentID;

    @FXML
    private TableColumn<?, ?> colphone;

    @FXML
    private TableView<InstructorTM> tblInstructor;

    @FXML
    private TextField telephone;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAvailabi;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtLessonId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtStudentId;

    @FXML
    private TextField txtintrId;

    InstructorBO instructorBO = (InstructorBO) BOFactory.getInstance().getBO(BoTypes.INSTRUCTOR);

    @FXML
    void deleteOnAction(ActionEvent event) {


    }

    @FXML
    void onClickTable(MouseEvent event) {
        InstructorTM selectedItem = tblInstructor.getSelectionModel().getSelectedItem();
        if(selectedItem!=null){
            txtintrId.setText(String.valueOf(selectedItem.getInstructorId()));
            txtName.setText(selectedItem.getInstructorName());
            txtAddress.setText(selectedItem.getAddress());
            telephone.setText(selectedItem.getPhone());
            txtEmail.setText(selectedItem.getEmail());
            cmbAvailability.setItems(FXCollections.observableArrayList(selectedItem.getAvailability()));
        }


    }

    @FXML
    void resetOnAction(ActionEvent event) {
       resetPage();

    }

    @FXML
    void saveOnAction(ActionEvent event) {
        try{
            InstructorDTO instructorDTO = new InstructorDTO(
                    txtName.getText(),
                    txtAddress.getText(),
                    telephone.getText(),
                    txtEmail.getText(),
                    cmbAvailability.getSelectionModel().getSelectedItem()

            );
            if(instructorBO.saveInstructor(instructorDTO)){
                new Alert(Alert.AlertType.INFORMATION, "Success Save", ButtonType.OK).show();
                loadTableData();
                resetPage();
            }
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "Not Save", ButtonType.OK).show();
        }

    }

    @FXML
    void updateOnAction(ActionEvent event) {
        try {
            long id = Long.parseLong(txtintrId.getText());
            InstructorDTO dto = new InstructorDTO(
                    id,
                    txtName.getText(),
                    txtAddress.getText(),
                    telephone.getText(),
                    colEmail.getText(),
                    (String) cmbAvailability.getSelectionModel().getSelectedItem()
            );

            if(instructorBO.updateInstructor(dto)){
                new Alert(Alert.AlertType.INFORMATION, "Instructor Updated", ButtonType.OK).show();
                loadTableData();
                resetPage();
            }
        }catch (Exception e){
            new Alert(Alert.AlertType.INFORMATION, "Instructor Error", ButtonType.OK).show();
        }

    }

    private void resetPage() {
        txtintrId.clear();
        txtName.clear();
        txtAddress.clear();
        txtEmail.clear();
        telephone.clear();
        cmbAvailability.getSelectionModel().clearSelection();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colInstructorID.setCellValueFactory(new PropertyValueFactory<>("instructorID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colphone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));

        loadTableData();
        cmbAvailability.setItems(FXCollections.observableArrayList("Available", "Not Available").sorted());

    }

    private void loadTableData() {
        try {
            List<InstructorDTO> dtos = instructorBO.findAllInstructor();
            ObservableList<InstructorTM> listInstructor = FXCollections.observableArrayList();
            for(InstructorDTO dto : dtos){
                listInstructor.add(new InstructorTM(
                        dto.getInstructorId(),
                        dto.getInstructorName(),
                        dto.getAddress(),
                        dto.getPhone(),
                        dto.getEmail(),
                        dto.getAvailability()

                ));
                tblInstructor.setItems(listInstructor);

            }

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Oops! Not Loading Instructors data for table").show();
        }
    }


}
