package com.jobarbosa.gestortarefas;

import javafx.application.Application;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;

/**
 * Main application entry point for GestorTarefasJavaFX.
 * Initializes controllers and loads persisted data.
 */
public class MainApp extends Application {
    private UserManagementController userController;
    private TasksManagementController tasksController;

    @Override
    public void start(Stage primaryStage) {
        userController = new UserManagementController();
        tasksController = new TasksManagementController();
        userController.loadUsers();
        tasksController.loadTasks();

        LoginController loginController = new LoginController(userController);
        RegistrationController registrationController = new RegistrationController(userController);
        TaskManagerController taskManagerController = new TaskManagerController(tasksController);

        // Navigation logic
        loginController.setOnLoginSuccess(() -> taskManagerController.show(primaryStage));
        loginController.setOnRegisterRequest(() -> registrationController.show(primaryStage));
        registrationController.setOnRegisterRequest(() -> loginController.show(primaryStage));

        // Show login screen first
        loginController.show(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public UserManagementController getUserController() {
        return userController;
    }

    public TasksManagementController getTasksController() {
        return tasksController;
    }
}
