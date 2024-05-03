
package com.mycompany.careconnectclinic;

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
    
    public Patient Update_medical_info(Patient patient,String allergies,String health_problems,String medications,String diagnosis){
        patient.getName();
        patient.getPatientID();
        patient.getAge();
        patient.getGender();
        patient.setAllergies(allergies);
        patient.setDiagnosis(diagnosis);
        patient.setHealth_problems(health_problems);
        patient.setMedications(medications);
        return patient;
    }
        
}

