package careconnectclinic;
import careconnectclinic.Assistant;
import careconnectclinic.Doctor;
import java.io.File;
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


public class LoginController {
     @FXML
    private Stage stage;

   
        String url = "jdbc:mysql://localhost:3306/management_system";
        String dbusername = "root";
        String dbpassword = "DatabaseSoftware";
        String username;
    @FXML
    private TextField usernamefield;
    
    @FXML
    private TextField passwordfield;
    
    @FXML
    private Button loginbutton2;
    
    @FXML
    private Label txt1;
    
    @FXML
    private Label txt2;
    
    @FXML
    private Label usernametxt;
    
    @FXML
    private Label passwordtxt;
     
    @FXML 
    private void handleLoginButton2(ActionEvent event) throws Exception{
        Login login= new Login(url, dbusername, dbpassword);
        String username =usernamefield.getText();
        String password =passwordfield.getText();
        login.initializeDatabaseConnection();
         
        if(username.substring(0,2).equals("dr")){
            Doctor newUser= (Doctor) login.loginUser(username, password);
            File fxmlFile = new File("C:\\Users\\w.i\\Documents\\NetBeansProjects\\careconnectclinic\\src\\careconnectclinic\\doctor'smenue.fxml");
            FXMLLoader loader= new FXMLLoader(fxmlFile.toURI().toURL());
            Parent xx = loader.load();
            DoctorsController d= loader.getController();
            d.setUser(newUser);
            Stage a=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene y =new Scene(xx);
            a.setScene(y);           
            stage.getScene();
            stage.show();
        }
        else {
            Assistant newUser= (Assistant)login.loginUser(username, password);
            File fxmlFile2 = new File("C:\\Users\\w.i\\Documents\\NetBeansProjects\\careconnectclinic\\src\\careconnectclinic\\ADB.fxml");
            FXMLLoader loader= new FXMLLoader(fxmlFile2.toURI().toURL());
            Parent xx = loader.load();
            AssistantController c= loader.getController();
            c.setUser(newUser);
            
            Stage a=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene y =new Scene(xx);
            a.setScene(y);           
            stage.getScene();
            stage.show();
        }
    //3ayzeen el method hena 
    }
}