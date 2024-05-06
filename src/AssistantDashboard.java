/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gui1;

/**
 *
 * @author FatmaALZahraa
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;



public class AssistantDashboard extends Application{

 public static void main(String[] args) {
       launch(args);
    }
  
    /**
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
 @SuppressWarnings("empty-statement")
    public void start(Stage primaryStage) throws Exception {
        Parent root= FXMLLoader.load(getClass().getResource("Main1.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        //scene.getStylesheets().add(getClass().getResource("gui.css").toExternalForm());
       String css=this.getClass().getResource("gui1.css").toExternalForm();
       scene.getStylesheets().add(css);
      Image icon=new Image("icon.jpg");
        primaryStage.getIcons().add(icon);

        primaryStage.setTitle(" Assistant Dashboard");
        primaryStage.show();
    }
}