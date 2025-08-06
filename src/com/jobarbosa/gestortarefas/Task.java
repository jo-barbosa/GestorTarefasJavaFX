package com.jobarbosa.gestortarefas;

/**
 * Represents a task with a unique id, description, and completion status.
 */
public class Task {
    /** Static variable to generate unique task IDs.
     * This is incremented each time a new Task is created.
     */
    private static int nextId = 1;
    /** Unique identifier for the task. */
    private final int id;
    /** Task description. */
    private String description;
    /** Indicates if the task is completed. */
    private boolean completed;
    /** Task priority. */
    private Priority priority;
    /** Due date for the task. */
    private java.time.LocalDate dueDate;
    /** End date when the task is completed. */
    private java.time.LocalDate endDate;

    /**
     * Enum representing the priority of a task.
     */
    public enum Priority {
        LOW,
        MEDIUM,
        HIGH
    }

    /**
     * Constructs a new Task with the specified id and description.
     * The task is initially marked as not completed.
     *
     * @param id unique identifier for the task
     * @param description task description
     */
    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.completed = false;
    }

    /**
     * Constructs a new Task with the specified id, description, priority, and due date.
     * The task is initially marked as not completed and has no end date.
     *
     * @param id unique identifier for the task
     * @param description task description
     * @param priority priority of the task
     * @param dueDate due date for the task
     */
    public Task(int id, String description, Priority priority, java.time.LocalDate dueDate) {
        this.id = id;
        this.description = description;
        this.completed = false;
        this.priority = priority;
        this.dueDate = dueDate;
        this.endDate = null;
    }

    /**
     * Returns the unique identifier of the task.
     *
     * @return the task id
     */
    public int getId() { return id; }

    /**
     * Returns the description of the task.
     *
     * @return the task description
     */
    public String getDescription() { return description; }

    /**
     * Sets the description of the task.
     *
     * @param description new task description
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Returns whether the task is completed.
     *
     * @return true if the task is completed, false otherwise
     */
    public boolean isCompleted() { return completed; }

    /**
     * Returns the priority of the task.
     *
     * @return the task priority
     */
    public Priority getPriority() { return priority; }

    /**
     * Sets the priority of the task.
     *
     * @param priority new task priority
     */
    public void setPriority(Priority priority) { this.priority = priority; }

    /**
     * Returns the due date of the task.
     *
     * @return the due date
     */
    public java.time.LocalDate getDueDate() { return dueDate; }

    /**
     * Sets the due date of the task.
     *
     * @param dueDate new due date
     */
    public void setDueDate(java.time.LocalDate dueDate) { this.dueDate = dueDate; }

    /**
     * Returns the end date of the task.
     *
     * @return the end date, or null if not completed
     */
    public java.time.LocalDate getEndDate() { return endDate; }

    /**
     * Marks the task as completed and sets the end date to today.
     */
    public void markCompleted() {
        this.completed = true;
        this.endDate = java.time.LocalDate.now();
    }
}