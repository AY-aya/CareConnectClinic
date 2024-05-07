package gui1;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aya Yasser
 */
public class ReservationTypeSettingsController implements Initializable{

    /**
     * Initializes the controller class.
     */
    @FXML
    TextField typeField;
    @FXML
    TextField feesField;
    @FXML
    RadioButton newType;
    @FXML
    RadioButton update;
    public void save(ActionEvent e){
        
        String type= typeField.getText();
        int fees= Integer.parseInt( feesField.getText() );
        //System.out.println(type+" "+fees);
        // Check which radio button is selected
        if (newType.isSelected()) {
            // Call addType method
            new Report().addReservationType(type, fees);
            System.out.println("added");
        } else if (update.isSelected()) {
            // Call editType method
            new Report().editReservationTypeFee(type, fees);
            System.out.println("updated");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Select the "New Type" radio button by default
        newType.setSelected(true);
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
