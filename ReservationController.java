package careconnectclinic;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReservationController implements Initializable{
    private Stage stage;
    private Assistant user;
	@FXML
	private Spinner<Integer> hour;

	@FXML
	private Spinner<Integer> minute;
	    
	    @FXML
	    private ToggleGroup R_Type;

	    @FXML
	    private TextField doctorid;
            
            @FXML
            private VBox box;
            
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
	   
            public void setUser(Assistant user){
                this.user= user;
            }
	    @FXML 
	    public void resverve(ActionEvent event) { 
                String url = "jdbc:mysql://localhost:3306/management_system";
                String dbusername = "root";
                String dbpassword = "DatabaseSoftware";
                Connection connection= null;
                try {
                    connection = DriverManager.getConnection(url, dbusername, dbpassword);
                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
	    	String  reservationType = null;
	    	String  patientID=patientid.getText();
	    	String  doctorID=doctorid.getText(); 
	    	boolean ispaid=paid.isSelected();
                reservationType= getSelectedReservationType();
                
	    LocalDate selectedDate = reserve_date.getValue();
	    int hour1 = hour.getValue();
	    int minute1 = minute.getValue();
	    
	    LocalDateTime dateTime = LocalDateTime.of(selectedDate, LocalTime.of(hour1, minute1,0));
	   
	    System.out.println(patientID+doctorID+ispaid+reservationType+ dateTime);
	    user.Make_Reservation(patientID, dateTime, reservationType, doctorID, ispaid, connection);
	    //Reservation reservation = new Reservation (patientID,dateTime,reservationType,doctorID,ispaid); 
	    }
	    
		   public void ispaid(ActionEvent clicked) { 
			  
	                System.out.println("Payment is completed.");
                        
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
         public void displayReservationTypes(){
             String url = "jdbc:mysql://localhost:3306/management_system";
                String dbusername = "root";
                String dbpassword = "DatabaseSoftware";
                Connection connection= null;
                try {
                    connection = DriverManager.getConnection(url, dbusername, dbpassword);
                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
             ToggleGroup t= new ToggleGroup();
             HashMap<String, Integer> map= (HashMap)Report.getTypeData(connection);
             for(String type:  map.keySet()){
                 RadioButton b= new RadioButton(type);
                 b.setToggleGroup(t);
                 box.getChildren().add(b);
             }
         }
         public String getSelectedReservationType() {
            for (Node radioButton : box.getChildren()) {
                if (((RadioButton)radioButton).isSelected()) {
                    return ((RadioButton)radioButton).getText();
                }
            }
            return null; // No radio button is selected
        }
        @Override
    public void initialize(URL url, ResourceBundle rb) {
           //for test, real map will be called from Report Class
           displayReservationTypes();
    }
}

