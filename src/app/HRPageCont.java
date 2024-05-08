package app;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HRPageCont {

    @FXML
    private ResourceBundle resources;

    @FXML
    private VBox requestsMainVbox;


    @FXML
    private URL location;

    @FXML
    private Text HRName;

    @FXML
    private Button addBonus;

    @FXML
    private Button addEmployee;

    @FXML
    private Button perfRating;

    @FXML
    private Button deductSalary;

    @FXML
    private Button hideTable;

    @FXML
    private TableColumn<Employee, String> deptColumn;

    @FXML
    private TextField newDept;

    @FXML
    private TextField newID;

    @FXML
    private TextField newName;

    @FXML
    private TextField newPassword;

    @FXML
    private TextField newPosition;

    @FXML
    private TextField newSalary;


    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private TableColumn<Employee, String> idColumn;

    @FXML
    private TableColumn<Employee, String> nameColumn;

    @FXML
    private TableColumn<Employee, String> positionColumn;

    @FXML
    private TableColumn<Employee, String> ratingColumn;

    @FXML
    private TableColumn<Employee, String> salaryColumn;

int state=0;

    @FXML
    private HBox newEmployeeHbox;

    @FXML
    private Button logout;

    @FXML
    private Button viewEmployees;

    @FXML
    private Button newEmployee;

    @FXML
    private Button newEmployee1;

    
    @FXML
    private TextField xAmountText;

    @FXML
    private TextField xIDText;

    @FXML
    private Button xSharedButton;

    @FXML
    private VBox xSharedVbox;

    ObservableList<Employee> data = FXCollections.observableArrayList();


    @FXML
    void addBonus(ActionEvent event) {
        xSharedVbox.setVisible(true);
        newEmployeeHbox.setVisible(false);
        newEmployee.setVisible(false);
        newEmployee1.setVisible(false);
        buttonsVbox.setVisible(false);
        requestsMainVbox.setVisible(false);
        scroll.setVisible(false);
        xSharedButton.setText("Add Bonus");


       state = 1;
    }
    
    @FXML
    void perfRating(ActionEvent event) {
        xSharedVbox.setVisible(true);
        newEmployeeHbox.setVisible(false);
        newEmployee.setVisible(false);
        newEmployee1.setVisible(false);
        buttonsVbox.setVisible(false);
        requestsMainVbox.setVisible(false);
        scroll.setVisible(false);
        xSharedButton.setText("Add/Remove From Rating");
        state = 2;
    }

    @FXML
    void deductSalary(ActionEvent event) {
        xSharedVbox.setVisible(true);
        newEmployeeHbox.setVisible(false);
        newEmployee.setVisible(false);
        newEmployee1.setVisible(false);
        buttonsVbox.setVisible(false);
        requestsMainVbox.setVisible(false);
        scroll.setVisible(false);
        xSharedButton.setText("Deduct From Salary");
        state = 3;
    }

   
    @FXML
    void xSharedButton(ActionEvent event) {
        try {
            Employee badr = Employee.findEmployeeById(App.employees, Integer.parseInt(xIDText.getText()));
            double amount = Double.parseDouble(xAmountText.getText());
            int amountint = Integer.parseInt(xAmountText.getText());
            switch (state) {
                case 1: // bonus
                System.out.println(badr.getSalary());
                ((HR)App.activeEmployee).addBonuses(badr, amount);
                System.out.println(badr.getSalary());

                    
                    break;
                case 2: // rating
                xSharedButton.setVisible(true);
                System.out.println(badr.getCurrentPerformanceRating());
                ((HR)App.activeEmployee).updatePerformanceRating(badr, amount);
                System.out.println(badr.getCurrentPerformanceRating());


                    break;
                case 3: //salary
                xSharedButton.setVisible(true);
                System.out.println(badr.getSalary());
                ((HR)App.activeEmployee).deductFromSalary(badr, amountint );
                System.out.println(badr.getSalary());


                    break;
                    
                default:
                    break;
            }
            buttonsVbox.setVisible(true);
            requestsMainVbox.setVisible(true);
            scroll.setVisible(true);
            xSharedVbox.setVisible(false);
            xAmountText.clear();
            xIDText.clear();


        } catch (Exception e) {
            Alert alert1 = new Alert(AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("An Error Occurred");
            alert1.setContentText("Invalid Data Fields");
            alert1.showAndWait();
        }
       

        
    }


 @FXML
    private VBox buttonsVbox;

    @FXML
    private ScrollPane scroll;

    @FXML
    void addEmployee(ActionEvent event) {

        newEmployeeHbox.setVisible(true);
        newEmployee.setVisible(true);
        newEmployee1.setVisible(true);
        buttonsVbox.setVisible(false);
        requestsMainVbox.setVisible(false);
        scroll.setVisible(false);



    }

   

    @FXML
    void viewEmployees(ActionEvent event) {

        data.clear();
        buttonsVbox.setVisible(false);
        employeeTable.setVisible(true);
        hideTable.setVisible(true);
        requestsMainVbox.setVisible(false);
        scroll.setVisible(false);



        for(Employee employee : App.employees){
            data.add(employee);
        }

        employeeTable.setItems(data);

    }
    @FXML
    void hideTable(ActionEvent event) {

        employeeTable.setVisible(false);
        hideTable.setVisible(false);
        buttonsVbox.setVisible(true);
        requestsMainVbox.setVisible(true);
        scroll.setVisible(true);



    }


    @FXML
    void newEmployee(ActionEvent event) {

        if(!newName.getText().isEmpty() || !newID.getText().isEmpty() || !newDept.getText().isEmpty() || !newPosition.getText().isEmpty() || !newSalary.getText().isEmpty() || !newPassword.getText().isEmpty()){

            try {
                Employee badr = new Employee(Integer.parseInt(newID.getText()), newName.getText(), newDept.getText(), newPosition.getText(), Double.parseDouble(newSalary.getText()), newPassword.getText());
                App.employees.add(badr);
                System.out.println(badr.getName());
                newName.clear();
                newID.clear();
                newDept.clear();
                newPassword.clear();
                newPosition.clear();
                newSalary.clear();
                newEmployeeHbox.setVisible(false);
                newEmployee.setVisible(false);
                buttonsVbox.setVisible(true);
                requestsMainVbox.setVisible(true);
                scroll.setVisible(true);

                
            } catch (Exception e) {

                Alert alert1 = new Alert(AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("An Error Occurred");
            alert1.setContentText("Invalid Data Fields");
            alert1.showAndWait();

            }

        }else{
            Alert alert1 = new Alert(AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("An Error Occurred");
            alert1.setContentText("Empty Data Fields");
        
            alert1.showAndWait();
                
        }

    }

    @FXML
    void cncl(ActionEvent event) {
        newName.clear();
                newID.clear();
                newDept.clear();
                newPassword.clear();
                newPosition.clear();
                newSalary.clear();
                newEmployeeHbox.setVisible(false);
                newEmployee.setVisible(false);
                buttonsVbox.setVisible(true);
                newEmployee1.setVisible(false);
                requestsMainVbox.setVisible(true);
                scroll.setVisible(true);
    }

    @FXML
    void logout(ActionEvent event) {
        App.activeEmployee=null;
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        // Close the stage
        stage.close();
    }


    @FXML
    void initialize() {
        // assert HRName.isEmpty() : "fx:id=\"HRName\" was not injected: check your FXML file 'HRPage.fxml'.";
        // assert addBonus.isEmpty() : "fx:id=\"addBonus\" was not injected: check your FXML file 'HRPage.fxml'.";
        // assert addEmployee.isEmpty() : "fx:id=\"addEmployee\" was not injected: check your FXML file 'HRPage.fxml'.";
        // assert deductSalary.isEmpty() : "fx:id=\"deductSalary\" was not injected: check your FXML file 'HRPage.fxml'.";
        // assert deptColumn.isEmpty() : "fx:id=\"deptColumn\" was not injected: check your FXML file 'HRPage.fxml'.";
        // assert employeeTable.isEmpty() : "fx:id=\"employeeTable\" was not injected: check your FXML file 'HRPage.fxml'.";
        // assert idColumn.isEmpty() : "fx:id=\"idColumn\" was not injected: check your FXML file 'HRPage.fxml'.";
        // assert nameColumn.isEmpty() : "fx:id=\"nameColumn\" was not injected: check your FXML file 'HRPage.fxml'.";
        // assert positionColumn.isEmpty() : "fx:id=\"positionColumn\" was not injected: check your FXML file 'HRPage.fxml'.";
        // assert ratingColumn.isEmpty() : "fx:id=\"ratingColumn\" was not injected: check your FXML file 'HRPage.fxml'.";
        // assert salaryColumn.isEmpty() : "fx:id=\"salaryColumn\" was not injected: check your FXML file 'HRPage.fxml'.";
        // assert viewEmployees.isEmpty() : "fx:id=\"viewEmployees\" was not injected: check your FXML file 'HRPage.fxml'.";


        HRName.setText(App.activeEmployee.getName());

        newEmployee1.setVisible(false);
        xSharedVbox.setVisible(false);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("currentPerformanceRating"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        deptColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        employeeTable.setVisible(false);
        hideTable.setVisible(false);
        newEmployeeHbox.setVisible(false);
        newEmployee.setVisible(false);

        HBox header = new HBox();

        Text sText = new Text("Start  ");
        Text eText = new Text("End  ");
        Text nText = new Text("Name  ");

        requestsMainVbox.setSpacing(3.2);
        sText.setWrappingWidth(85);
        eText.setWrappingWidth(85);
        nText.setWrappingWidth(50);
        sText.setFont(Font.font("Arial ",FontWeight.BOLD, 15));
        eText.setFont(Font.font("Arial",FontWeight.BOLD, 15));
        nText.setFont(Font.font("Arial",FontWeight.BOLD, 15));


        header.getChildren().addAll(sText, eText, nText);
            requestsMainVbox.getChildren().add(header);

        for(leaveRequest leave : LeaveManagement.allRequests){

            if(leave.isApproved()==false){
            HBox row = new HBox();

            Text sdatetext = new Text(leave.getStartDateString());
            Text edatetext = new Text(leave.getEndDateString());
            Text enametext = new Text(leave.getEmployee().getName());

            Button approve = new Button("Approve");
            Button decline = new Button("Decline");

            approve.setMinWidth(80);
            decline.setMinWidth(80);

            sdatetext.setWrappingWidth(85);
            edatetext.setWrappingWidth(85);
            enametext.setWrappingWidth(50);

            

            row.getChildren().add(sdatetext);
            row.getChildren().add(edatetext);
            row.getChildren().add(enametext);
            row.getChildren().addAll(approve,decline);
            row.setPrefWidth(500);
            requestsMainVbox.getChildren().add(row);

            approve.setOnAction(event->{
                ((HR)App.activeEmployee).managerApproveLeave(leave);
                System.out.println(leave.isApproved());
                requestsMainVbox.getChildren().remove(row);
                
            });

            decline.setOnAction(event1->{
                row.setVisible(false);
                requestsMainVbox.getChildren().remove(row);
                LeaveManagement.allRequests.remove(leave);
            });
        }
        }
    }
}