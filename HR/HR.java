package HR;
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
        double performanceRating = employee.getCurrentPerformanceRating();
        employee.setCurrentPerformanceRating(performanceRating + amount);
      
    } 




 



    public static void copyHR(ArrayList<Employee> employees) {
        try {
            Scanner scanner = new Scanner(new File("data/HR.txt"));
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(",");
                
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
                HR hr = new HR(employeeId, name, department, position, salary, remainingLeave, currentperf, password);
                
                // Add the employee to the ArrayList
                employees.add(hr);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}
