package com.jobarbosa.gestortarefas;

import java.io.File;
import java.io.IOException;

public class TasksManagementController {
    private Tasks tasks = new Tasks();
    private static final String TASKS_FILE = "data/tasks.dat";

    /**
     * Loads tasks from the persistent file. Call this on app startup.
     * If the data directory does not exist, it is created.
     */
    public void loadTasks() {
        ensureDataDirectory();
        try {
            tasks.loadFromFile(TASKS_FILE);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Could not load tasks: " + e.getMessage());
        }
    }

    /**
     * Saves tasks to the persistent file. Call this on task changes or app exit.
     * If the data directory does not exist, it is created.
     */
    public void saveTasks() {
        ensureDataDirectory();
        try {
            tasks.saveToFile(TASKS_FILE);
        } catch (IOException e) {
            System.err.println("Could not save tasks: " + e.getMessage());
        }
    }

    /**
     * Adds a new task and saves the updated task list.
     *
     * @param task the task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
        saveTasks();
    }

    /**
     * Removes a task and saves the updated task list.
     *
     * @param task the task to remove
     */
    public void removeTask(Task task) {
        tasks.remove(task);
        saveTasks();
    }

    /**
     * Returns the Tasks instance for task management operations.
     */
    public Tasks getTasks() {
        return tasks;
    }

    /**
     * Ensures the data directory exists.
     */
    private void ensureDataDirectory() {
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
    }
}
