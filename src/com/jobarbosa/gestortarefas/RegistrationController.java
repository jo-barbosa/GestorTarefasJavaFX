package com.jobarbosa.gestortarefas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for the user registration screen.
 * Handles user input and registration logic.
 */
public class RegistrationController {
    private UserManagementController userController;
    private Runnable onRegisterRequest;

    public RegistrationController(UserManagementController userController) {
        this.userController = userController;
    }

    /**
     * Shows the registration UI.
     * @param primaryStage the main stage
     */
    public void show(Stage primaryStage) {
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        Button registerButton = new Button("Register");
        Label feedbackLabel = new Label();

        registerButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String email = emailField.getText();
            if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                feedbackLabel.setText("All fields are required.");
                return;
            }
            User newUser = new User(userController.getUsers().listAll().size() + 1, username, password, email, java.time.LocalDate.now(), User.Role.USER);
            boolean success = userController.registerUser(newUser);
            if (success) {
                feedbackLabel.setText("Registration successful! You can now log in.");
                usernameField.clear();
                passwordField.clear();
                emailField.clear();
                if (onRegisterRequest != null) {
                    onRegisterRequest.run();
                }
            } else {
                feedbackLabel.setText("Username already exists.");
            }
        });

        VBox vbox = new VBox(usernameLabel, usernameField, passwordLabel, passwordField, emailLabel, emailField, registerButton, feedbackLabel);
        vbox.setSpacing(8);
        Scene scene = new Scene(vbox, 350, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("User Registration");
        primaryStage.show();
    }

    /**
     * Sets the action to perform after registration (e.g., return to login screen).
     * @param action the Runnable to execute
     */
    public void setOnRegisterRequest(Runnable action) {
        this.onRegisterRequest = action;
    }
}
