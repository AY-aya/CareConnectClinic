package careconnectclinic;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent; 
import javafx.fxml.FXML; 
import javafx.fxml.FXMLLoader; 
import javafx.scene.Node; 
import javafx.scene.Parent;
import javafx.scene.Scene;         
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddpatientController {
private Stage stage;

private Patient patient; // Patient object to store entered data temporarily

// FXML fields
@FXML
private TextField nametextfield;

@FXML
private TextField agetextfield;

@FXML
private TextField phonenumbertextfield;

@FXML
private TextField gendertextfield;

@FXML
private Button saveButton;

@FXML
private Label NameLabel;

@FXML
private Label AgeLabel;

@FXML
private Label PhoneNumberLabel;

@FXML
private Label GenderLabel;

@FXML
private Label patientID;

@FXML
private Button closeButton;

@FXML
Label successlabel;
Assistant user;
   public void setUser(Assistant user){
       this.user= user;
   }
// Method to save patient data
@FXML
private void savePatientData(ActionEvent event) {
    String url = "jdbc:mysql://localhost:3306/management_system";
            String dbusername = "root";
            String dbpassword = "DatabaseSoftware";
            Connection connection= null;
                try {
                    connection = DriverManager.getConnection(url, dbusername, dbpassword);
                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            
    // Get data from text fields
    String nameValue = nametextfield.getText();
    String ageValue = agetextfield.getText();
    String phoneNumberValue = phonenumbertextfield.getText();
    String genderValue = gendertextfield.getText();

    // Ensure age field is not empty before parsing
    int age = 0; // Default value if parsing fails
    if (!ageValue.isEmpty()) {
        try {
            age = Integer.parseInt(ageValue);
        } catch (NumberFormatException e) {
            // Handle parsing error if necessary
            e.printStackTrace();
        }
    }
    
    // Create an instance of Patient
    patient = user.Addpatient(nameValue, phoneNumberValue, age, genderValue, connection);
    
    

    // Switch to patient information scene
    switchToPatientInfoScene(event);
}

// Switch to patient information scene
@FXML
public void switchToPatientInfoScene(ActionEvent event) {
    try {
        File fxmlFile = new File("C:\\Users\\w.i\\Documents\\NetBeansProjects\\careconnectclinic\\src\\careconnectclinic\\patientinfo2.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlFile.toURI().toURL());
        Parent x = loader.load(); // Load the FXML file
        AddpatientController controller = loader.getController();
        controller.setPatient(patient);
        Stage currentStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(x);
        currentStage.setScene(scene);
        currentStage.setTitle("Patient Information");
        currentStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
// Switch back to data entry scene
@FXML
private void switchbacktoInfoScene(ActionEvent event) {
    try {
         File fxmlFile = new File("C:\\Users\\w.i\\Documents\\NetBeansProjects\\careconnectclinic\\src\\careconnectclinic\\AddPatient2.fxml");
         Parent x = FXMLLoader.load(fxmlFile.toURI().toURL());
        //Maincontroller.setPatient(patient); // Pass patient object back to the data entry scene

        Stage a=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene y =new Scene(x);
        a.setScene(y);           
        stage.getScene();       
        stage.setTitle("Data Entry");
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
public void setPatient(Patient patient) {
    this.patient = patient;
    updatePatientInfoLabels();
}

private void updatePatientInfoLabels() {
    // Use the patient object to update the labels in the patientinfoo.fxml view
    // For example:
   
    NameLabel.setText("Name: "+patient.getName());
    AgeLabel.setText("Age: "+String.valueOf(patient.getAge()));
    PhoneNumberLabel.setText("Phone Number: "+patient.getPhonenumber());
    GenderLabel.setText("Gender: "+patient.getGender());
    patientID.setText("Patient ID: "+patient.getPatientID());
     
    
}
public void switchtoAMscene(ActionEvent event) throws IOException{     
            File fxmlFile = new File("C:\\Users\\w.i\\Documents\\NetBeansProjects\\careconnectclinic\\src\\careconnectclinic\\ADB.fxml");
            Parent x = FXMLLoader.load(fxmlFile.toURI().toURL());
            Stage a=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene y =new Scene(x);
            a.setScene(y);           
            stage.getScene();
            stage.show();
        }  
}
