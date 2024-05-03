
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

public class Registration {

    private String name;
    private String username;
    private String password;
    private String email;
    private static int doctorCount = 0;
    private static int assistantCount = 0;
    private final String url;
    private final String dbUsername;
    private final String dbPassword;
    private Connection connection;

    // Constructor to initialize database connection details
    public Registration(String url, String dbUsername, String dbPassword) {
        this.url = url;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
    }

    // Method to initialize the database connection
    public void initializeDatabaseConnection() throws SQLException {
        connection = DriverManager.getConnection(url, dbUsername, dbPassword);
    }

    // Method to validate user input
    private void validateUserInput(String name, String email, String password) throws IllegalArgumentException {
        // Set inputs to class variables
        this.name = name.trim();
        this.email = email.trim();
        this.password = password.trim();

        // Validate name
        if (this.name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        // Validate email
        if (this.email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        } else if (!this.email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Invalid email address");
        }

        // Validate password
        if (this.password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        } else if (!this.password.matches("^(?=.*\\d)(?=\\S+$).{8,}$")) {
            throw new IllegalArgumentException("Password must be at least 8 characters long, contain at least one digit, one lowercase letter, one uppercase letter, one special character, and no whitespace");
        }
    }

    // Method to register a new user
    public Person registerUser(String name, String email, String password, String role) throws IllegalArgumentException, SQLException {
        // Validate user input
        validateUserInput(name, email, password);

        // Check if role is valid
        while (!role.equalsIgnoreCase("doctor") && !role.equalsIgnoreCase("assistant")) {
            System.out.println("Invalid role. Please enter either 'doctor' or 'assistant'.");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your role: ");
            role = scanner.nextLine().trim().toLowerCase();
        }

        // Generate username based on role
        username = generateUsername(role);

        // Check if username or email already exists in the database
        if (existsInDatabase(username, email)) {
            throw new IllegalArgumentException("Username or email already exists");
        }

        // Insert user data into the database
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO users (name, email, password, username) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, this.name);
            stmt.setString(2, this.email);
            stmt.setString(3, this.password);
            stmt.setString(4, username);
            stmt.executeUpdate();
            System.out.println("Registration successful!");

            // Determine user type based on username prefix
            String usernamePrefix = username.substring(0, 2);
            switch (usernamePrefix) {
                case "dr" -> {
                    return new Doctor(this.name, this.email, this.password, username);
                }
                case "as" -> {
                    return new Assistant(this.name, this.email, this.password, username);
                }
                default -> throw new IllegalArgumentException("Invalid username prefix");
            }
        }
    }

    // Method to generate username based on role
    private String generateUsername(String role) {
        switch (role) {
            case "doctor" -> {
                doctorCount++;
                return "dr" + String.format("%04d", doctorCount);
            }
            case "assistant" -> {
                assistantCount++;
                return "as" + String.format("%04d", assistantCount);
            }
            default -> throw new IllegalArgumentException("Invalid role specified");
        }
    }

    // Method to check if username or email already exists in the database
    private boolean existsInDatabase(String targetUsername, String targetEmail) {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE username = ? OR email = ?")) {
            stmt.setString(1, targetUsername);
            stmt.setString(2, targetEmail);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println("Error checking user existence in database: " + e.getMessage());
            return false; // or handle the error accordingly
        }
    }

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/your_database";
        String username = "your_username";
        String password = "your_password";

        Registration registration = new Registration(url, username, password);

        try {
            registration.initializeDatabaseConnection();
            Scanner scanner = new Scanner(System.in);
            // Take input from user
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            System.out.print("Enter your email: ");
            String email = scanner.nextLine();
            System.out.print("Enter your password: ");
            String passwordInput = scanner.nextLine();
            String role;
            do {
                System.out.print("Enter your role (doctor/assistant): ");
                role = scanner.nextLine().trim().toLowerCase();
            } while (!role.equals("doctor") && !role.equals("assistant"));

            // Register user
            registration.registerUser(name, email, passwordInput, role);
        } catch (SQLException e) {
            System.out.println("Error initializing database connection: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }
}
