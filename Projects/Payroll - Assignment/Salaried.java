import java.util.Scanner;

public class Salaried extends Employee {
    protected double weeklySalary;
    
    @Override
    public void load() {
        super.load();
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Salary ==> ");
        weeklySalary = scanner.nextDouble();
    }
    
    @Override
    public double getEarnings() {
        paycheck = weeklySalary;
        applyPaycheckCap();
        getBonus();
        return paycheck;
    }
}
