package app;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class HR extends Employee {
    
    public HR(int employeeId, String name, String department, String position, double salary, String password) {
        super(employeeId, name, department, position, salary, password);
    }

    public HR(int employeeId, String name, String department, String position, double salary, int remainingLeaveDays, double currentPerformanceRating, String password) {
        super(employeeId, name, department, position, salary, remainingLeaveDays, currentPerformanceRating, password);
    }



    public void addBonuses(Employee employee, double bonus){
        employee.setSalary(employee.getSalary() + bonus);

    }

    public void deductFromSalary(Employee employee, int amount){

        Payroll.deductSalary(employee, amount);
    }

    void managerApproveLeave(leaveRequest leave) {
        leave.setApproved(true);
        LeaveManagement.updateLeaveBalance(leave);
        PerformanceEvaluation.calculatePerformanceRate(leave.getEmployee(), leave.getEmployee().getRemainingLeaveDays(), leave.getStartDate(), leave.getEndDate());


        if(leave.isValid()==false)
            Payroll.deductSalary(leave.getEmployee(), Payroll.calculateDeduction(leave.getEmployee(), Math.abs(leave.getEmployee().getRemainingLeaveDays())));
        else 
            System.out.println("No deduction");
    }




    void updatePerformanceRating(Employee employee, double amount){
        PerformanceEvaluation.setPerformanceRating(employee, amount);
    } // maybe in onclick




    void addEmployee(int employeeId, String name, String department, String position, int salary, int remainingLeave, double currentperf, String password, ArrayList<Employee> employees ){
        employees.add(new Employee(employeeId, name, department, position, salary,remainingLeave,currentperf, password));
        System.out.println("Added new employee!");


    }







    public static void copyHR(ArrayList<Employee> employees) {
        try {
            Scanner scanner = new Scanner(new File("data/HR.txt"));
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(",");
                if (line.length != 8)
                    continue;
                else{
                int employeeId = Integer.parseInt(line[0].trim());
                String name = line[1].trim();
                String department = line[2].trim();
                String position = line[3].trim();
                double salary = Double.parseDouble(line[4].trim());
                int remainingLeave = Integer.parseInt(line[5].trim());
                double currentperf = Double.parseDouble(line[6].trim());
                String password = line[7].trim();

                
                HR hr = new HR(employeeId, name, department, position, salary, remainingLeave, currentperf, password);
                
                employees.add(hr);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}
