
package com.mycompany.careconnectclinic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Person {
    private String name;
    private String password;
    private String email;
    
    public Person(){};
    public Person(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    //not needed 
    /***
    public void view_schedule(String doctorID, Connection c){
        Schedule.viewSchedule(doctorID, c);
    }***/
    
    /***** can be done directly from report class
    public void editReservationTypeFee(String type, int newFee, Connection connection){
        
       // new Report(connection).editReservationTypeFee(type, newFee);
    }
     
   public static void generateMonthlyReport(ArrayList<Reservation> reservations, int month, int year,Connection connection) {
        HashMap<String, Integer> incomeMap = new Report(connection).prepareMonthlyReport(month, year, connection);
        
        // Print the generated report
        System.out.println("Monthly Report for " + month + "/" + year + ":");
        for (HashMap.Entry<String, Integer> entry : incomeMap.entrySet()) {
            System.out.println("Reservation Type: " + entry.getKey() + ", Income: " + entry.getValue());
        }
        
        double totalRevenue = Report.getTotalRevenue();
        System.out.println("Total Revenue: " + totalRevenue);
    }
   
   **********/
    
   // Method to retrieve a patient from the database based on patientID
    //can be used to viw medical and non-medical object (specified in gui implementation)
    public Patient getPatientByID(String patientID, Connection connection) {
        Patient patient = null;
        try {
            // Prepare SQL query to retrieve patient information based on patientID
            String sql = "SELECT * FROM patient WHERE patient_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, patientID);

            // Execute query
            ResultSet resultSet = statement.executeQuery();

            // Check if patient exists
            if (resultSet.next()) {
                // Retrieve patient data from result set
                String name = resultSet.getString("name");
                String phoneNumber = resultSet.getString("phone_number");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                String allergies= resultSet.getString("Allergies");
                String healthProb= resultSet.getString("Health_Problems");
                String medication= resultSet.getString("Medications");
                String diagnosis= resultSet.getString("Diagnosis");
                // Create patient object
                patient = new Patient(name, phoneNumber, age, gender);
                patient.setPatientID(patientID); // Set patientID
                patient.setAllergies(allergies);
                patient.setHealth_problems(healthProb);
                patient.setMedications(medication);
                patient.setDiagnosis(diagnosis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;
    }
    
}
