/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 20114
 */
public class UpdateNonMedicalController {
    private String PTID;
    private Patient patient;
   @FXML
   Label invalididlabel;
   @FXML
   TextField nametext;
   @FXML
   TextField IDtext;
   @FXML
   TextField agetext;
   @FXML
   TextField gendertext;
   @FXML
   TextField numtext;
   
   public void UpdatePT(ActionEvent event){
       PTID=IDtext.getText();
       if(patient.getPatientID().equals(PTID)){
           patient.setName(nametext.getText());
           patient.setGender(gendertext.getText());
           patient.setAge(Integer.parseInt(agetext.getText()));
           patient.setPhonenumber(numtext.getText());
       }
       else invalididlabel.setText("Invalid ID");
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
