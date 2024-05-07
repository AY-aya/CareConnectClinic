
package careconnectclinic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
   
    public void initializeDatabaseConnection() {
        try {
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  private void validateInput() throws IllegalArgumentException {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
    }

    public Person loginUser(String username, String password) throws IllegalArgumentException {
        this.username= username;
        this.password= password;
        validateInput();

        try {
            if (!existsInDatabase(username, password)) {
                throw new IllegalArgumentException("Username or password doesn't exist");
            }
            System.out.println("successful login");
            return getUserFromDatabase();
        } catch (SQLException e) {
            System.out.println("Error querying database: " + e.getMessage());
            return null;
        }
    }
    
 private boolean existsInDatabase(String targetUsername, String targetPassword) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM person WHERE username = ? AND passwordi = ?")) {
            stmt.setString(1, targetUsername);
            stmt.setString(2, targetPassword);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }
  
    private Person getUserFromDatabase() throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM person WHERE username = ? AND passwordi = ?")) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String role = rs.getString("username").substring(0, 2);
                    switch (role) {
                        case "dr" -> {
                            return new Doctor(rs.getString("name"), rs.getString("email"), rs.getString("passwordi"), rs.getString("username"));
                        }
                        case "as" -> {
                            return new Assistant(rs.getString("name"), rs.getString("email"), rs.getString("passwordi"), rs.getString("username"));
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
}
