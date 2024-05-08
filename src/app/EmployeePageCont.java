package app;

import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EmployeePageCont {

    @FXML
    private ResourceBundle resources;

    @FXML
    private Text employeeName;

    @FXML
    private ImageView employeePhoto;

    @FXML
    private TableView<leaveRequest> requestsTable;

    @FXML
    private TableColumn<leaveRequest, String> endColumn;

    @FXML
    private TableColumn<leaveRequest, String> startColumn;

    @FXML
    private TableColumn<leaveRequest, String> approvedColumn;


    @FXML
    private Label successLabel;
    
    @FXML
    private Text employeeDepartment;


    @FXML
    private Text employeePerformance;

   
    @FXML
    private ImageView employeePhoto1;

    @FXML
    private Text employeePosition;

    @FXML
    private Pane requestPane;

    @FXML
    private Text employeeSalary;

    @FXML
    private DatePicker endDate;

    @FXML
    private Button requestButton;

    @FXML
    private DatePicker startDate;

    @FXML
    private Button submitButton;

    @FXML
    private Button hideButton;

    @FXML
    private Button logout;

    @FXML
    private Button viewRequests;

    ObservableList<leaveRequest> data = FXCollections.observableArrayList();


    @FXML
    void initialize() {

        assert approvedColumn != null : "fx:id=\"approvedColumn\" was not injected: check your FXML file 'EmployeePage.fxml'.";
        assert employeeDepartment != null : "fx:id=\"employeeDepartment\" was not injected: check your FXML file 'EmployeePage.fxml'.";
        assert employeeName != null : "fx:id=\"employeeName\" was not injected: check your FXML file 'EmployeePage.fxml'.";
        assert employeePerformance != null : "fx:id=\"employeePerformance\" was not injected: check your FXML file 'EmployeePage.fxml'.";
        assert employeePhoto != null : "fx:id=\"employeePhoto\" was not injected: check your FXML file 'EmployeePage.fxml'.";
        assert employeePhoto1 != null : "fx:id=\"employeePhoto1\" was not injected: check your FXML file 'EmployeePage.fxml'.";
        assert employeePosition != null : "fx:id=\"employeePosition\" was not injected: check your FXML file 'EmployeePage.fxml'.";
        assert employeeSalary != null : "fx:id=\"employeeSalary\" was not injected: check your FXML file 'EmployeePage.fxml'.";
        assert endColumn != null : "fx:id=\"endColumn\" was not injected: check your FXML file 'EmployeePage.fxml'.";
        assert endDate != null : "fx:id=\"endDate\" was not injected: check your FXML file 'EmployeePage.fxml'.";
        assert hideButton != null : "fx:id=\"hideButton\" was not injected: check your FXML file 'EmployeePage.fxml'.";
        assert requestButton != null : "fx:id=\"requestButton\" was not injected: check your FXML file 'EmployeePage.fxml'.";
        assert requestPane != null : "fx:id=\"requestPane\" was not injected: check your FXML file 'EmployeePage.fxml'.";
        assert requestsTable != null : "fx:id=\"requestsTable\" was not injected: check your FXML file 'EmployeePage.fxml'.";
        assert startColumn != null : "fx:id=\"startColumn\" was not injected: check your FXML file 'EmployeePage.fxml'.";
        assert startDate != null : "fx:id=\"startDate\" was not injected: check your FXML file 'EmployeePage.fxml'.";
        assert submitButton != null : "fx:id=\"submitButton\" was not injected: check your FXML file 'EmployeePage.fxml'.";
        assert successLabel != null : "fx:id=\"successLabel\" was not injected: check your FXML file 'EmployeePage.fxml'.";
        assert viewRequests != null : "fx:id=\"viewRequests\" was not injected: check your FXML file 'EmployeePage.fxml'.";
        employeeName.setText(App.activeEmployee.getName());
        employeeDepartment.setText(App.activeEmployee.getDepartment());
        employeePerformance.setText(String.valueOf(App.activeEmployee.getCurrentPerformanceRating()));
        employeeSalary.setText(String.valueOf(App.activeEmployee.getSalary()));
        employeePosition.setText(App.activeEmployee.getPosition());
        requestPane.setVisible(false);
        successLabel.setVisible(false);
        startDate.getEditor().setEditable(false);
        endDate.getEditor().setEditable(false);
        endColumn.setCellValueFactory(new PropertyValueFactory<>("endDateString"));
        startColumn.setCellValueFactory(new PropertyValueFactory<>("startDateString"));
        approvedColumn.setCellValueFactory(new PropertyValueFactory<>("isApprovedstring"));

        requestsTable.setVisible(false);
        hideButton.setVisible(false);

    }

    @FXML
    void logout(ActionEvent event) {
        App.activeEmployee=null;
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        // Close the stage
        stage.close();
    }


    @FXML
    void requestLeave(ActionEvent event) {

        requestsTable.setVisible(false);
        hideButton.setVisible(false);
        requestPane.setVisible(true);
        successLabel.setVisible(false);

    }

    @FXML
    void hideTable(ActionEvent event) {

        requestsTable.setVisible(false);
        hideButton.setVisible(false);

    }

    @SuppressWarnings("deprecation")
    @FXML
    void submitLeave(ActionEvent event) {

        if(startDate.getValue() != null && endDate.getValue() != null){
       Date startt = new Date(startDate.getValue().getYear()-2000+100, startDate.getValue().getMonthValue()-1, startDate.getValue().getDayOfMonth());
       Date endd= new Date(endDate.getValue().getYear()-2000+100, endDate.getValue().getMonthValue()-1, endDate.getValue().getDayOfMonth());

       if(startt.compareTo(endd) < 0 ){
        App.activeEmployee.requestLeave(startt, endd);
        requestPane.setVisible(false);
        System.out.println(startDate.getValue().getDayOfMonth());
        System.out.println(startt.getDate());
         
        successLabel.setVisible(true);
        successLabel.setText("Request Sent!");
        System.out.println(endDate.getValue().getDayOfMonth());
        System.out.println(endd.getDate());
        startDate.setValue(null);
        endDate.setValue(null); 

       }else
       {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("An Error Occurred");
        alert.setContentText("Start date after End date");
    
        alert.showAndWait();
       }

        }else
        {
            Alert alert1 = new Alert(AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("An Error Occurred");
            alert1.setContentText("Empty or Invalid Date Fields");
        
            alert1.showAndWait();
        }
    }

    @FXML
    void viewLeaves(ActionEvent event) {
        data.clear();
        for(leaveRequest requesta : LeaveManagement.allRequests){
            if(requesta.getEmployee().getEmployeeId() == App.activeEmployee.getEmployeeId()){
                data.add(requesta);
            }
        }
        requestsTable.setItems(data);
        requestPane.setVisible(false);
        successLabel.setVisible(false);
        requestsTable.setVisible(true);
        hideButton.setVisible(true);

    }
}