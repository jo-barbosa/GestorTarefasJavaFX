package com.jobarbosa.gestortarefas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;

/**
 * Controller for the main task management screen.
 * Handles displaying, adding, and removing tasks.
 */
public class TaskManagerController {
    private TasksManagementController tasksController;
    private ObservableList<Task> taskList;
    private TableView<Task> tableView;

    public TaskManagerController(TasksManagementController tasksController) {
        this.tasksController = tasksController;
        this.taskList = FXCollections.observableArrayList(tasksController.getTasks().listAll());
    }

    /**
     * Initializes and shows the main task management UI.
     * @param primaryStage the main stage
     */
    public void show(Stage primaryStage) {
        tableView = new TableView<>(taskList);
        TableColumn<Task, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Task, String> descCol = new TableColumn<>("Description");
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        TableColumn<Task, Boolean> completedCol = new TableColumn<>("Completed");
        completedCol.setCellValueFactory(new PropertyValueFactory<>("completed"));
        completedCol.setCellFactory(col -> new TableCell<Task, Boolean>() {
            @Override
            protected void updateItem(Boolean completed, boolean empty) {
                super.updateItem(completed, empty);
                if (empty || completed == null) {
                    setText("");
                } else {
                    setText(completed ? "✔" : "✘");
                }
            }
        });
        TableColumn<Task, Task.Priority> priorityCol = new TableColumn<>("Priority");
        priorityCol.setCellValueFactory(new PropertyValueFactory<>("priority"));
        TableColumn<Task, java.time.LocalDate> dueDateCol = new TableColumn<>("Due Date");
        dueDateCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        tableView.getColumns().addAll(idCol, descCol, completedCol, priorityCol, dueDateCol);

        TableColumn<Task, Void> completeCol = new TableColumn<>("Mark Completed");
        completeCol.setCellFactory(col -> new TableCell<Task, Void>() {
            private final Button btn = new Button("Complete");
            {
                btn.setOnAction(e -> {
                    Task task = getTableView().getItems().get(getIndex());
                    if (!task.isCompleted()) {
                        task.markCompleted();
                        tasksController.saveTasks();
                        taskList.setAll(tasksController.getTasks().listAll());
                    }
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableView().getItems().get(getIndex()).isCompleted()) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                }
            }
        });
        tableView.getColumns().add(completeCol);

        TextField descField = new TextField();
        descField.setPromptText("Task description");
        ComboBox<Task.Priority> priorityBox = new ComboBox<>();
        priorityBox.getItems().setAll(Task.Priority.values());
        priorityBox.setValue(Task.Priority.MEDIUM);
        DatePicker dueDatePicker = new DatePicker();
        dueDatePicker.setPromptText("Due date");

        Button addButton = new Button("Add Task");
        addButton.setOnAction(e -> {
            String desc = descField.getText();
            Task.Priority priority = priorityBox.getValue();
            java.time.LocalDate dueDate = dueDatePicker.getValue();
            if (!desc.isEmpty() && dueDate != null) {
                Task newTask = new Task(taskList.size() + 1, desc, priority, dueDate);
                tasksController.addTask(newTask);
                taskList.setAll(tasksController.getTasks().listAll());
                descField.clear();
                priorityBox.setValue(Task.Priority.MEDIUM);
                dueDatePicker.setValue(null);
            }
        });

        Button removeButton = new Button("Remove Selected");
        removeButton.setOnAction(e -> {
            Task selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                tasksController.removeTask(selected);
                taskList.setAll(tasksController.getTasks().listAll());
            }
        });

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> {
            // Go back to login screen
            new LoginController(new UserManagementController()).show(primaryStage);
        });

        VBox vbox = new VBox(tableView, descField, priorityBox, dueDatePicker, addButton, removeButton, exitButton);
        Scene scene = new Scene(vbox, 700, 450);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Task Manager");
        primaryStage.show();
    }
}
