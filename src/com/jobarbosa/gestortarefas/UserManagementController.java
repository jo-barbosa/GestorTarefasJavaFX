package com.jobarbosa.gestortarefas;

import java.io.File;
import java.io.IOException;

public class UserManagementController {
    private Users users = new Users();
    private static final String USERS_FILE = "data/users.dat";

    /**
     * Loads users from the persistent file. Call this on app startup.
     * If the data directory does not exist, it is created.
     */
    public void loadUsers() {
        ensureDataDirectory();
        try {
            users.loadFromFile(USERS_FILE);
        } catch (IOException | ClassNotFoundException e) {
            // File may not exist on first run, or may be corrupted
            System.err.println("Could not load users: " + e.getMessage());
        }
    }

    /**
     * Saves users to the persistent file. Call this on user changes or app exit.
     * If the data directory does not exist, it is created.
     */
    public void saveUsers() {
        ensureDataDirectory();
        try {
            users.saveToFile(USERS_FILE);
        } catch (IOException e) {
            System.err.println("Could not save users: " + e.getMessage());
        }
    }

    /**
     * Registers a new user and saves the updated user list if registration is successful.
     *
     * @param user the user to register
     * @return true if registration was successful, false otherwise
     */
    public boolean registerUser(User user) {
        boolean success = users.register(user);
        if (success) {
            saveUsers();
        }
        return success;
    }

    /**
     * Returns the Users instance for user management operations.
     */
    public Users getUsers() {
        return users;
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
