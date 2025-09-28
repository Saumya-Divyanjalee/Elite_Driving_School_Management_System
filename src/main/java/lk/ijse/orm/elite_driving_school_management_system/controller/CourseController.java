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
import lk.ijse.orm.elite_driving_school_management_system.util.RegexUtil;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CourseController implements Initializable {

    @FXML
    private TableColumn<CourseTM, String> colCourseID;

    @FXML
    private TableColumn<CourseTM, String> colCourseName;

    @FXML
    private TableColumn<CourseTM, String> colTimePeriod;

    @FXML
    private TableColumn<CourseTM, String> colCourseFee;

    @FXML
    private TableView<CourseTM> tblCourse;

    @FXML
    private TextField txtCourseName;

    @FXML
    private TextField txtTimePeriod;

    @FXML
    private TextField txtCourseFee;

    @FXML
    private Label lblCourseID;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    private final CourseBO courseBO =
            (CourseBO) BOFactory.getInstance().getBO(BOFactory.BoTypes.COURSE);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCourseID.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colTimePeriod.setCellValueFactory(new PropertyValueFactory<>("timePeriod"));
        colCourseFee.setCellValueFactory(new PropertyValueFactory<>("courseFee"));

        try {
            loadTableData();
            loadNextId();
            resetPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong during initialization...").show();
        }
    }

    private void loadTableData() {
        try {
            List<CourseDTO> courseList = courseBO.getAllCourse();
            ObservableList<CourseTM> obList = FXCollections.observableArrayList();
            for (CourseDTO dto : courseList) {
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
            new Alert(Alert.AlertType.ERROR, "Failed to load course data!").show();
        }
    }

    private void resetPage() {
        try {
            txtCourseName.clear();
            txtTimePeriod.clear();
            txtCourseFee.clear();

            loadTableData();
            loadNextId();

            btnSave.setDisable(false);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Oops! Something went wrong during reset!").show();
        }
    }

    private void loadNextId() {
        try {
            Long nextId = courseBO.getNextIdCourse();
            if (nextId == null) {
                nextId = 1L; // Start with 1 if no courses exist
            }
            String nextIdStr = String.format("C%03d", nextId);
            // Check if lblCourseID is not null before setting text
            if (lblCourseID != null) {
                lblCourseID.setText(nextIdStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Set a default value if there's an error
            if (lblCourseID != null) {
                lblCourseID.setText("C001");
            }
        }
    }

    @FXML
    public void saveOnAction(ActionEvent actionEvent) {
        if (!validateInput()) return;

        // Check if lblCourseID is not null and has text
        if (lblCourseID == null || lblCourseID.getText() == null || lblCourseID.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Course ID is not set!").show();
            return;
        }

        CourseDTO dto = new CourseDTO(
                lblCourseID.getText(),
                txtCourseName.getText(),
                txtTimePeriod.getText(),
                txtCourseFee.getText()
        );
        try {
            boolean saved = courseBO.saveCourse(dto);
            if (saved) {
                resetPage();
                new Alert(Alert.AlertType.INFORMATION, "Course saved successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Course could not be saved!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Oops! Course could not be saved! " + e.getMessage()).show();
        }
    }

    @FXML
    public void updateOnAction(ActionEvent actionEvent) {
        if (!validateInput()) return;

        // Check if lblCourseID is not null and has text
        if (lblCourseID == null || lblCourseID.getText() == null || lblCourseID.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "No course selected for update!").show();
            return;
        }

        CourseDTO dto = new CourseDTO(
                lblCourseID.getText(),
                txtCourseName.getText(),
                txtTimePeriod.getText(),
                txtCourseFee.getText()
        );
        try {
            boolean updated = courseBO.updateCourse(dto);
            if (updated) {
                resetPage();
                new Alert(Alert.AlertType.INFORMATION, "Course updated successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Course could not be updated!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Oops! Course could not be updated! " + e.getMessage()).show();
        }
    }

    @FXML
    public void deleteOnAction(ActionEvent actionEvent) {
        // Check if lblCourseID is not null and has text
        if (lblCourseID == null || lblCourseID.getText() == null || lblCourseID.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "No course selected for deletion!").show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete this course?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            try {
                boolean deleted = courseBO.deleteCourse(lblCourseID.getText());
                if (deleted) {
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION, "Course deleted successfully!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Course could not be deleted!").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Oops! Course could not be deleted! " + e.getMessage()).show();
            }
        }
    }

    @FXML
    public void resetOnAction(ActionEvent actionEvent) {
        resetPage();
    }

    @FXML
    public void onClickTable(MouseEvent mouseEvent) {
        CourseTM selected = tblCourse.getSelectionModel().getSelectedItem();
        if (selected != null) {
            lblCourseID.setText(selected.getCourseId());
            txtCourseName.setText(selected.getCourseName());
            txtTimePeriod.setText(selected.getTimePeriod());
            txtCourseFee.setText(selected.getCourseFee());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

    private boolean validateInput() {
        try {
            // Validate Course Name
            RegexUtil.validateRequired(txtCourseName.getText(), "Course Name");
            if (!RegexUtil.isValidName(txtCourseName.getText())) {
                new Alert(Alert.AlertType.WARNING, "Course name must be 3-50 letters only.").show();
                return false;
            }

            // Validate Time Period
            RegexUtil.validateRequired(txtTimePeriod.getText(), "Time Period");
            if (!RegexUtil.isValidDuration(txtTimePeriod.getText())) {
                new Alert(Alert.AlertType.WARNING, "Time period must be a number followed by 'month' or 'months'.").show();
                return false;
            }

            // Validate Course Fee
            RegexUtil.validateRequired(txtCourseFee.getText(), "Course Fee");
            if (!RegexUtil.isValidFee(txtCourseFee.getText())) {
                new Alert(Alert.AlertType.WARNING, "Course fee must be a valid number (up to 2 decimals).").show();
                return false;
            }

            return true;
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
            return false;
        }
    }
}