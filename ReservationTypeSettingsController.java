package careconnectclinic;


import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class ReservationTypeSettingsController implements Initializable{

    /**
     * Initializes the controller class.
     */
    @FXML
    TextField typeField;
    @FXML
    TextField feesField;
    @FXML
    RadioButton newType;
    @FXML
    RadioButton update;
    public void save(ActionEvent e){
         String url = "jdbc:mysql://localhost:3306/management_system";
            String dbusername = "root";
            String dbpassword = "DatabaseSoftware";
            Connection connection= null;
                try {
                    connection = DriverManager.getConnection(url, dbusername, dbpassword);
                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
        String type= typeField.getText();
        int fees= Integer.parseInt( feesField.getText() );
        //System.out.println(type+" "+fees);
        // Check which radio button is selected
        if (newType.isSelected()) {
            // Call addType method
            new Report().addReservationType(type, fees, connection);
            System.out.println("added");
        } else if (update.isSelected()) {
            // Call editType method
           new Report().editReservationTypeFee(type, fees, connection);
            System.out.println("updated");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Select the "New Type" radio button by default
        newType.setSelected(true);
    }
    
}
