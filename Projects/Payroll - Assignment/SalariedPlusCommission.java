import java.util.Scanner;

public class SalariedPlusCommission extends Salaried {
    private double sales;
    private double commissionRate;
    
    @Override
    public void load() {
        super.load(); 
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Sales for this past week ==> ");
        sales = scanner.nextDouble();
        
        System.out.print("Sales commission rate (fraction paid to employee) ==> ");
        commissionRate = scanner.nextDouble();
    }
    
    @Override
    public double getEarnings() {
        double commission = sales * commissionRate;
        paycheck = weeklySalary + commission;
        applyPaycheckCap();
        getBonus();
        return paycheck;
    }
}