package careconnectclinic;
import careconnectclinic.Doctor;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewMIController  {
    Patient patient;
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    private ImageView image1;
    private Pane pane;
    // Patient patient;
    private String PTID;
    @FXML
    TextField getPT;
    @FXML
    Label namelabel;
    @FXML
    Label IDlabel;
    @FXML
    Label agelabel;
    @FXML
    Label genderlabel;
    @FXML
    Label allergieslabel;
    @FXML
    Label healthlabel;
    @FXML
    Label medicationslabel;
    @FXML
    Label diagnosislabel;
    @FXML
    Label invalididlabel;
    private Doctor doc;
    public void setUser(Doctor doc){
        this.doc= doc;
    }
    public void switchtoDMscene(ActionEvent event) throws IOException{
            
            File fxmlFile = new File("C:\\Users\\w.i\\Documents\\NetBeansProjects\\careconnectclinic\\src\\careconnectclinic\\doctor'smenue.fxml");
            Parent x = FXMLLoader.load(fxmlFile.toURI().toURL());
            Stage a=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene y =new Scene(x);
            a.setScene(y);           
            stage.getScene();
            stage.show();
        }
    public void switchtoUMIscene(ActionEvent event) throws IOException{
            File fxmlFile = new File("C:\\Users\\w.i\\Documents\\NetBeansProjects\\careconnectclinic\\src\\careconnectclinic\\updatemedicalinfo.fxml");
            FXMLLoader loader= new FXMLLoader(fxmlFile.toURI().toURL());
            Parent x = loader.load();
            UpdateMIController v =loader.getController();
            v.setUser(doc);
            Stage a=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene y =new Scene(x);
            a.setScene(y);           
            stage.getScene();
            stage.show();
        }
    public void setPTID (ActionEvent event){
          PTID=getPT.getText();
          String url = "jdbc:mysql://localhost:3306/management_system";
            String dbusername = "root";
            String dbpassword = "DatabaseSoftware";
            Connection connection= null;
                try {
                    connection = DriverManager.getConnection(url, dbusername, dbpassword);
                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
          patient = doc.getPatientByID(PTID, connection);
          if (patient.getPatientID().equals(PTID)){
            if(patient.getName()==null)      
                namelabel.setText("no name provided");
            else
              namelabel.setText(patient.getName());
            if(patient.getPatientID()==null)
                IDlabel.setText("no ID provided");
            else
                IDlabel.setText(patient.getPatientID());
            if(String.valueOf(patient.getAge()).equals(0))
                agelabel.setText("no age provided");
            else
                agelabel.setText(String.valueOf(patient.getAge()));
            if(patient.getGender()==null)
                genderlabel.setText("no gender provided");
            else
                genderlabel.setText(patient.getGender());
            if(patient.getAllergies()==null)
                allergieslabel.setText("no allergies provided");
            else
                allergieslabel.setText(patient.getAllergies());
            if(patient.getHealth_problems()==null)
                healthlabel.setText("no health problems provided");
            else
                healthlabel.setText(patient.getHealth_problems());
            if(patient.getMedications()==null)
                medicationslabel.setText("no medications provided");
            else
                medicationslabel.setText(patient.getMedications());
            if(patient.getDiagnosis()==null)
                diagnosislabel.setText("no diagnosis provided");
            else
                diagnosislabel.setText(patient.getDiagnosis());
        }else invalididlabel.setText("Invalid ID");
         System.out.println(patient.getPatientID());
    }
}
            


    
