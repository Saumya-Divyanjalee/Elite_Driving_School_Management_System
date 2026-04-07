package lk.ijse.orm.elite_driving_school_management_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lk.ijse.orm.elite_driving_school_management_system.config.FactoryConfiguration;
import lk.ijse.orm.elite_driving_school_management_system.entity.User;
import lk.ijse.orm.elite_driving_school_management_system.exception.LoginException;
import lk.ijse.orm.elite_driving_school_management_system.util.PasswordEncryptor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class LoginController {

    @FXML
    private Button btnLogin;

    @FXML
    private ComboBox<String> cmbUser;

    @FXML
    private TextField txtUserName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    public void initialize() {
        cmbUser.getItems().addAll("ADMIN", "RECEPTIONIST");
    }

    @FXML
    void loginOnAction(ActionEvent event) {
        try {
            String usernameOrEmail = txtUserName.getText().trim();
            String password = txtPassword.getText().trim();
            String role = cmbUser.getValue();

            //   Simple, clear validation — no confusing regex conditions
            if (usernameOrEmail.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Validation Error", "Please enter your username or email.");
                return;
            }
            if (password.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Validation Error", "Please enter your password.");
                return;
            }
            if (role == null) {
                showAlert(Alert.AlertType.WARNING, "Validation Error", "Please select a role (ADMIN or RECEPTIONIST).");
                return;
            }
            if (usernameOrEmail.length() < 3) {
                showAlert(Alert.AlertType.WARNING, "Validation Error", "Username or email must be at least 3 characters.");
                return;
            }

            //    Query by username OR email, matching the selected role
            try (Session session = FactoryConfiguration.getInstance().getSession()) {
                Transaction tx = session.beginTransaction();

                Query<User> query = session.createQuery(
                        "FROM User u WHERE (u.username = :uname OR u.email = :email) AND u.role = :role",
                        User.class
                );
                query.setParameter("uname", usernameOrEmail);
                query.setParameter("email", usernameOrEmail);
                query.setParameter("role", role);

                User user = query.uniqueResult();
                tx.commit();

                if (user == null) {
                    showAlert(Alert.AlertType.ERROR, "Login Failed", "No user found with that username/email for role: " + role);
                    return;
                }

                //   Verify hashed password using BCrypt
                boolean valid = PasswordEncryptor.verifyPassword(password, user.getPassword());
                if (!valid) {
                    showAlert(Alert.AlertType.ERROR, "Login Failed", "Incorrect password. Please try again.");
                    return;
                }

                //   Login success — open correct dashboard
                openDashboard(role);

            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "Something went wrong during login: " + e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Unexpected Error", e.getMessage());
        }
    }

    private void openDashboard(String role) {
        try {
            String fxmlPath;
            String title;

            //   Make sure these file names EXACTLY match your files in /view/ folder
            if ("ADMIN".equals(role)) {
                fxmlPath = "/view/Admin-dashboard.fxml";
                title = "Admin Dashboard";
            } else {
                fxmlPath = "/view/Receptionist-dashboard.fxml";
                title = "Receptionist Dashboard";
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            //   Get the current stage and replace the scene
            Stage stage = (Stage) txtUserName.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.setResizable(false);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Navigation Error",
                    "Failed to open dashboard. Check that FXML file exists: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}