/**
 * SavingsAccount class representing a bank savings account with monthly
 * compounding interest.
 */
public class SavingsAccount {
    // Class variable (static) for annual interest rate
    private static double annualInterestRate;
    
    // Instance constant for account number (final)
    private final int ACCOUNT_NUMBER;
    
    // Instance variable for balance
    private double balance;
    
    /**
     * Two-parameter constructor to initialize account number and balance
     * @param accountNumber The account number (constant)
     * @param initialBalance The initial balance
     */
    public SavingsAccount(int accountNumber, double initialBalance) {
        this.ACCOUNT_NUMBER = accountNumber;
        this.balance = initialBalance;
    }
    
    /**
     * Accessor for account number
     * @return The account number
     */
    public int getAccountNumber() {
        return ACCOUNT_NUMBER;
    }
    
    /**
     * Accessor for balance
     * @return The current balance
     */
    public double getBalance() {
        return balance;
    }
    
    /**
     * Updates the balance by adding one month's interest
     * Interest calculation: balance * annualInterestRate / 12
     */
    public void addMonthlyInterest() {
        double monthlyInterest = balance * annualInterestRate / 12;
        balance += monthlyInterest;
    }
    
    /**
     * Class method to set the annual interest rate
     * @param rate The annual interest rate (as a decimal, e.g., 0.05 for 5%)
     */
    public static void setAnnualInterestRate(double rate) {
        annualInterestRate = rate;
    }
    
    /**
     * Class method to get the annual interest rate
     * @return The current annual interest rate
     */
    public static double getAnnualInterestRate() {
        return annualInterestRate;
    }
}