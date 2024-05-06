package management.system;

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
    private void validateUserInput() throws IllegalArgumentException {
        Scanner scanner = new Scanner(System.in);

        // Get user input for name, email, and password
        System.out.print("Enter your name: ");
        name = scanner.nextLine().trim();
        System.out.print("Enter your email: ");
        email = scanner.nextLine().trim();
        System.out.print("Enter your password: ");
        password = scanner.nextLine().trim();

        // Validate name
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        // Validate email
        if (email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        } else if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Invalid email address");
        }

        // Validate password
        if (password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        } else if (!password.matches("^(?=.*\\d)(?=\\S+$).{8,}$")) {
            throw new IllegalArgumentException("Password must be at least 8 characters long, contain at least one digit, one lowercase letter, one uppercase letter, one special character, and no whitespace");
        }
    }
    
    // Method to register a new user
    public Person registerUser() throws IllegalArgumentException, SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you a doctor or an assistant? (doctor/assistant)");
        String role = scanner.nextLine().trim().toLowerCase();

        // Validate user input
        validateUserInput();

        // Generate username based on role
        username = generateUsername(role);

        // Check if username or email already exists in the database
        if (existsInDatabase(username, email)) {
            throw new IllegalArgumentException("Username or email already exists");
        }

        // Insert user data into the database
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO person (username, name, passwordi, email) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, username);
            stmt.setString(2, name);
            stmt.setString(3, password);
            stmt.setString(4, email);
            stmt.executeUpdate();
            System.out.println("Registration successful!");

            // Determine user type based on username prefix
            String usernamePrefix = username.substring(0, 2);
            switch (usernamePrefix) {
                case "dr" -> {
                    return new Doctor(name, email, password, username);
                }
                case "as" -> {
                    return new Assistant(name, email, password, username);
                }
                default -> throw new IllegalArgumentException("Invalid username prefix");
            }
        } finally {
            scanner.close(); // Close the scanner to prevent resource leak
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
    try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM person WHERE username = ? OR email = ?")) {
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

}
