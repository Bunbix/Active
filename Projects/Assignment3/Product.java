/**
 * Product class representing a product with name, ID, and price history
 */
public class Product {
    private String productName;
    private int productID;
    private double[] prices;
    /**
     * Constructor to initialize Product object
     */
    public Product(String productName, int productID, double[] prices) {
        this.productName = productName;
        this.productID = productID;
        this.prices = prices;
    }
    /**
     * Then returns a string representation of the product
     * @return Formatted string with product details
     */
    public String getProductName() {
        return productName;
    }
    
    public int getProductID() {
        return productID;
    }
    
    public double[] getPrices() {
        return prices;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Product Name: ").append(productName).append("\n");
        sb.append("Product ID: ").append(productID).append("\n");
        sb.append("Prices: [");
        for (int i = 0; i < prices.length; i++) {
            sb.append(prices[i]);
            if (i < prices.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}