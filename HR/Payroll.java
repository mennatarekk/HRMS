package HR;

public class Payroll {
    public double calculateTax(double salary) {
        // Example: Assume a flat tax rate of 20%
        double taxRate = 0.20;
        double taxAmount = salary * taxRate;
        return taxAmount;
    }

    public static double calculateDeduction(Employee employee, int days) {
      return employee.getSalary()*days*0.03;
    }
    

    // Method to calculate deductions
    public static void deductSalary(Employee employee, double d) {
        employee.setSalary(employee.getSalary()-d);
    }
    
    
}

