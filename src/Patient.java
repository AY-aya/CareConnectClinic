package com.mycompany.careconnectclinic;

//import jdk.internal.icu.impl.CharacterIteratorWrapper;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Patient {
    private String name;
    private String patientID;
    private String phonenumber;
    private int age;
    private String gender;
    private String allergies;
    private String health_problems;
    private String medications;
    private String diagnosis;
    private static int patientIDcounter=0;
    static ResultSet resultSet = null;

    public Patient(String name,String phonenumber ,int age,String gender){
        this.name=name;
        this.phonenumber=phonenumber;
        if (phonenumber.length()!=11)
            System.out.println("invalid phone number");
        this.age=age;
        this.gender=gender;
        patientIDcounter++;
        patientID= "PT"+ patientIDcounter;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getHealth_problems() {
        return health_problems;
    }

    public void setHealth_problems(String health_problems) {
        this.health_problems = health_problems;
    }

    public String getMedications() {
        return medications;
    }

    public void setMedications(String medications) {
        this.medications = medications;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public static void getReport(int id) throws SQLException {

        mySqlCon database = new mySqlCon();
        database.connect();
        resultSet = database.getQuery("SELECT * FROM patient");

        // 4. Process the results

        while (resultSet.next()) {

            int patientId = resultSet.getInt("PId");

            if (patientId == id) {
                String name = resultSet.getString("PName");
                int age = resultSet.getInt("PAge");
                String gender = resultSet.getString("PGender");
                String allergies = resultSet.getString("PAllergies");
                String health_problems = resultSet.getString("PHealth_problems");
                String medications = resultSet.getString("PMedications");

                // Retrieve other columns as needed

                System.out.println("Patient ID: " + patientId + ", Name: " + name + " Gender: " + gender + " allergies: " + allergies);
                // Print other retrieved data
            }
        }
        database.disconnect();

    }
    public static void main(String[] args) throws SQLException {
        getReport(2);
    }

}