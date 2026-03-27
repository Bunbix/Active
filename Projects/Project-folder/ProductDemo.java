/**
 * @Author Olabisi Afolabi
 * ProductDemo class demonstrates the usage of Product class.
 * This class tests all the required functionality from the assignment.
 */
public class ProductDemo {
    public static void main(String[] args) {
        System.out.println("=== Part 1: Creating Classes and Objects ===\n");
        
        // Create first product instance
        Product product1 = new Product("Laptop", 999.99, 12345);
        System.out.println("Product 1 Information:");
        product1.printProductInfo();
        System.out.println("Product Code: " + product1.getProductCode());
        
        System.out.println("\n=== Part 2: Reference Assignment and Equality Testing ===\n");
        
        // Create second product with same values
        Product product2 = new Product("Laptop", 999.99, 12345);
        System.out.println("Product 2 Information:");
        product2.printProductInfo();
        System.out.println("Product Code: " + product2.getProductCode());
        
        // Compare the two products
        System.out.println("\nProduct 1 and Product 2 are equal: " + 
                          product1.equals(product2));
        
        System.out.println("\n=== Part 3: Overloaded Methods and Constructors ===\n");
        
        // Create third product
        Product product3 = new Product("Tablet", 299.99, 54321);
        System.out.println("Product 3 Information:");
        product3.printProductInfo(); // Without product code
        System.out.println("\nProduct 3 Information with Product Code:");
        product3.printProductInfo(true); // With product code
        
        System.out.println("\n=== Part 4: Static Variables and Methods, Named Constants ===\n");
        
        // Create fourth product
        Product product4 = new Product("Smartphone", 799.99, 67890);
        System.out.println("Product 4 Information:");
        product4.printProductInfo(true);
        
        // Display total product count using static method
        System.out.println("\nTotal Product Count: " + Product.getProductCount());
        
        // Calculate and display tax for product1 using the named constant
        double taxAmount = product1.calculateTax();
        System.out.println("Tax Amount for Product 1: $" + 
                          String.format("%.3f", taxAmount));
        
        System.out.println("\n=== Summary ===");
        System.out.println("All tasks completed successfully!");
    }
}