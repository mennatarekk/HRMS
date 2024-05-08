package HR;
import java.util.Date;
import java.util.ArrayList;


public class LeaveManagement {

    static ArrayList<leaveRequest> allRequests = new ArrayList<>();


    public static boolean applyForLeave(Employee employee, Date startDate, Date endDate) {
        leaveRequest leave = new leaveRequest(employee, startDate, endDate, false, true);
        allRequests.add(leave);
        if (!isValidLeaveRequest(leave)) {
            leave.setValid(false);
            return false;
        }
        return true;
    }


    // Method to validate leave request
     static boolean isValidLeaveRequest(leaveRequest leave) {
    	 
    	 System.out.println(leave.getEmployee().getRemainingLeaveDays());
        if (calculateLeaveDays(leave.getStartDate(),leave.getEndDate()) < leave.getEmployee().getRemainingLeaveDays()) {
            return true;
        }
        return false;
    }

     static int calculateLeaveDays(Date startDate, Date endDate) {
        long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
        long diffInDays = (diffInMillies / (1000 * 60 * 60 * 24)) + 1; // Add 1 to include the end date
        return (int) diffInDays;
    }

    // Method for manager approval of leave
    

    // Method to update employee's leave balance
    public static void updateLeaveBalance(leaveRequest leave) {
        leave.getEmployee().setRemainingLeaveDays(leave.getEmployee().getRemainingLeaveDays() - calculateLeaveDays(leave.getStartDate(), leave.getEndDate()));
        System.out.println("Leave balance updated for employee: " + leave.getEmployee().getName());
    }
}

