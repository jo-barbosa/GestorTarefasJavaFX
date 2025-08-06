package com.jobarbosa.gestortarefas;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Manages a collection of User objects, providing methods to add, remove, list, and search users.
 */
public class Users implements Serializable {
    private final List<User> list = new ArrayList<>();

    /**
     * Adds a user to the collection.
     *
     * @param user the user to add
     */
    public void add(User user) { list.add(user); }

    /**
     * Removes a user from the collection.
     *
     * @param user the user to remove
     */
    public void remove(User user) { list.remove(user); }

    /**
     * Returns a list of all users.
     *
     * @return a new list containing all users
     */
    public List<User> listAll() { return new ArrayList<>(list); }

    /**
     * Finds a user by username.
     *
     * @param username the username to search for
     * @return an Optional containing the user if found, or empty if not found
     */
    public Optional<User> findByUsername(String username) {
        return list.stream().filter(u -> u.getUsername().equals(username)).findFirst();
    }

    /**
     * Finds a user by id.
     *
     * @param id the user id to search for
     * @return an Optional containing the user if found, or empty if not found
     */
    public Optional<User> findById(int id) {
        return list.stream().filter(u -> u.getId() == id).findFirst();
    }

    /**
     * Returns a list of users with the specified role.
     *
     * @param role the user role to filter by
     * @return a list of users with the given role
     */
    public List<User> getByRole(User.Role role) {
        List<User> result = new ArrayList<>();
        for (User user : list) {
            if (user.getRole() == role) {
                result.add(user);
            }
        }
        return result;
    }

    /**
     * Authenticates a user by username and password.
     *
     * @param username the username
     * @param password the password
     * @return an Optional containing the authenticated user if credentials are correct, or empty if not
     */
    public Optional<User> authenticate(String username, String password) {
        return list.stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword() != null && u.getPassword().equals(password))
                .findFirst();
    }

    /**
     * Checks if a username is already taken.
     *
     * @param username the username to check
     * @return true if the username exists, false otherwise
     */
    public boolean usernameExists(String username) {
        return list.stream().anyMatch(u -> u.getUsername().equals(username));
    }

    /**
     * Registers a new user if the username is not taken.
     *
     * @param user the user to register
     * @return true if registration was successful, false if username already exists
     */
    public boolean register(User user) {
        if (usernameExists(user.getUsername())) {
            return false;
        }
        add(user);
        return true;
    }

    /**
     * Saves the user list to a file using Java serialization.
     *
     * @param filename the file to save to
     * @throws IOException if an I/O error occurs
     */
    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(list);
        }
    }

    /**
     * Loads the user list from a file using Java serialization.
     *
     * @param filename the file to load from
     * @throws IOException if an I/O error occurs
     * @throws ClassNotFoundException if the class of a serialized object cannot be found
     */
    @SuppressWarnings("unchecked")
    public void loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            list.clear();
            list.addAll((List<User>) in.readObject());
        }
    }
}