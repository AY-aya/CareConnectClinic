package gui1;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ReportController implements Initializable {

    @FXML
    private TextField YearField;

    @FXML
    private Spinner<Integer> MonthSpinner;

    @FXML
    private GridPane reportContainer; // Container for the generated labels

    private final Report report = new Report(); // Create an instance of the Report class

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set the spinner factory to handle integer values
        MonthSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12));
        report.addReservationType("ENT", 100);
        report.addReservationType("Cardiology", 200);
        report.addReservationType("Dental", 100);
        report.addReservationType("Pshychaitry", 200);
        
    }

    // Method to handle button press event
    @FXML
    public void buttonPressed(ActionEvent event) {
        int month = MonthSpinner.getValue();
        int year = Integer.parseInt(YearField.getText());
        
        // Retrieve the report data for the specified month and year
        Map<String, Integer> reportData = report.prepareMonthlyReport(month, year);
        
        // Update the UI with the report data
        updateReportUI(reportData);
    }

    // Method to update the UI with the report data
    public void updateReportUI(Map<String, Integer> incomeMap) {
        reportContainer.getChildren().clear(); // Clear existing labels

        int row = 0;
        for (Map.Entry<String, Integer> entry : incomeMap.entrySet()) {
            String type = entry.getKey();
            int income = entry.getValue();

            // Create labels for type and income
            Label typeLabel = new Label(type);
            Label incomeLabel = new Label(String.valueOf(income));
            
            // Set font size for labels
            typeLabel.setFont(new Font(16));
            incomeLabel.setFont(new Font(16));

            // Add labels to the grid pane
            reportContainer.add(typeLabel, 0, row);
            reportContainer.add(incomeLabel, 1, row);

            // Increment row index
            row++;
        }
        Label typeLabel = new Label("Total Income");
        Label incomeLabel = new Label(String.valueOf(report.getTotalRevenue()));
        // Add labels to the grid pane
            reportContainer.add(typeLabel, 0, row);
            reportContainer.add(incomeLabel, 1, row);
            // Set font size for labels
            typeLabel.setFont(new Font(16));
            incomeLabel.setFont(new Font(16));
        // Increment row index
            row++;
        // Set spacing between rows
        reportContainer.setVgap(1);
    }
      public void backToMenu(ActionEvent e) throws IOException{
      FXMLLoader loader = new FXMLLoader(getClass().getResource("ADB.fxml")); //////set to menu scene
            Object root = loader.load();
            //EnterController w= loader.getController(); //create instance of menu controller
            //display scene
            Stage stage = (Stage)(((Node)e.getSource()).getScene().getWindow());
            Scene scene = new Scene((Parent) root);
      stage.setScene(scene);
      stage.show();
    }   
}
