package com.jobarbosa.gestortarefas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Optional;

/**
 * Controller for the login screen.
 * Handles user authentication and UI logic.
 */
public class LoginController {
    private UserManagementController userController;
    private Runnable onLoginSuccess;
    private Runnable onRegisterRequest;

    public LoginController(UserManagementController userController) {
        this.userController = userController;
    }

    /**
     * Sets the action to perform on successful login.
     */
    public void setOnLoginSuccess(Runnable action) {
        this.onLoginSuccess = action;
    }

    /**
     * Sets the action to perform when registration is requested.
     */
    public void setOnRegisterRequest(Runnable action) {
        this.onRegisterRequest = action;
    }

    /**
     * Shows the login UI.
     * @param primaryStage the main stage
     */
    public void show(Stage primaryStage) {
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");
        Label feedbackLabel = new Label();

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (username.isEmpty() || password.isEmpty()) {
                feedbackLabel.setText("Username and password are required.");
                return;
            }
            Optional<User> user = userController.getUsers().authenticate(username, password);
            if (user.isPresent()) {
                feedbackLabel.setText("Login successful!");
                if (onLoginSuccess != null) onLoginSuccess.run();
            } else {
                feedbackLabel.setText("Invalid username or password.");
            }
        });

        registerButton.setOnAction(e -> {
            if (onRegisterRequest != null) onRegisterRequest.run();
        });

        VBox vbox = new VBox(usernameLabel, usernameField, passwordLabel, passwordField, loginButton, registerButton, feedbackLabel);
        vbox.setSpacing(8);
        Scene scene = new Scene(vbox, 350, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }
}
