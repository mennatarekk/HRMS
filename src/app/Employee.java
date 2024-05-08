package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.Date;


public class Employee {
    private int employeeId;
    private String name;
    private String department;
    private String position;
    private double salary;
    private double currentPerformanceRating;
    private int remainingLeaveDays;
    private String password;


    // Constructor Default
    public Employee(){
    }


    // Constructor
    public Employee(int employeeId, String name, String department, String position, double salary, String password) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.position = position;
        this.salary = salary;
        currentPerformanceRating = 100;
        remainingLeaveDays = 30;
        this.password = password;
    }

    public Employee(int employeeId, String name, String department, String position, double salary, int remainingLeaveDays, double currentPerformanceRating, String password) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.position = position;
        this.salary = salary;
        this.currentPerformanceRating = currentPerformanceRating;
        this.remainingLeaveDays = remainingLeaveDays;
        this.password = password;

    }

    // Getters and setters
    public int getEmployeeId() {
        return employeeId;
    }
    public String getPassword() {
        return password;
    }
    

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


    public double getCurrentPerformanceRating() {
        return currentPerformanceRating;
    }

    public void setCurrentPerformanceRating(double currentPerformanceRating) {
        this.currentPerformanceRating = currentPerformanceRating;
    }

    public int getRemainingLeaveDays() {
        return remainingLeaveDays;
    }

    public void setRemainingLeaveDays(int remainingLeaveDays) {
        this.remainingLeaveDays = remainingLeaveDays;
    }



    public void requestLeave(Date startDate, Date endDate){
        LeaveManagement.applyForLeave(this, startDate, endDate);
    }



    public static Employee findEmployeeById(ArrayList<Employee> employees , int id){
        for(Employee emp : employees){
            if(emp.getEmployeeId() == id){
                System.out.println("founds");
                return emp;
            }
        }
        System.out.println("not found");
        return null;
    }


    public static Employee Login(ArrayList<Employee> employees, int username, String password){
        
        for(Employee emp : employees){
            if(emp.employeeId == username && emp.password.equals(password)){
                System.out.println("WEEEEEEEEEE FOUNDDD");
                return emp;
            }else{
                System.out.println("YOOOOOOOOO NOT FOUNDDD");
            }
        }
        return null;
    }
    

    public static void copyEmployees(ArrayList<Employee> employees) {
        try {
            Scanner scanner = new Scanner(new File("data/Employees.txt"));
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(",");

                if (line.length != 8)
                    continue;
                else{
                
                // Extracting data from the line array
                int employeeId = Integer.parseInt(line[0].trim());
                String name = line[1].trim();
                String department = line[2].trim();
                String position = line[3].trim();
                double salary = Double.parseDouble(line[4].trim());
                int remainingLeave = Integer.parseInt(line[5].trim());
                double currentperf = Double.parseDouble(line[6].trim());
                String password = line[7].trim();
                
                // Create a new Employee object
                Employee employee = new Employee(employeeId, name, department, position, salary,remainingLeave,currentperf, password);
                
                // Add the employee to the ArrayList
                employees.add(employee);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

    }

    @Override
    public String toString() {
        return String.format("%d, %s, %s, %s, %.2f, %d, %.2f, %s",
                employeeId,
                name,
                department,
                position,
                salary, 
                remainingLeaveDays,
                currentPerformanceRating,
                password);
    }

    public static void endEmployees(ArrayList<Employee> emps) throws IOException{
    
        try {
            FileWriter writerEMP = new FileWriter("data/Employees.txt", false);
            FileWriter writerHR = new FileWriter("data/HR.txt", false);

        
            for (Employee employee : emps) {
                if (employee instanceof HR)
                    writerHR.write(employee.toString() + "\n");
                else 
                    writerEMP.write(employee.toString() + "\n");
            }
            writerHR.close();
            writerEMP.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}