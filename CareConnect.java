package careconnectclinic;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import java.io.File;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

public class CareConnect extends Application  {
  
    public void start(Stage stage)throws Exception {
        
           File fxmlFile = new File("C:\\Users\\w.i\\Documents\\NetBeansProjects\\careconnectclinic\\src\\careconnectclinic\\welcome.fxml");
           Parent root = FXMLLoader.load(fxmlFile.toURI().toURL());                 
        Scene scene = new Scene(root);
        stage.setTitle("CareConnectClinic");  
        stage.setScene(scene);
        stage.show();
        
    }
     public static void main(String[] args) {
        launch(args);
    }
    
}
