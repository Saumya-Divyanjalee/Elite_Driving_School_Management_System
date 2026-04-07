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
import lk.ijse.orm.elite_driving_school_management_system.bo.custom.CourseBO;
import lk.ijse.orm.elite_driving_school_management_system.dto.CourseDTO;
import lk.ijse.orm.elite_driving_school_management_system.tm.CourseTM;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CourseController implements Initializable {

    //  Table columns — only 4, matching CourseTM fields exactly
    @FXML private TableColumn<CourseTM, String> colCourseID;
    @FXML private TableColumn<CourseTM, String> colCourseName;
    @FXML private TableColumn<CourseTM, String> colTimePeriod;
    @FXML private TableColumn<CourseTM, String> colCourseFee;
    @FXML private TableView<CourseTM> tblCourse;

    //  Form fields
    @FXML private TextField txtCourseName;
    @FXML private TextField txtTimePeriod;
    @FXML private TextField txtCourseFee;

    //   lblCourseID is a Label (auto-generated, read-only) — was TextField before
    @FXML private Label lblCourseID;

    //  Buttons
    @FXML private Button btnSave;
    @FXML private Button btnUpdate;
    @FXML private Button btnDelete;
    @FXML private Button btnReset;

    private final CourseBO courseBO =
            (CourseBO) BOFactory.getInstance().getBO(BOFactory.BoTypes.COURSE);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //  Bind table columns to CourseTM property names
        colCourseID.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colTimePeriod.setCellValueFactory(new PropertyValueFactory<>("timePeriod"));
        colCourseFee.setCellValueFactory(new PropertyValueFactory<>("courseFee"));

        //  Set initial button states
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        btnSave.setDisable(false);

        loadTableData();
        loadNextId();
    }

    //  LOAD TABLE
    private void loadTableData() {
        try {
            List<CourseDTO> courseList = courseBO.getAllCourse();
            ObservableList<CourseTM> obList = FXCollections.observableArrayList();

            for (CourseDTO dto : courseList) {
                //   Only pass 4 args — CourseTM(courseId, courseName, timePeriod, courseFee)
                obList.add(new CourseTM(
                        dto.getCourseId(),
                        dto.getCourseName(),
                        dto.getTimePeriod(),
                        dto.getCourseFee()
                ));
            }
            tblCourse.setItems(obList);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Failed to load course data: " + e.getMessage());
        }
    }

    //  LOAD NEXT AUTO ID
    private void loadNextId() {
        try {
            Long nextId = courseBO.getNextIdCourse();
            if (nextId == null) nextId = 1L;
            lblCourseID.setText(String.format("C%03d", nextId));
        } catch (Exception e) {
            e.printStackTrace();
            lblCourseID.setText("C001"); // safe fallback
        }
    }

    //  SAVE
    @FXML
    public void saveOnAction(ActionEvent actionEvent) {
        if (!validateInput()) return;

        CourseDTO dto = new CourseDTO(
                lblCourseID.getText(),
                txtCourseName.getText().trim(),
                txtTimePeriod.getText().trim(),
                txtCourseFee.getText().trim()
        );

        try {
            if (courseBO.saveCourse(dto)) {
                showAlert(Alert.AlertType.INFORMATION, "Course saved successfully!");
                resetPage();
            } else {
                showAlert(Alert.AlertType.ERROR, " Failed to save course.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error saving course: " + e.getMessage());
        }
    }

    //  UPDATE
    @FXML
    public void updateOnAction(ActionEvent actionEvent) {
        if (!validateInput()) return;

        //  Guard: must have a course selected
        if (lblCourseID.getText() == null || lblCourseID.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Please select a course from the table first.");
            return;
        }

        CourseDTO dto = new CourseDTO(
                lblCourseID.getText(),
                txtCourseName.getText().trim(),
                txtTimePeriod.getText().trim(),
                txtCourseFee.getText().trim()
        );

        try {
            if (courseBO.updateCourse(dto)) {
                showAlert(Alert.AlertType.INFORMATION, " Course updated successfully!");
                resetPage();
            } else {
                showAlert(Alert.AlertType.ERROR, " Failed to update course.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error updating course: " + e.getMessage());
        }
    }

    // DELETE
    @FXML
    public void deleteOnAction(ActionEvent actionEvent) {
        if (lblCourseID.getText() == null || lblCourseID.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Please select a course from the table first.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Delete course " + lblCourseID.getText() + "?\nThis action cannot be undone.",
                ButtonType.YES, ButtonType.NO);
        confirm.setTitle("Confirm Delete");
        confirm.setHeaderText(null);
        Optional<ButtonType> result = confirm.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            try {
                if (courseBO.deleteCourse(lblCourseID.getText())) {
                    showAlert(Alert.AlertType.INFORMATION, " Course deleted successfully!");
                    resetPage();
                } else {
                    showAlert(Alert.AlertType.ERROR, " Failed to delete course.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error deleting course: " + e.getMessage());
            }
        }
    }

    //  RESET
    @FXML
    public void resetOnAction(ActionEvent actionEvent) {
        resetPage();
    }

    // TABLE CLICK
    @FXML
    public void onClickTable(MouseEvent mouseEvent) {
        CourseTM selected = tblCourse.getSelectionModel().getSelectedItem();
        if (selected != null) {
            // Populate form from selected row
            lblCourseID.setText(selected.getCourseId());
            txtCourseName.setText(selected.getCourseName());
            txtTimePeriod.setText(selected.getTimePeriod());
            txtCourseFee.setText(selected.getCourseFee());

            //  Switch buttons: disable Save, enable Update/Delete
            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

    //  HELPERS

    private void resetPage() {
        txtCourseName.clear();
        txtTimePeriod.clear();
        txtCourseFee.clear();
        tblCourse.getSelectionModel().clearSelection();

        //  Reload next auto ID and table data
        loadNextId();
        loadTableData();

        //  Reset button states
        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    private boolean validateInput() {
        String name = txtCourseName.getText().trim();
        String period = txtTimePeriod.getText().trim();
        String fee = txtCourseFee.getText().trim();

        if (name.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Course Name is required.");
            txtCourseName.requestFocus();
            return false;
        }
        if (name.length() < 3 || name.length() > 50) {
            showAlert(Alert.AlertType.WARNING, "Course Name must be 3–50 characters.");
            txtCourseName.requestFocus();
            return false;
        }
        if (period.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Time Period is required.");
            txtTimePeriod.requestFocus();
            return false;
        }
        if (fee.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Course Fee is required.");
            txtCourseFee.requestFocus();
            return false;
        }
        //  Validate fee is a valid number
        try {
            double feeVal = Double.parseDouble(fee);
            if (feeVal < 0) {
                showAlert(Alert.AlertType.WARNING, "Course Fee cannot be negative.");
                txtCourseFee.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.WARNING, "Course Fee must be a valid number (e.g. 15000.00).");
            txtCourseFee.requestFocus();
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