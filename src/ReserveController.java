/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui1;

/**
 *
 * @author FatmaALZahraa
 */

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ReserveController {	
	@FXML
	private Spinner<Integer> hour;

	@FXML
	private Spinner<Integer> minute;



	    @FXML
	    private RadioButton Cardiology;

	    @FXML
	    private RadioButton ENT;

	    @FXML
	    private ToggleGroup R_Type;

	    @FXML
	    private TextField doctorid;

	    @FXML
	    private RadioButton internalmedicine;

	    @FXML
	    private Button new_reservation;

	    @FXML
	    private CheckBox paid;

	    @FXML
	    private TextField patientid;
	    @FXML
	    String patientid1  ;
	    
		
	    
	    @FXML
	    private DatePicker reserve_date;
	   
	    @FXML
	   
	   
	   
	    public void resverve(ActionEvent event) { 
	    	String  reservationType = null;
	    	String  patientID=patientid.getText();
	    	String  doctorID=doctorid.getText(); 
	    	boolean ispaid=paid.isSelected();
	    if ( ENT .isSelected())	{ reservationType="ENT";}
	    if ( Cardiology .isSelected())	{ reservationType="Cardiology";}
	    if ( internalmedicine .isSelected())	{ reservationType="InternalMedicine";}
	    
	    LocalDate selectedDate = reserve_date.getValue();
	    int hour1 = hour.getValue();
	    int minute1 = minute.getValue();
	    
	    LocalDateTime dateTime = LocalDateTime.of(selectedDate, LocalTime.of(hour1, minute1,0));
	   
	    System.out.println(patientID+doctorID+ispaid+reservationType+ dateTime);
	   
	    Reservation reservation = new Reservation (patientID,dateTime,reservationType,doctorID,ispaid); 
	    }
	    
		   public void ispaid(ActionEvent clicked) { 
			  
	                System.out.println("Payment is completed.");
                        
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