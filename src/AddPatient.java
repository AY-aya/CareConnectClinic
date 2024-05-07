package gui1;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CareConnect extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
       
         
// Load the FXML file
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);
        String css=this.getClass().getResource("gui1.css").toExternalForm();
         scene.getStylesheets().add(css);
        
       
        
        // Set the scene on the primary stage
        primaryStage.setScene(scene);
        
        
        // Set the title of the stage
        primaryStage.setTitle("Information");
        
        // Make the window resizable
        primaryStage.setResizable(true);
        
        // Show the primary stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}