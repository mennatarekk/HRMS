package HR;
import java.util.ArrayList;
import java.util.Date;


public class App {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        ArrayList<Employee> employees = new ArrayList<>();

        Employee.copyEmployees(employees);
        HR.copyHR(employees);

        Employee Cristiano = new Employee(111, "Cristiano", "CS", "HEAD", 5000, "PASSWORDS");
        HR CristianoHR = new HR(111, "Cristiano", "CS", "HEAD", 5000, "PASSWORDS");
        
        
        //Salary Test
        System.out.println("Salary Before!");
        System.out.println(Cristiano.getSalary());
        CristianoHR.deductFromSalary(Cristiano, 1000);
        System.out.println(Cristiano.getSalary());
        System.out.println("Salary AFTER!");
        //Leave req test without deduction
        System.out.println("REQUESTS Before!");
        System.out.println(Cristiano.getSalary());
        System.out.println(Cristiano.getRemainingLeaveDays()); 
        Cristiano.requestLeave(new Date(124, 5, 15), new Date(124, 5, 17));
        CristianoHR.managerApproveLeave(LeaveManagement.allRequests.get(0));
        System.out.println(Cristiano.getRemainingLeaveDays()); 
        System.out.println(Cristiano.getSalary());
        System.out.println("REQUESTS AFTER!");
        //Leave req test with deduction
        System.out.println("REQUESTS Before!");
        System.out.println(Cristiano.getSalary());
        System.out.println(Cristiano.getRemainingLeaveDays()); 
        Cristiano.requestLeave(new Date(124, 5, 15), new Date(124, 8, 17));
        CristianoHR.managerApproveLeave(LeaveManagement.allRequests.get(1));
        System.out.println(Cristiano.getRemainingLeaveDays()); 
        System.out.println(Cristiano.getSalary());
        System.out.println("REQUESTS AFTER!");

        employees.add(CristianoHR);
        employees.add(Cristiano);
        //Login Test successfully
        System.out.println("Login Before!");
        Employee.Login(employees, 111, "PASSWORDS");
        System.out.println("Login AFTER!");


        //Login Test unsucc username
        System.out.println("Login Before!");
        Employee.Login(employees, 112, "PASSWORDS");
        System.out.println("Login AFTER!");


        //Login Test unsucc Password
        System.out.println("Login Before!");
        Employee.Login(employees, 111, "PASSWORDD");
        System.out.println("Login AFTER!");


        // FILE FUNCS FOR LEAVES 
        // FIX DEDUCTION CALCULATION (done w/out testing)
        //PERF (done w/out testing)
 

        System.out.println();
        System.out.println(0);
        System.out.println(0);
        System.out.println(0);


        leaveRequest.copyLeaveRequests(employees);
        leaveRequest.endLeaveRequests();

        for (leaveRequest leave : LeaveManagement.allRequests)
        {
            System.out.println(leave);
        }


        Employee.endEmployees(employees);

       

    }
}



















 // Employee yomna = new Employee(124, "yomna", "it", "manager", 500);
        // Employee menna = new Employee(125, "menna", "it", "manager", 500);
        // employees.add(badr);
        // employees.add(menna);
        // employees.add(yomna);

        //HR man = new HR(0, null, null, null, 0);
        // employees.add(man);