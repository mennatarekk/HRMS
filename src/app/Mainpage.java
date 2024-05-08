package app;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Mainpage {
    @FXML
    private Label LoginLabel;

    @FXML
    private TextField idtext;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordtext;

   
    @FXML
    void login(ActionEvent event) {

    try {
        int employeeId;
        employeeId = Integer.parseInt(idtext.getText());
        Employee.Login(App.employees, Integer.parseInt(idtext.getText()), passwordtext.getText()).getName();
    

    
            App.activeEmployee=Employee.Login(App.employees, Integer.parseInt(idtext.getText()), passwordtext.getText());
            System.out.println(App.activeEmployee.getEmployeeId());

            if(App.activeEmployee instanceof HR){
                try {
                    // Load the new window FXML file
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("HRPage.fxml"));
                    Parent root = loader.load();
        
                    // Create a new stage (window)
                    Stage newStage = new Stage();
                    newStage.setTitle("New Window");
        
                    // Set the scene with the new window's layout
                    newStage.setScene(new Scene(root));
        
                    // Show the new window
                    newStage.show();
                } catch (IOException e) {
                    e.printStackTrace(); // Handle exception properly in your application
                }
            }
      else{  try {
            // Load the new window FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeePage.fxml"));
            Parent root = loader.load();

            // Create a new stage (window)
            Stage newStage = new Stage();
            newStage.setTitle("New Window");

            // Set the scene with the new window's layout
            newStage.setScene(new Scene(root));

            // Show the new window
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle exception properly in your application
        }
    }
} catch (Exception e) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error Dialog");
    alert.setHeaderText("An Error Occurred");
    alert.setContentText("Invalid Login");

    alert.showAndWait();
}
}
}


