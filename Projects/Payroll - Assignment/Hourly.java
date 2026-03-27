import java.util.Scanner;

public class Hourly extends Employee {
    private double hourlyPay;
    private double hoursWorked;
    
      private static final double OVERTIME_THRESHOLD = 40.0;
    private static final double OVERTIME_RATE = 1.5;
    
    @Override
    public void load() {
        super.load();
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Hourly pay ==> ");
        hourlyPay = scanner.nextDouble();
        
        System.out.print("Hours worked this past week ==> ");
        hoursWorked = scanner.nextDouble();
    }
    
    @Override
    public double getEarnings() {
        if (hoursWorked <= OVERTIME_THRESHOLD) {
            paycheck = hourlyPay * hoursWorked;
        } else {
            double regularPay = hourlyPay * OVERTIME_THRESHOLD;
            double overtimeHours = hoursWorked - OVERTIME_THRESHOLD;
            double overtimePay = hourlyPay * OVERTIME_RATE * overtimeHours;
            paycheck = regularPay + overtimePay;
        }
        
        applyPaycheckCap();
        getBonus();
        return paycheck;
    }
}