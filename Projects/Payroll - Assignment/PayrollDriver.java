import java.util.Scanner;
import java.util.ArrayList;

public class PayrollDriver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Employee> employees = new ArrayList<>();
        
        System.out.print("Number of employees: ");
        int numEmployees = scanner.nextInt();
        System.out.println();
        
               for (int i = 0; i < numEmployees; i++) {
            System.out.println("PROFILE FOR EMPLOYEE #" + (i + 1) + ":");
            System.out.println("type Hourly(1), Salaried(2), Salaried plus Commission(3)");
            System.out.print("Enter 1, 2, or 3 ==> ");
            int type = scanner.nextInt();
            scanner.nextLine(); 
            
            Employee employee = null;
            
            switch (type) {
                case 1:
                    employee = new Hourly();
                    break;
                case 2:
                    employee = new Salaried();
                    break;
                case 3:
                    employee = new SalariedPlusCommission();
                    break;
                default:
                    System.out.println("Invalid type. Using Hourly as default.");
                    employee = new Hourly();
            }
            
            employee.load();
            employee.getEarnings(); 
            System.out.println();
            
            employees.add(employee);
        }
        
        // Print paycheck report
        System.out.println("PAYCHECK REPORT:");
        for (Employee emp : employees) {
            System.out.print(emp.toString());
        }
        
        scanner.close();
    }
}