package careconnectclinic;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;



public class RegisterController  {
String url = "jdbc:mysql://localhost:3306/management_system";
        String dbusername = "root";
        String dbpassword = "DatabaseSoftware";  

@FXML
private TextField rolefield;
@FXML
private Label roletxt;
@FXML
private Label usernametxt;
@FXML
private Label passwordtxt;
@FXML
private Label successLabel;

@FXML
private TextField passwordfield;
@FXML
private Button RegisterButton2;
@FXML
private TextField namefield;
@FXML
private TextField emailfield;
@FXML
private Label nametxt;
@FXML
private Label emailtxt;
@FXML
private void handleRegisterButton2(ActionEvent e){
    //registeration logic
    String name= namefield.getText();
    String email= emailfield.getText();
    String password= passwordfield.getText();
    String role= rolefield.getText();
    Registration reg= new Registration(url, dbusername, dbpassword);
    reg.initializeDatabaseConnection();
    try {
        if(role.equals("doctor")){
        Doctor user= (Doctor)reg.registerUser(name, email, password, role);
        successLabel.setText("Registered Successfully, please find your username below");
        usernametxt.setText("Your Username: "+ user.getDoctorID() );
        }
        else if(role.equals("assistant")){
            Assistant user= (Assistant)reg.registerUser(name, email, password, role);
        successLabel.setText("Registered Successfully, please find your username below");
        usernametxt.setText("Your Username: "+ user.getAssistantID() );
        }
    } catch (IllegalArgumentException ex) {
        Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
}
    
}