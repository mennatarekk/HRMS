package HR;
import java.util.Date;
public class PerformanceEvaluation {

    public static void calculatePerformanceRate(Employee employee, double remainigDays, Date startDate, Date endDate) {
        int leaveDays = LeaveManagement.calculateLeaveDays(startDate, endDate);
        double performanceRating = employee.getCurrentPerformanceRating();
        if(remainigDays > leaveDays)
            return;

        else if(employee.getRemainingLeaveDays() < 0){
            employee.setCurrentPerformanceRating(performanceRating - 0.5*Math.abs(leaveDays));
        }
        // remaining days is positive & will go to negative
        else {
            employee.setCurrentPerformanceRating(performanceRating - 0.5*Math.abs(remainigDays - leaveDays));

        }
    }

}


