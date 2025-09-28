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
import lk.ijse.orm.elite_driving_school_management_system.bo.custom.LessonBO;
import lk.ijse.orm.elite_driving_school_management_system.dto.LessonDTO;
import lk.ijse.orm.elite_driving_school_management_system.exception.LoginException;
import lk.ijse.orm.elite_driving_school_management_system.tm.LessonTM;
import lk.ijse.orm.elite_driving_school_management_system.util.RegexUtil;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class LessonSchedulingController implements Initializable {

    public TextField txtLesson;
    public ComboBox cmbInstructor;
    public ComboBox cmbStudent;
    public ComboBox cmbCourse;
    public TableColumn colDate;
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
    private TableColumn<LessonTM,String> colCourseID;

    @FXML
    private TableColumn<LessonTM,String> colEndTime;

    @FXML
    private TableColumn<LessonTM,String> colInstructorID;

    @FXML
    private TableColumn<LessonTM,String> colName;

    @FXML
    private TableColumn<LessonTM,String> colStartTime;

    @FXML
    private TableColumn<LessonTM,String> colStudentID;

    @FXML
    private TableColumn<LessonTM,String> collessonID;

    @FXML
    private DatePicker datepicker;

    @FXML
    private TableView<LessonTM> tblLesson;

    @FXML
    private TextField txtName;

    // Changed from TextField to ComboBox to match FXML
    @FXML
    private ComboBox<String> cmbStartTime;

    @FXML
    private ComboBox<String> cmbEndTime;


    LessonBO lessonBO = (LessonBO) BOFactory.getInstance().getBO(BOFactory.BoTypes.LESSON);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        collessonID.setCellValueFactory(new PropertyValueFactory<>("lessonId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("lessonName"));
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colInstructorID.setCellValueFactory(new PropertyValueFactory<>("instructorId"));
        colCourseID.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("studentId"));

        try{
            // Initialize time combo boxes with time values
            initializeTimeComboBoxes();
            loadTableData();
            loadCourseId();
            loadInstructorId();
            loadStudentId();
            resetPage();
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
        }

    }

    private void initializeTimeComboBoxes() {
        // Create time options for combo boxes (every 30 minutes from 6:00 to 22:00)
        ObservableList<String> timeOptions = FXCollections.observableArrayList();
        for (int hour = 6; hour <= 22; hour++) {
            timeOptions.add(String.format("%02d:00", hour));
            if (hour < 22) {
                timeOptions.add(String.format("%02d:30", hour));
            }
        }

        cmbStartTime.setItems(timeOptions);
        cmbEndTime.setItems(timeOptions);
    }

    private void loadStudentId() {
        try {
            List<String> sId = lessonBO.getAllStudentIds();
            ObservableList<String> list = FXCollections.observableArrayList(sId);
            cmbStudent.setItems(list);

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong!-loadStudentId").show();
        }
    }

    private void loadInstructorId() {
        try {
            List<String> iId = lessonBO.getAllInstructorIds();
            ObservableList<String> list = FXCollections.observableArrayList(iId);
            cmbInstructor.setItems(list);
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong!-loadInstructorId").show();
        }
    }

    private void loadCourseId() {
        try {
            List<String> cId = lessonBO.getAllCourseIds();
            ObservableList<String> list = FXCollections.observableArrayList(cId);
            cmbCourse.setItems(list);

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong!-loadCourseId").show();
        }

    }

    private void loadTableData() throws Exception {
        try {
            List<LessonDTO> lessonDTOList = lessonBO.getAllLesson();

            ObservableList<LessonTM> observableList = FXCollections.observableArrayList();
            for (LessonDTO dto : lessonDTOList) {
                observableList.add(new LessonTM(
                        dto.getLessonId(),
                        dto.getLessonName(),
                        dto.getStartTime(),
                        dto.getEndTime(),
                        dto.getDate(),
                        dto.getInstructorId(),
                        dto.getStudentId(),  // Fixed order - was courseId before
                        dto.getCourseId()    // Fixed order - was studentId before
                ));
                tblLesson.setItems(observableList);

            }
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
        }
    }



    private void resetPage() {
        txtLesson.clear();
        txtName.clear();
        // Clear selection in combo boxes instead of clearing text
        cmbStartTime.getSelectionModel().clearSelection();
        cmbEndTime.getSelectionModel().clearSelection();
        datepicker.setValue(null);
        cmbInstructor.getSelectionModel().clearSelection();
        cmbStudent.getSelectionModel().clearSelection();
        cmbCourse.getSelectionModel().clearSelection();

        // Reset button states
        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        // Check if a lesson is selected
        if (txtLesson.getText() == null || txtLesson.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a lesson to delete!").show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to delete this lesson?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.YES){
            try{
                boolean deleted = lessonBO.deleteLesson(txtLesson.getText().trim());
                if(deleted){
                    resetPage();
                    loadTableData();
                    new Alert(Alert.AlertType.INFORMATION,"Deleted Successfully!").show();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Something went wrong! Delete Issue").show();
                }
            }catch (Exception e){
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR,"Something went wrong! Not work Delete Button").show();
            }
        }

    }


    @FXML
    void onClickTable(MouseEvent event) {
        LessonTM lessonTM = tblLesson.getSelectionModel().getSelectedItem();
        if(lessonTM != null){
            txtLesson.setText(String.valueOf(lessonTM.getLessonId()));
            txtName.setText(lessonTM.getLessonName());
            // Set the selected time values in combo boxes
            cmbStartTime.setValue(lessonTM.getStartTime());
            cmbEndTime.setValue(lessonTM.getEndTime());

            // Set the date in the date picker
            if (lessonTM.getDate() != null) {
                // Convert java.sql.Date to LocalDate without using toInstant()
                LocalDate localDate = ((java.sql.Date) lessonTM.getDate()).toLocalDate();
                datepicker.setValue(localDate);
            } else {
                datepicker.setValue(null);
            }

            // Set the combo box selections for instructor, student, and course
            if (lessonTM.getInstructorId() != null) {
                cmbInstructor.setValue(lessonTM.getInstructorId());
            } else {
                cmbInstructor.getSelectionModel().clearSelection();
            }

            if (lessonTM.getStudentId() != null) {
                cmbStudent.setValue(lessonTM.getStudentId());
            } else {
                cmbStudent.getSelectionModel().clearSelection();
            }

            if (lessonTM.getCourseId() != null) {
                cmbCourse.setValue(lessonTM.getCourseId());
            } else {
                cmbCourse.getSelectionModel().clearSelection();
            }

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

    @FXML
    void resetOnAction(ActionEvent event) {
        resetPage();

    }

    @FXML
    void saveOnAction(ActionEvent event) {
        try {
            validateLessonInputs();

            // Convert LocalDate to String properly
            String dateStr = "";
            if (datepicker.getValue() != null) {
                dateStr = datepicker.getValue().toString();
            }

            LessonDTO dto = new LessonDTO(
                    txtName.getText(),
                    cmbStartTime.getValue(), // Get value from combo box
                    cmbEndTime.getValue(),   // Get value from combo box
                    dateStr,
                    cmbInstructor.getSelectionModel().getSelectedItem().toString(),
                    cmbCourse.getSelectionModel().getSelectedItem().toString(),
                    cmbStudent.getSelectionModel().getSelectedItem().toString()
            );

            if (lessonBO.saveLesson(dto)) {
                new Alert(Alert.AlertType.INFORMATION, "Lesson added successfully!").show();
                loadTableData();
                resetPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save lesson!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error saving lesson: " + e.getMessage()).show();
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        // Check if a lesson is selected
        if (txtLesson.getText() == null || txtLesson.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a lesson to update!").show();
            return;
        }

        try {
            validateLessonInputs();

            // Convert LocalDate to String properly
            String dateStr = "";
            if (datepicker.getValue() != null) {
                dateStr = datepicker.getValue().toString();
            }

            LessonDTO dto = new LessonDTO(
                    Long.parseLong(txtLesson.getText().trim()),
                    txtName.getText(),
                    cmbStartTime.getValue(), // Get value from combo box
                    cmbEndTime.getValue(),   // Get value from combo box
                    dateStr,
                    cmbInstructor.getSelectionModel().getSelectedItem().toString(),
                    cmbCourse.getSelectionModel().getSelectedItem().toString(),
                    cmbStudent.getSelectionModel().getSelectedItem().toString()
            );

            if (lessonBO.updateLesson(dto)) {
                new Alert(Alert.AlertType.INFORMATION, "Lesson updated successfully!").show();
                loadTableData();
                resetPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update lesson!").show();
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid lesson ID format!").show();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error updating lesson: " + e.getMessage()).show();
        }

    }

    private void validateLessonInputs() throws Exception {
        // Validate required fields
        try {
            RegexUtil.validateRequired(txtName.getText(), "Lesson Name");
            // Validate that time combo boxes have selections
            if (cmbStartTime.getValue() == null) {
                throw new Exception("Start Time is required");
            }
            if (cmbEndTime.getValue() == null) {
                throw new Exception("End Time is required");
            }
            if (datepicker.getValue() == null) {
                throw new Exception("Date is required");
            }
            if (cmbInstructor.getSelectionModel().getSelectedItem() == null) {
                throw new Exception("Instructor is required");
            }
            if (cmbCourse.getSelectionModel().getSelectedItem() == null) {
                throw new Exception("Course is required");
            }
            if (cmbStudent.getSelectionModel().getSelectedItem() == null) {
                throw new Exception("Student is required");
            }

            String startTime = cmbStartTime.getValue();
            String endTime = cmbEndTime.getValue();

            // Validate time format using proper time parsing
            if (!isValidTimeFormat(startTime)) {
                throw new Exception("Invalid Start Time format. Please use HH:mm format (e.g., 09:30 or 14:15)");
            }

            if (!isValidTimeFormat(endTime)) {
                throw new Exception("Invalid End Time format. Please use HH:mm format (e.g., 09:30 or 14:15)");
            }

            // Additional validation to ensure start time is before end time
            if (!isStartTimeBeforeEndTime(startTime, endTime)) {
                throw new Exception("Start time must be before end time");
            }
        } catch (lk.ijse.orm.elite_driving_school_management_system.exception.LoginException e) {
            throw new Exception(e.getMessage());
        }
    }

    private boolean isValidTimeFormat(String time) {
        if (time == null || time.isEmpty()) {
            return false;
        }
        try {
            // Handle various time formats
            // Try H:mm format (e.g., 9:30)
            LocalTime.parse(time, DateTimeFormatter.ofPattern("H:mm"));
            return true;
        } catch (DateTimeParseException e1) {
            try {
                // Try HH:mm format (e.g., 09:30)
                LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
                return true;
            } catch (DateTimeParseException e2) {
                try {
                    // Try H:mm:ss format (e.g., 9:30:00) and truncate seconds
                    LocalTime.parse(time, DateTimeFormatter.ofPattern("H:mm:ss"));
                    return true;
                } catch (DateTimeParseException e3) {
                    try {
                        // Try HH:mm:ss format (e.g., 09:30:00) and truncate seconds
                        LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm:ss"));
                        return true;
                    } catch (DateTimeParseException e4) {
                        return false;
                    }
                }
            }
        }
    }

    private boolean isStartTimeBeforeEndTime(String startTime, String endTime) {
        try {
            LocalTime start = parseTime(startTime);
            LocalTime end = parseTime(endTime);
            return start.isBefore(end);
        } catch (DateTimeParseException e) {
            return false; // If parsing fails, consider it invalid
        }
    }

    private LocalTime parseTime(String time) throws DateTimeParseException {
        // Try multiple formats and return the first one that works
        try {
            return LocalTime.parse(time, DateTimeFormatter.ofPattern("H:mm"));
        } catch (DateTimeParseException e1) {
            try {
                return LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
            } catch (DateTimeParseException e2) {
                try {
                    // Parse with seconds but return without seconds
                    return LocalTime.parse(time, DateTimeFormatter.ofPattern("H:mm:ss"));
                } catch (DateTimeParseException e3) {
                    // Parse with seconds but return without seconds
                    return LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm:ss"));
                }
            }
        }
    }

}