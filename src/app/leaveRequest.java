package app;
import java.util.Date;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class leaveRequest {

    private Employee employee;
    private Date startDate;
    private Date endDate;
    private boolean isApproved;
    private boolean isValid;
    private String isApprovedstring;
    private String startDateString;
    private String endDateString;

    

    public String getStartDateString() {
        return startDateString;
    }



    public String getEndDateString() {
        return endDateString;
    }



    public leaveRequest(){

    }

    

    @SuppressWarnings("deprecation")
    public leaveRequest(Employee employee, Date startDate, Date endDate, boolean isApproved, boolean isValid) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isApproved = isApproved;
        this.isValid = isValid;
        isApprovedstring = String.valueOf(isApproved);
        startDateString = String.valueOf(startDate.getYear()+1900) + " - " + startDate.getMonth() + " - " + startDate.getDate();
        endDateString = String.valueOf(endDate.getYear()+1900) + " - " + endDate.getMonth() + " - " + endDate.getDate();
    }



    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getStartDate() {
        return startDate;
    }

    @SuppressWarnings("deprecation")
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
        startDateString = String.valueOf(startDate.getYear()+1900) + " - " + startDate.getMonth() + " - " + startDate.getDate();
    }

    public Date getEndDate() {
        return endDate;
    }

    @SuppressWarnings("deprecation")
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
        endDateString = String.valueOf(endDate.getYear()+1900) + " - " + endDate.getMonth() + " - " + endDate.getDate();

    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean isApproved) {
        this.isApproved = isApproved;
        isApprovedstring = String.valueOf(isApproved);
    }



    public boolean isValid() {
        return isValid;
    }



    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }








    @SuppressWarnings("deprecation")
    public static void copyLeaveRequests(ArrayList<Employee> employees) {
        try {
            Scanner scanner = new Scanner(new File("data/LeaveRequests.txt"));
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(",");
                if (line.length != 9)
                    continue;
                else{
                
                int employeeId = Integer.parseInt(line[0].trim()); 

                int startyear = Integer.parseInt(line[1].trim());
                int startmonth = Integer.parseInt(line[2].trim());
                int startday = Integer.parseInt(line[3].trim());
                int endyear = Integer.parseInt(line[4].trim());
                int endmonth = Integer.parseInt(line[5].trim());
                int endday = Integer.parseInt(line[6].trim());


                Date startDate = new Date(startyear, startmonth, startday); 
                Date endDate = new Date(endyear, endmonth, endday); 


                boolean isApproved = Boolean.parseBoolean(line[7].trim());
                boolean isValid = Boolean.parseBoolean(line[8].trim());
                
                
                Employee employee = Employee.findEmployeeById(employees,employeeId); 

                leaveRequest leaveRequest = new leaveRequest(employee, startDate, endDate, isApproved, isValid);
                
                LeaveManagement.allRequests.add(leaveRequest);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }



    public static void endLeaveRequests() {
        try {
            FileWriter writer = new FileWriter("data/LeaveRequests.txt", false);

            for (leaveRequest leaveRequest : LeaveManagement.allRequests) {
                writer.write(leaveRequestToString(leaveRequest) + "\n");
            }
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Helper method to convert LeaveRequest object to String format for file output
    @SuppressWarnings("deprecation")
    private static String leaveRequestToString(leaveRequest leaveRequest) {
        return String.format("%d, %s, %s, %s, %s, %s, %s, %b, %b",
                leaveRequest.getEmployee().getEmployeeId(),
                leaveRequest.getStartDate().getYear(),
                leaveRequest.getStartDate().getMonth(), 
                leaveRequest.getStartDate().getDate(),
                leaveRequest.getEndDate().getYear(),
                leaveRequest.getEndDate().getMonth(), 
                leaveRequest.getEndDate().getDate(), 
                leaveRequest.isApproved(),
                leaveRequest.isValid());
    }
    
    @Override
    public String toString() {
        return String.format("Employee ID: %d, IsValid: %b, IsApproved: %b",
                employee.getEmployeeId(),
                isValid,
                isApproved);
    }

    

    public String getIsApprovedstring() {
        return isApprovedstring;
    }
}
