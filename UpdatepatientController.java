package careconnectclinic;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class UpdatepatientController {
    private Stage stage;
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
   
   Assistant user;
   public void setUser(Assistant user){
       this.user= user;
   }
   public void UpdatePT(ActionEvent event){
       String url = "jdbc:mysql://localhost:3306/management_system";
        String dbusername = "root";
        String dbpassword = "DatabaseSoftware";
        Connection connection= null;
            try {
                connection = DriverManager.getConnection(url, dbusername, dbpassword);
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            
       PTID=IDtext.getText();
       user.Updatepatientinfo(PTID, nametext.getText(), Integer.parseInt(agetext.getText()), gendertext.getText(), numtext.getText(), connection);
       
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
   
   
   
   
    
    
}