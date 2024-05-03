
package com.mycompany.careconnectclinic;

/**
 *
 * @author FatmaALZahraa
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {

    private String password;
    private String username;
    private final String url;
    private final String dbUsername;
    private final String dbPassword;
    private Connection connection;

    public Login(String url, String dbUsername, String dbPassword) {
        this.url = url;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
    }

    public void initializeDatabaseConnection() throws SQLException {
        connection = DriverManager.getConnection(url, dbUsername, dbPassword);
    }
  private void validateInput() throws IllegalArgumentException {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
    }

    public Person loginUser() throws IllegalArgumentException {
        validateInput();

        try {
            if (!existsInDatabase(username, password)) {
                throw new IllegalArgumentException("Username or password doesn't exist");
            }

            return getUserFromDatabase();
        } catch (SQLException e) {
            System.out.println("Error querying database: " + e.getMessage());
            return null;
        }
    }
    
 private boolean existsInDatabase(String targetUsername, String targetPassword) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {
            stmt.setString(1, targetUsername);
            stmt.setString(2, targetPassword);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }
  
    private Person getUserFromDatabase() throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String role = rs.getString("username").substring(0, 2);
                    switch (role) {
                        case "dr" -> {
                            return new Doctor(rs.getString("name"), rs.getString("email"), rs.getString("password"), rs.getString("username"));
                        }
                        case "as" -> {
                            return new Assistant(rs.getString("name"), rs.getString("email"), rs.getString("password"), rs.getString("username"));
                        }
                        default -> throw new IllegalArgumentException("Invalid role");
                    }
                } else {
                    System.out.println("Login failed. Username or password is incorrect.");
                    return null;
                }
            }
        }
    }
    
    // Getters and setters for username and password (if needed)

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/your_database";
        String username = "your_username";
        String password = "your_password";

        Login login = new Login(url, username, password);

        try {
            login.initializeDatabaseConnection();

            // Set username and password
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your username: ");
            String usernameInput = scanner.nextLine().trim();
            System.out.print("Enter your password: ");
            String passwordInput = scanner.nextLine().trim();

            // Authenticate user
            login.setUsername(usernameInput);
            login.setPassword(passwordInput);
            Person loggedInUser = login.loginUser();

            // Perform actions based on login status
            if (loggedInUser != null) {
                System.out.println("Login successful!");
                // Perform actions for logged-in user (e.g., display user details, navigate to user dashboard, etc.)
            } else {
                System.out.println("Login failed. Username or password is incorrect.");
            }
        } catch (SQLException e) {
            System.out.println("Error initializing database connection: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Login failed: " + e.getMessage());
        }
    }
}
