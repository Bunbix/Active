/**
 * Driver class for testing the SavingsAccount class.
 * Creates two savings accounts and displays monthly balances for one year.
 */
public class SavingsAccountDriver {
    public static void main(String[] args) {
        // Step 1: Instantiate the saver1 and saver2 objects
        SavingsAccount saver1 = new SavingsAccount(10002, 2000.00);
        SavingsAccount saver2 = new SavingsAccount(10003, 3000.00);
        
        // Step 2: Set the annual interest rate to 5%
        SavingsAccount.setAnnualInterestRate(0.05);
        
        // Step 3: Print the table heading
        System.out.println("Monthly balances for one year with 0.05 annual interest:");
        System.out.println("Month\tAccount #\tBalance\t\tAccount #\tBalance");
        System.out.println("----\t--------\t-------\t\t--------\t-------");
        
        // Step 4: Use a for loop to print initial balances and monthly updates
        for (int month = 0; month <= 12; month++) {
            // Print current month's balances
            System.out.printf("%d\t%d\t\t%.2f\t\t%d\t\t%.2f%n", 
                month, 
                saver1.getAccountNumber(), 
                saver1.getBalance(),
                saver2.getAccountNumber(), 
                saver2.getBalance());
            
            // Add monthly interest for next month (if not the last month)
            if (month < 12) {
                saver1.addMonthlyInterest();
                saver2.addMonthlyInterest();
            }
        }
        
        // Step 5: Compute and display the total of both balances
        double totalBalance = saver1.getBalance() + saver2.getBalance();
        System.out.printf("%nFinal balance of both accounts combined: %.2f%n", totalBalance);
    }
}