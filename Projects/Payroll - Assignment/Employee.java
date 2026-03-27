import java.util.Calendar;
import java.util.Scanner;

public abstract class Employee {
    protected String name;
    protected String socialSecurityNumber;
    protected int birthdayMonth;
    protected int birthdayWeek;
    protected double paycheck;
    
        protected static final double MAX_PAYCHECK = 1000.0;
    protected static final double BONUS_AMOUNT = 100.0;
    
    public abstract double getEarnings();
    
    public void load() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Name ==> ");
        name = scanner.nextLine();
        
        System.out.print("Social security number ==> ");
        socialSecurityNumber = scanner.nextLine();
        
        System.out.print("Birthday month (1-12) ==> ");
        birthdayMonth = scanner.nextInt();
        
        System.out.print("Birthday bonus week (1-4) ==> ");
        birthdayWeek = scanner.nextInt();
    }
    
    public String toString() {
        return String.format("employee: %s\nsocial security number: %s\npaycheck: $%.2f\n", 
                             name, socialSecurityNumber, paycheck);
    }
    
    public void getBonus() {
        Calendar today = Calendar.getInstance();
        int currentMonth = today.get(Calendar.MONTH) + 1; // Calendar months are 0-based
        int currentWeek = today.get(Calendar.WEEK_OF_MONTH);
        
        // Adjust week to 1-4 scale (some months have 5 weeks)
        if (currentWeek > 4) {
            currentWeek = 4;
        }
        
        if (birthdayMonth == currentMonth && birthdayWeek == currentWeek) {
            paycheck += BONUS_AMOUNT;
        }
    }
    
    protected void applyPaycheckCap() {
        if (paycheck > MAX_PAYCHECK) {
            paycheck = MAX_PAYCHECK;
        }
    }
}