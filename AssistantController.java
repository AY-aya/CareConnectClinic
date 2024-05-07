package careconnectclinic;
    
import java.io.File;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 *
 * @author FatmaALZahraa
 */
public class AssistantController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private static Assistant user;
    
    public void setUser(Assistant user){
        this.user= user;
    }
   public void switchtoAPscene(ActionEvent event) throws IOException{
            File fxmlFile = new File("C:\\Users\\w.i\\Documents\\NetBeansProjects\\careconnectclinic\\src\\careconnectclinic\\AddPatient2.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlFile.toURI().toURL());
            Parent x = loader.load();
            AddpatientController u = loader.getController();
            u.setUser(user);
            Stage a=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene y =new Scene(x);
            a.setScene(y);           
            stage.getScene();
            stage.show();
        }
   
     
      public void switchtoUPscene(ActionEvent event) throws IOException{
            File fxmlFile = new File("C:\\Users\\w.i\\Documents\\NetBeansProjects\\careconnectclinic\\src\\careconnectclinic\\UpdateNonMedical.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlFile.toURI().toURL());
            Parent x = loader.load();
            UpdatepatientController u = loader.getController();
            u.setUser(user);
            Stage a=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene y =new Scene(x);
            a.setScene(y);           
            stage.getScene();
            stage.show();
        }
      
       public void switchtoRscene(ActionEvent event) throws IOException{
            File fxmlFile = new File("C:\\Users\\w.i\\Documents\\NetBeansProjects\\careconnectclinic\\src\\careconnectclinic\\Reserve.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlFile.toURI().toURL());
            Parent x = loader.load();

            // Get the controller instance
            ReservationController r = loader.getController();

            // Set the user property of the ReservationController
            r.setUser(user);

            // Switch to the Reservation scene
            Stage a = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene y = new Scene(x);
            a.setScene(y);
            a.show();
        }
       public void switchtoSscene(ActionEvent event) throws IOException {
    File fxmlFile = new File("C:\\Users\\w.i\\Documents\\NetBeansProjects\\careconnectclinic\\src\\careconnectclinic\\Schedule.fxml");
    Parent x = FXMLLoader.load(fxmlFile.toURI().toURL());

    // Create a new stage for the Schedule scene
    Stage scheduleStage = new Stage();
    Scene y = new Scene(x);
    scheduleStage.setScene(y);
    scheduleStage.setTitle("Schedule");
    
    // Show the new stage
    scheduleStage.show();
}
       public void switchtoLscene(ActionEvent event) throws IOException{
            File fxmlFile = new File("C:\\Users\\w.i\\Documents\\NetBeansProjects\\careconnectclinic\\src\\careconnectclinic\\login.fxml");
            Parent x = FXMLLoader.load(fxmlFile.toURI().toURL());
            Stage a=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene y =new Scene(x);
            a.setScene(y);           
            stage.getScene();
            stage.show();
        }
       public void switchtoSettingsscene(ActionEvent event) throws IOException {
    File fxmlFile = new File("C:\\Users\\w.i\\Documents\\NetBeansProjects\\careconnectclinic\\src\\careconnectclinic\\ReservationTypeSettings.fxml");
    Parent x = FXMLLoader.load(fxmlFile.toURI().toURL());

    // Create a new stage for the Schedule scene
    Stage scheduleStage = new Stage();
    Scene y = new Scene(x);
    scheduleStage.setScene(y);
    scheduleStage.setTitle("Settings");
    
    // Show the new stage
    scheduleStage.show();
}
       public void switchtoReportscene(ActionEvent event) throws IOException {
    File fxmlFile = new File("C:\\Users\\w.i\\Documents\\NetBeansProjects\\careconnectclinic\\src\\careconnectclinic\\Report.fxml");
    Parent x = FXMLLoader.load(fxmlFile.toURI().toURL());

    // Create a new stage for the Schedule scene
    Stage scheduleStage = new Stage();
    Scene y = new Scene(x);
    scheduleStage.setScene(y);
    scheduleStage.setTitle("Report");
    
    // Show the new stage
    scheduleStage.show();
}
       
   
  }  
