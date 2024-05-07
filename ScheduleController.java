package careconnectclinic;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Aya Yasser
 */
public class ScheduleController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private LocalDate d;
    String docID;
    @FXML
    DatePicker datap;
    @FXML
    Label date;
    @FXML
    FlowPane flowpane; 
    @FXML
    AnchorPane anchorPane;
    @FXML
    ImageView imgView;
    @FXML
    Button MenuB;
    @FXML
    Button NextB;
    @FXML
    Button prevB;
    @FXML
    TextField getDoc;
    
    public void displayDate(ActionEvent e){
        d= datap.getValue();
        String formatted= d.format(DateTimeFormatter.ofPattern("dd--MMM-yyyy"));
        date.setText(formatted);
        displaySchedule();
    }
    public void setDocID(ActionEvent e){
        docID= getDoc.getText();
        displaySchedule();
    }
    public void nextDay(ActionEvent e){
        d= d.plusDays(1);
        String formatted= d.format(DateTimeFormatter.ofPattern("dd--MMM-yyyy"));
        date.setText(formatted);
        displaySchedule();
    }
       
    public void previousDay(ActionEvent e){
        d= d.minusDays(1);
        String formatted= d.format(DateTimeFormatter.ofPattern("dd--MMM-yyyy"));
        date.setText(formatted);
        displaySchedule();
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
    /******
    public void Logout(ActionEvent e) throws IOException{
      FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScene.fxml"));
      root = loader.load();
      //EnterController w= loader.getController(); //create instance of login controller
      //display scene
      stage= (Stage)(((Node)e.getSource()).getScene().getWindow());
      scene= new Scene(root);
      stage.setScene(scene);
      stage.show();
    }*********/
    
    public void displaySchedule(){
        if(docID== null){
            flowpane.getChildren().clear(); // Clear existing children
            Label l = new Label("choose a doctor to view schedule");
            flowpane.getChildren().add(l);
        }
        else{
            String url = "jdbc:mysql://localhost:3306/management_system";
            String dbusername = "root";
            String dbpassword = "DatabaseSoftware";
            Connection connection= null;
                try {
                    connection = DriverManager.getConnection(url, dbusername, dbpassword);
                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            ArrayList<Reservation> r = Schedule.viewSchedule(docID, d, connection);
            flowpane.getChildren().clear(); // Clear existing children
            flowpane.setPadding(new Insets(10));
            flowpane.setHgap(10);
            flowpane.setVgap(10);
            if(r.isEmpty()){
                Label l = new Label("NO reservations found for this day");
                flowpane.getChildren().add(l);
            }
            else{
                // Populate FlowPane with reservations
                for (Reservation reservation : r) {
                    VBox reservationBox = createReservationBox(reservation);
                    flowpane.getChildren().add(reservationBox);
                }
            }
        }
        
    }
    // Method to create a custom control for each reservation
    private VBox createReservationBox(Reservation reservation) {
        VBox box = new VBox();
        box.prefWidthProperty().bind(flowpane.widthProperty().divide(7));
        // Bind VBox height to maintain aspect ratio
        box.prefHeightProperty().bind(box.widthProperty());
        box.setSpacing(5);
        //box style
        // Set background color
        BackgroundFill backgroundFill = new BackgroundFill(
                Color.web("555555"),          // Fill color
                CornerRadii.EMPTY,             // Corner radii (empty for square corners)
                Insets.EMPTY                   // Insets (empty for default insets)
        );
        box.setBackground(new Background(backgroundFill));

      
        // Display reservation data
        Label dateLabel = new Label("Time: " + reservation.getDate().format(DateTimeFormatter.ofPattern("h:mm a")));
        dateLabel.setTextFill(Color.web("e1a52d")); // Set text color to gold
        dateLabel.setPadding(new Insets(5, 5, 5, 5)); // Add bottom padding to text
        dateLabel.styleProperty().bind(
                            box.heightProperty().divide(7).asString("-fx-font-size: %.2fpx;")
                        );
        dateLabel.setWrapText(true);
        Label patientIdLabel = new Label("Patient ID: " + reservation.getPatientId());
        patientIdLabel.setTextFill(Color.web("e1a52d")); // Set text color to gold
        patientIdLabel.setPadding(new Insets(5, 5, 5, 5)); // Add bottom padding to text
        patientIdLabel.styleProperty().bind(
                            box.heightProperty().divide(7).asString("-fx-font-size: %.2fpx;")
                        );
        box.getChildren().addAll(dateLabel, patientIdLabel);

        return box;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       imgView.fitWidthProperty().bind(anchorPane.widthProperty());
       imgView.fitHeightProperty().bind(anchorPane.heightProperty());
       //flowpane.setOpacity(0.40);
       BackgroundFill backgroundFill = new BackgroundFill(
            Color.rgb(100, 100, 100, 0.5), // Transparent white color
            CornerRadii.EMPTY,
            Insets.EMPTY
        );
        Background background = new Background(backgroundFill);
        flowpane.setBackground(background);
        d= LocalDate.now();
        String formatted= d.format(DateTimeFormatter.ofPattern("dd--MMM-yyyy"));
        date.setText(formatted);
        displaySchedule();
    }

}
