package careconnectclinic;
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

    public Patient(String name, String phonenumber, String gender, String allergies, String health_problems, String medications, String diagnosis) {
        this.name = name;
        this.phonenumber = phonenumber;
        this.gender = gender;
        this.allergies = allergies;
        this.health_problems = health_problems;
        this.medications = medications;
        this.diagnosis = diagnosis;
        patientIDcounter++;
        patientID= "PT"+ patientIDcounter;
    }
     
     
    
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

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public static void setPatientIDcounter(int patientIDcounter) {
        Patient.patientIDcounter = patientIDcounter;
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
     
}