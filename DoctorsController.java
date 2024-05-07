package careconnectclinic;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class DoctorsController {
     @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Doctor doc;
    public void setUser(Doctor doc){
        this.doc= doc;
    }
     public void switchtoVMIscene(ActionEvent event) throws IOException{
            File fxmlFile = new File("C:\\Users\\w.i\\Documents\\NetBeansProjects\\careconnectclinic\\src\\careconnectclinic\\viewmedicalinfo.fxml");
            FXMLLoader loader= new FXMLLoader(fxmlFile.toURI().toURL());
            Parent x = loader.load();
            ViewMIController v =loader.getController();
            v.setUser(doc);
            Stage a=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene y =new Scene(x);
            a.setScene(y);           
            stage.getScene();
            stage.show();
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


    //"C:/Users/janah/OneDrive/Documents/NetBeansProjects/CareConnect/src/main/java/com/mycompany/careconnect/viewmedicalinfo.fxml"    
    


