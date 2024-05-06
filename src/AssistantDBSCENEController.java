/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui1;

import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author FatmaALZahraa
 */
public class AssistantDBSCENEController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    
   public void switchToMain1(ActionEvent event) throws IOException{
       Parent root= FXMLLoader.load(getClass().getResource("Main1.fxml"));
       stage=(Stage)((Node)event.getSource()).getScene().getWindow();
       scene= new Scene(root);
       stage.setScene(scene);
       stage.show();
       
    }
    public void switchToAddPatient(ActionEvent event) throws IOException{
       Parent root= FXMLLoader.load(getClass().getResource("Main.fxml"));
       stage=(Stage)((Node)event.getSource()).getScene().getWindow();
       scene= new Scene(root);
       stage.setScene(scene);
       stage.show();
       
    }
    
     public void switchToViewPatientInfo(ActionEvent event) throws IOException{
         Parent root= FXMLLoader.load(getClass().getResource("ViewPatientInfo.fxml"));
       stage=(Stage)((Node)event.getSource()).getScene().getWindow();
       scene= new Scene(root);
       stage.setScene(scene);
       stage.show();
      
    }
     
      public void switchToUpdatePatientInfo(ActionEvent event) throws IOException{
          Parent root= FXMLLoader.load(getClass().getResource("UpdatePatientInfo.fxml"));
       stage=(Stage)((Node)event.getSource()).getScene().getWindow();
       scene= new Scene(root);
       stage.setScene(scene);
       stage.show();
      
    }
      
       public void switchToMakeReservation(ActionEvent event) throws IOException{
           Parent root= FXMLLoader.load(getClass().getResource("MakeReservation.fxml"));
       stage=(Stage)((Node)event.getSource()).getScene().getWindow();
       scene= new Scene(root);
       stage.setScene(scene);
       stage.show();
      
    }
       public void switchToSchedule(ActionEvent event) throws IOException{
       Parent root= FXMLLoader.load(getClass().getResource("Schedule.fxml"));
       stage=(Stage)((Node)event.getSource()).getScene().getWindow();
       scene= new Scene(root);
       stage.setScene(scene);
       stage.show();
       
    }
   
}
