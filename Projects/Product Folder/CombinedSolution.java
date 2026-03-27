/**
 * Combined solution for Assignment 2 in a single file
 */
public class CombinedSolution {
    
    /**
     * Product class representing a product
     */
    static class Product {
        // Instance variables
        private String name;
        private double price;
        private int productCode;
        
        // Static variable to track number of products created
        private static int productCount = 0;
        
        // Named constant for tax rate
        public static final double TAX_RATE = 0.1; // 10% tax rate
        
        /**
         * Constructor to initialize a Product object
         */
        public Product(String name, double price, int productCode) {
            this.name = name;
            this.price = price;
            this.productCode = productCode;
            productCount++; // Increment count each time a product is created
        }
        
        // Getter methods
        public String getName() {
            return name;
        }
        
        public double getPrice() {
            return price;
        }
        
        public int getProductCode() {
            return productCode;
        }
        
        /**
         * Overloaded printProductInfo method with no arguments
         * Prints product information without product code
         */
        public void printProductInfo() {
            System.out.println("Name: " + name);
            System.out.println("Price: $" + String.format("%.2f", price));
        }
        
        /**
         * Overloaded printProductInfo method with boolean argument
         * @param includeCode If true, includes product code in output
         */
        public void printProductInfo(boolean includeCode) {
            if (includeCode) {
                System.out.println(name + ", $" + String.format("%.2f", price) + 
                                 ", Product Code: " + productCode);
            } else {
                printProductInfo(); // Reuse the no-argument version
            }
        }
        
        /**
         * Static method to get the total number of products created
         * @return The count of products
         */
        public static int getProductCount() {
            return productCount;
        }
        
        /**
         * Calculates tax amount for this product
         * @return The tax amount
         */
        public double calculateTax() {
            return price * TAX_RATE;
        }
        
        /**
         * Override equals method to compare Product objects
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            
            Product product = (Product) obj;
            return productCode == product.productCode &&
                   Double.compare(product.price, price) == 0 &&
                   name.equals(product.name);
        }
    }
    
    /**
     * Main demo class
     */
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