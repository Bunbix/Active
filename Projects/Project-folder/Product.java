/**
 * @author Olabisi Afolabi
 * Product class representing a product with name, price, and product code.
 * This class demonstrates various OOP concepts including:
 * - Instance variables and constructors
 * - Overloaded methods
 * - Static variables and methods
 * - Named constants
 * - equals method override
 */
public class Product {
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
     * @param name The name of the product
     * @param price The price of the product
     * @param productCode The unique product code
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
     * Override equals method to compare Product objects by their attributes
     * @param obj The object to compare with
     * @return true if objects have same attribute values, false otherwise
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
