package com.mycompany.careconnectclinic;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Assistant extends Person {
    private String assistantID;
    private static int assistantIDcounter=0;
    
    public Assistant(){};
     public Assistant(String name, String email, String password, String username){
     super(name,email,password);
     assistantIDcounter++;
     this.assistantID="AS"+assistantIDcounter;
   }

   
    public String getAssistantID() {
        return assistantID;
    }
    
    public void Make_Reservation(String patientId, LocalDateTime date, String reservationType, String doctorId, boolean paid){
        Reservation RES =new Reservation(patientId,date,reservationType,doctorId,paid);
    }
    
    public Patient Addpatient (String name,String phonenumber ,int age,String gender,Connection connection){
        Patient patient=new Patient(name,phonenumber,age,gender);
        String patient_id = patient.getPatientID();
        
        try {
            String sql = "INSERT INTO patient (patient_Id,name,phone_number, age, gender) VALUES (?, ?, ?, ?,?)";
             
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, patient_id);
            statement.setString(2, name);
            statement.setString(3, phonenumber);
            statement.setInt(4, age);
            statement.setString(5, gender);
            statement.executeUpdate();
            System.out.println("test");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return patient;
    }
    
    public Patient Updatepatientinfo (Patient patient, String name,int age, String gender, String phonenumber){
        System.out.println("Patient ID: " + patient.getPatientID());
        patient.setName(name);
        patient.setAge(age);
        patient.setGender(gender);
        patient.setPhonenumber(phonenumber); 
        return patient;
    }
    
   public void viewPatientInfo(Patient patient){
        System.out.println("Name: " + patient.getName());
        System.out.println("Patient ID: " + patient.getPatientID());
        System.out.println("Age: " + patient.getAge());
        System.out.println("Gender:" + patient.getGender());
        System.out.println("Phone Number: " + patient.getPhonenumber());
    }
}
    