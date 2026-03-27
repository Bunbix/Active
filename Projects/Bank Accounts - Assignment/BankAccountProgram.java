// Abstract BankAccount class
abstract class BankAccount {
    protected double balance;
    
    public BankAccount() {
        balance = 0.0;
    }
    
     public BankAccount(double initialBalance) {
               if (initialBalance < 0) {
            balance = 0;
        } else {
            balance = initialBalance;
        }
    }
    
       public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
    
    public void withdraw(double amount) {
        if (amount > 0) {
            balance -= amount;
            if (balance < 0) {
                balance = 0;
            }
        }
    }
    
    public double getBalance() {
        return balance;
    }
    
    public abstract void display();
    
    @Override
    public String toString() {
        return String.format("$%,.2f", balance);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BankAccount that = (BankAccount) obj;
        return Double.compare(that.balance, balance) == 0;
    }
}

// Checking class
class Checking extends BankAccount {
    
      public Checking() {
        super();
    }
    
       public Checking(double initialBalance) {
        super(initialBalance);
    }
    
       public void writeACheck(double amount) {
        if (amount > 0) {
            balance -= (amount + 1.0);
            if (balance < 0) {
                balance = 0;
            }
        }
    }
    
    @Override
    public void display() {
        System.out.println("Checking account balance = " + this.toString());
    }
    
    @Override
    public String toString() {
        return String.format("$%,.2f", balance);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        return true;
    }
}

// Savings class
class Savings extends BankAccount {
    private double intRate; 
    
      public Savings() {
        super();
        intRate = 0.0;
    }
    
       public Savings(double initialBalance, double interestRate) {
        super(initialBalance);
        this.intRate = interestRate;
    }
    
       public void addInterest() {
        double interest = intRate * balance;
        balance += interest;
    }
    
       public double getIntRate() {
        return intRate;
    }
    
    public void setIntRate(double interestRate) {
        this.intRate = interestRate;
    }
    
    @Override
    public void display() {
        System.out.println("Savings account balance = " + this.toString());
    }
    
    @Override
    public String toString() {
        return String.format("$%,.2f", balance);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        Savings savings = (Savings) obj;
        return Double.compare(savings.intRate, intRate) == 0;
    }
}

// Driver class
public class BankAccountProgram {
    public static void main(String[] args) {
        BankAccount[] accounts = new BankAccount[100];
        
        accounts[0] = new Savings(1100, .05);
        accounts[0].deposit(100);
        accounts[0].withdraw(200);
        ((Savings) accounts[0]).addInterest();
        
        accounts[1] = new Checking(-100);
        accounts[1].deposit(50);
        
        accounts[2] = new Checking(200);
        accounts[2].withdraw(210);
        accounts[2].deposit(100);
        ((Checking) accounts[2]).writeACheck(50);
        
        for (int i=0; i<accounts.length && accounts[i] != null; i++) {
            accounts[i].display();
        }
    } 
}