
package management.system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Doctor extends Person {
    private String doctorID;
    private static Integer doctorIDcounter=0;
    
   public Doctor(String name, String email, String password, String username ){
     super(name,email,password);
     doctorIDcounter++;  
     doctorID="DR"+ doctorIDcounter;
   }   

    public String getDoctorID() {
        return doctorID;
    }
    
    public void setDoctorID(String doctorID){
        this.doctorID=doctorID;
    }
    //done in Person
    /****
    public void View_medical_info(Patient patient){
        
        System.out.println("Patient Name: " + patient.getName());
        System.out.println("Patient ID: " + patient.getPatientID());
        System.out.println("Age: " + patient.getAge());
        System.out.println("Gender: " + patient.getGender());
        System.out.println("Allergies: " + patient.getAllergies());
        System.out.println("Diagnosis: " + patient.getDiagnosis());
        System.out.println("Health Problems: " + patient.getHealth_problems());
        System.out.println("Medications: " + patient.getMedications());
    }
    ********/
    public Patient Update_medical_info(Patient patient,String allergies,String health_problems,String medications,String diagnosis, Connection connection){
        try {
            // Prepare SQL query to update medical information based on patientID
            String sql = "UPDATE patient SET Allergies=?, Health_Problems=?, Medications=?, Diagnosis=? WHERE patient_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, allergies);
            statement.setString(2, health_problems);
            statement.setString(3, medications);
            statement.setString(4, diagnosis);
            statement.setString(5, patient.getPatientID());

            // Execute update
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Medical information updated successfully for patient with ID: " + patient.getPatientID());
            } else {
                System.out.println("Failed to update medical information. Patient with ID " + patient.getPatientID() + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        patient.setAllergies(allergies);
        patient.setDiagnosis(diagnosis);
        patient.setHealth_problems(health_problems);
        patient.setMedications(medications);
        return patient;
    }
        
}
