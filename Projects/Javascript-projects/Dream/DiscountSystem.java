import java.util.Scanner;

/**
 * Discount System
 * This program calculates employee discounts based on job titles
 * using a switch statement and user input.
 */
class DiscountSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to the Employee Discount System!");
        System.out.println("========================================");
        
        System.out.print("Please enter your job title: ");
        String jobTitle = scanner.nextLine().toLowerCase().trim();
        
        if (jobTitle.equals("manager")) {
            System.out.println("Managers cannot participate in the discount system.");
        } else {
            System.out.print("Please enter the price of the item you wish to buy: $");
            double originalPrice = scanner.nextDouble();
            
            double discountRate = 0.0;
            double discountedPrice = 0.0;
            
            switch (jobTitle) {
                case "supervisor":
                    discountRate = 0.10; 
                    break;
                case "sales representative":
                case "salesrepresentative": 
                    discountRate = 0.15; 
                    break;
                default:
                    discountRate = 0.20; 
                    break;
            }
            
            discountedPrice = originalPrice * (1 - discountRate);
            
            System.out.printf("Your final price is: $%.2f%n", discountedPrice);
        }
        
        scanner.close();
    }
}