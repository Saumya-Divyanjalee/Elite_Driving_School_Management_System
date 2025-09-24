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
import lk.ijse.orm.elite_driving_school_management_system.util.RegexUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class LoginController {

    public TextField txtEmail;
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

            RegexUtil.validateRequired(usernameOrEmail, "Username/Email");
            RegexUtil.validateRequired(password, "Password");
            if (role == null) throw new LoginException("Please select a role");

            if (!RegexUtil.isValidEmail(usernameOrEmail) && usernameOrEmail.length() < 3) {
                throw new LoginException("Enter valid Email or Username");
            }

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
                    throw new LoginException("User not found for role: " + role);
                }

                boolean valid = PasswordEncryptor.verifyPassword(password, user.getPassword());
                if (!valid) {
                    throw new LoginException("Invalid password!");
                }

                openDashboard(role);

            } catch (LoginException ex) {
                showAlert(Alert.AlertType.ERROR, "Login Failed", ex.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "Something went wrong!");
            }

        } catch (LoginException e) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", e.getMessage());
        }
    }

    private void openDashboard(String role) {
        try {
            String fxmlPath = "";
            String title = "";

            if ("ADMIN".equals(role)) {
                fxmlPath = "/view/Admin-dashboard.fxml";
                title = "Admin Dashboard";
            } else if ("RECEPTIONIST".equals(role)) {
                fxmlPath = "/view/Receptionist-dashboard.fxml";
                title = "Receptionist Dashboard";
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = (Stage) txtUserName.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Dashboard loading failed!");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}
