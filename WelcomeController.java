package careconnectclinic;

import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class WelcomeController {
    private Stage stage;
     public void switchtoRscene(ActionEvent event) throws IOException{
            File fxmlFile = new File("C:\\Users\\w.i\\Documents\\NetBeansProjects\\careconnectclinic\\src\\careconnectclinic\\register.fxml");
            Parent x = FXMLLoader.load(fxmlFile.toURI().toURL());
            Stage a=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene y =new Scene(x);
            a.setScene(y);           
            stage.getScene();
            stage.show();
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
    
}
