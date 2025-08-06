package com.jobarbosa.gestortarefas;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of Task objects, providing methods to add, remove, and list tasks.
 */
public class Tasks {
    /** Internal list that stores the tasks. */
    private final List<Task> list = new ArrayList<>();

    /**
     * Adds a task to the list.
     *
     * @param task the task to be added
     */
    public void add(Task task) { list.add(task); }

    /**
     * Removes a task from the list.
     *
     * @param task the task to be removed
     */
    public void remove(Task task) { list.remove(task); }

    /**
     * Returns a list of all tasks.
     *
     * @return a new list containing all tasks
     */
    public List<Task> listAll() { return new ArrayList<>(list); }

    /**
     * Returns a list of tasks with the specified priority.
     *
     * @param priority the priority to filter by
     * @return a list of tasks with the given priority
     */
    public List<Task> getByPriority(Task.Priority priority) {
        List<Task> result = new ArrayList<>();
        for (Task task : list) {
            if (task.getPriority() == priority) {
                result.add(task);
            }
        }
        return result;
    }

    /**
     * Returns a list of completed tasks.
     *
     * @return a list of completed tasks
     */
    public List<Task> getCompleted() {
        List<Task> result = new ArrayList<>();
        for (Task task : list) {
            if (task.isCompleted()) {
                result.add(task);
            }
        }
        return result;
    }

    /**
     * Returns a list of tasks to be done by a given due date (inclusive).
     *
     * @param dueDate the due date to filter by
     * @return a list of tasks with due date on or before the given date and not completed
     */
    public List<Task> getToBeDoneBy(java.time.LocalDate dueDate) {
        List<Task> result = new ArrayList<>();
        for (Task task : list) {
            if (!task.isCompleted() && task.getDueDate() != null && !task.getDueDate().isAfter(dueDate)) {
                result.add(task);
            }
        }
        return result;
    }

    /**
     * Returns a list of tasks with end dates between two dates (inclusive).
     *
     * @param start the start date (inclusive)
     * @param end the end date (inclusive)
     * @return a list of tasks completed between the two dates
     */
    public List<Task> getCompletedBetween(java.time.LocalDate start, java.time.LocalDate end) {
        List<Task> result = new ArrayList<>();
        for (Task task : list) {
            if (task.isCompleted() && task.getEndDate() != null &&
                (!task.getEndDate().isBefore(start) && !task.getEndDate().isAfter(end))) {
                result.add(task);
            }
        }
        return result;
    }
}