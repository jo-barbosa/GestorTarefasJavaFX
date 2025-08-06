package com.jobarbosa.gestortarefas;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a user with a unique id, username, password, email, registration date, and role.
 */
public class User implements Serializable {
    /** Unique identifier for the user. */
    private final int id;
    /** Username. */
    private String username;
    /** User password (stored in plain text for example purposes, use hashing in production). */
    private String password;
    /** User email. */
    private String email;
    /** User registration date. */
    private LocalDate registrationDate;
    /** User role (e.g., ADMIN, USER). */
    private Role role;

    /**
     * Enum representing the user's role.
     */
    public enum Role {
        ADMIN,
        USER
    }

    /**
     * Full constructor for User.
     *
     * @param id unique identifier
     * @param username username
     * @param password password
     * @param email email
     * @param registrationDate registration date
     * @param role user role
     */
    public User(int id, String username, String password, String email, LocalDate registrationDate, Role role) {
        if (username == null || username.isEmpty()) throw new IllegalArgumentException("username cannot be empty");
        if (email == null || email.isEmpty()) throw new IllegalArgumentException("email cannot be empty");
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.registrationDate = registrationDate != null ? registrationDate : LocalDate.now();
        this.role = role != null ? role : Role.USER;
    }

    /**
     * Minimal constructor for User.
     *
     * @param id unique identifier
     * @param username username
     */
    public User(int id, String username) {
        this(id, username, null, null, LocalDate.now(), Role.USER);
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public void setUsername(String username) {
        if (username == null || username.isEmpty()) throw new IllegalArgumentException("username cannot be empty");
        this.username = username;
    }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) {
        if (email == null || email.isEmpty()) throw new IllegalArgumentException("email cannot be empty");
        this.email = email;
    }
    public LocalDate getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    /**
     * Map of task groups, where each group name maps to a list of tasks.
     */
    private final Map<String, List<Task>> taskGroups = new HashMap<>();

    /**
     * Adds a task to a group. If the group does not exist, it is created.
     *
     * @param groupName the name of the group
     * @param task the task to add
     */
    public void addTaskToGroup(String groupName, Task task) {
        taskGroups.computeIfAbsent(groupName, k -> new ArrayList<>()).add(task);
    }

    /**
     * Gets the list of tasks in a group.
     *
     * @param groupName the name of the group
     * @return the list of tasks in the group, or an empty list if the group does not exist
     */
    public List<Task> getTasksInGroup(String groupName) {
        return taskGroups.getOrDefault(groupName, new ArrayList<>());
    }

    /**
     * Removes a task from a group.
     *
     * @param groupName the name of the group
     * @param task the task to remove
     * @return true if the task was removed, false otherwise
     */
    public boolean removeTaskFromGroup(String groupName, Task task) {
        List<Task> group = taskGroups.get(groupName);
        if (group != null) {
            return group.remove(task);
        }
        return false;
    }

    /**
     * Gets all group names for this user.
     *
     * @return a list of group names
     */
    public List<String> getGroupNames() {
        return new ArrayList<>(taskGroups.keySet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", registrationDate=" + registrationDate +
                ", role=" + role +
                '}';
    }
}