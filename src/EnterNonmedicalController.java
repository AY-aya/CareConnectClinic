package gui1;

import java.io.IOException;
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

public class EnterNonmedicalController {

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

// Method to save patient data
@FXML
private void savePatientData(ActionEvent event) {
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
    patient = new Patient(nameValue,phoneNumberValue, age, genderValue);
    
    

    // Switch to patient information scene
    switchToPatientInfoScene(event);
}

// Switch to patient information scene
@FXML
public void switchToPatientInfoScene(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("patientinfoo.fxml")); //////set to menu scene
        Parent root = loader.load();
        EnterNonmedicalController controller = loader.getController();
        controller.setPatient(patient);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
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
         Parent root =FXMLLoader.load(getClass().getResource("AddPatient.fxml"));
        
        //Maincontroller.setPatient(patient); // Pass patient object back to the data entry scene

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        currentStage.setScene(scene);
        currentStage.setTitle("Data Entry");
        currentStage.show();
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
public void backToMenu(ActionEvent e) throws IOException{
      FXMLLoader loader = new FXMLLoader(getClass().getResource("ADB.fxml")); //////set to menu scene
            Object root = loader.load();
            //EnterController w= loader.getController(); //create instance of menu controller
            //display scene
            Stage stage = (Stage)(((Node)e.getSource()).getScene().getWindow());
            Scene scene = new Scene((Parent) root);
      stage.setScene(scene);
      stage.show();
    }   
}