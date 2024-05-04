package com.mycompany.careconnectclinic;

import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class mySqlCon {
    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/clinic";
    private static final String USER = "root";
    private static final String PASSWORD = "fa25077148";

    private Connection connection;
    Statement statement = null;
    ResultSet resultSet = null;

    // Method to connect to the database
    public void connect() {
        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to database successfully.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to disconnect from the database
    public void disconnect() {
        if (connection != null) {
            try {
                // Close the connection
                System.out.println("Disconnecting from database...");
                connection.close();
                System.out.println("Disconnected from database successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ResultSet getQuery(String query) throws SQLException {

        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);

        return resultSet;
    }

        public static void main(String[] args) throws SQLException {
            mySqlCon database = new mySqlCon();
            database.connect();
            database.getQuery("SELECT * FROM patient");
            // Perform database operations here
            database.disconnect();
        }

}
