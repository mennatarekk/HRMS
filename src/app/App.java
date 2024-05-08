package app;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;





/*
 ------------------------ERRORS-------------------------

 1) entering text in date field
 2)update performance for leave requests on approve


 */






public class App extends Application{

    public static Employee activeEmployee;
    static ArrayList<Employee> employees = new ArrayList<>();


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mylogin.fxml"));
        primaryStage.setTitle("Login Page");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    
        Employee.copyEmployees(employees);
        HR.copyHR(employees);
        leaveRequest.copyLeaveRequests(employees);

        launch(args);

    
        leaveRequest.endLeaveRequests();
        Employee.endEmployees(employees);

       

    }
}

