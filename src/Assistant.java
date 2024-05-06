
package management.system;


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
    
    public void Make_Reservation(String patientId, LocalDateTime date, String reservationType, String doctorId, boolean paid, Connection connection){
        Reservation RES =new Reservation(patientId,date,reservationType,doctorId,paid);
        RES.saveToDatabase(connection);
    }
    
    public Patient Addpatient (String name,String phonenumber ,int age,String gender,Connection connection){
        Patient patient=new Patient(name,phonenumber,age,gender);
        String patient_id = patient.getPatientID();
        
        try {
            String sql = "INSERT INTO patient (patient_id, name, phone_number, age, gender) VALUES (?, ?, ?, ?,?)";
             
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
    
    public Patient Updatepatientinfo (String patientID, String name,int age, String gender, String phonenumber, Connection connection){
        //update's the patient data in the database 
        try {
            // Prepare SQL query to update patient information based on patientID
            String sql = "UPDATE patient SET name=?, phone_number=?, age=?, gender=? WHERE patient_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, phonenumber);
            statement.setInt(3, age);
            statement.setString(4, gender);
            statement.setString(5, patientID);

            // Execute update
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Patient data updated successfully.");
            } else {
                System.out.println("Failed to update patient data. Patient with ID " + patientID + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL exceptions
        }
        
        //get the updated patient object to view in gui
        Patient patient= getPatientByID(patientID, connection);
        return patient;
    }
    /******** getPatientByID method in Person can be used to view the patient
   public void viewPatientInfo(Patient patient){
        System.out.println("Name: " + patient.getName());
        System.out.println("Patient ID: " + patient.getPatientID());
        System.out.println("Age: " + patient.getAge());
        System.out.println("Gender:" + patient.getGender());
        System.out.println("Phone Number: " + patient.getPhonenumber());
    }*************/
   
   
}
    
