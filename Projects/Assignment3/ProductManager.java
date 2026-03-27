import java.util.Arrays;
import java.util.Random;
/**
 * ProductManager class with main method to manage products and perform array operations
 */
public class ProductManager {
    public static void main(String[] args) {
        System.out.println("=== PART 1: Product Information and Weekly Sales ===\n");

        // Create array of Product instances  
        Product[] products = createProductArray();

         // Display all products using for-each loop
        int productCount = 1;
        for (Product product : products) {
            System.out.println("Product " + productCount + ":");
            System.out.println(product);
            System.out.println();
            productCount++;
        }
        
         // Create and initialize weeklySales array
        int[] weeklySales = new int[40];
        Random random = new Random();
        
         // Initialize with random values (50-200 range)
        for (int i = 0; i < weeklySales.length; i++) {
            weeklySales[i] = 50 + random.nextInt(151);
        }
        
         // Display weekly sales
        System.out.println("Weekly Sales:");
        System.out.print("[");
        int count = 0;
        for (int sale : weeklySales) {
            System.out.print(sale);
            count++;
            if (count < weeklySales.length) {
                System.out.print(", ");
            }
            if (count % 10 == 0 && count < weeklySales.length) {
                System.out.print("\n ");
            }
        }
        System.out.println("]\n");
        
        System.out.println("=== PART 2: Monthly Sales ===\n");
        int[][] monthlySales = createMonthlySales(weeklySales);
        
        // Display monthly sales
        for (int month = 0; month < monthlySales.length; month++) {
            System.out.print("Month " + (month + 1) + ": [");
            int index = 0;
            for (int sale : monthlySales[month]) {
                System.out.print(sale);
                index++;
                if (index < monthlySales[month].length) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
        System.out.println();
        
        System.out.println("=== PART 3: Searching and Sorting ===\n");
        double averageMonth2 = findAverageSales(monthlySales[1]);
        System.out.println("Average Sales for Month 2: " + averageMonth2);
        
        // Sort and display prices for first product
        System.out.println("\nSorted Prices for " + products[0].getProductName() + ":");
        double[] sortedPrices = sortPrices(products[0].getPrices());
        System.out.print("[");
        for (int i = 0; i < sortedPrices.length; i++) {
            System.out.print(sortedPrices[i]);
            if (i < sortedPrices.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]\n");
        
        System.out.println("=== PART 4: Two-Dimensional Array ===\n");
        int[][] matrix = createMatrix(5, 5);
        System.out.println("Two-Dimensional Array:");
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
    
        /**
     * Creates an array of Product objects with sample data
         */
    private static Product[] createProductArray() {
        double[] laptopPrices = {899.99, 949.99, 999.99, 899.99, 949.99, 999.99, 849.99, 899.99, 949.99, 999.99};
        double[] phonePrices = {599.99, 649.99, 699.99, 749.99, 699.99, 749.99, 799.99, 849.99, 799.99, 849.99};
        double[] tabletPrices = {299.99, 349.99, 399.99, 449.99, 399.99, 449.99, 499.99, 549.99, 499.99, 549.99};
        double[] headphonePrices = {99.99, 129.99, 149.99, 179.99, 149.99, 179.99, 199.99, 229.99, 199.99, 229.99};
        double[] monitorPrices = {199.99, 249.99, 299.99, 349.99, 299.99, 349.99, 399.99, 449.99, 399.99, 449.99};
        double[] keyboardPrices = {49.99, 59.99, 69.99, 79.99, 69.99, 79.99, 89.99, 99.99, 89.99, 99.99};
        double[] mousePrices = {19.99, 29.99, 39.99, 49.99, 39.99, 49.99, 59.99, 69.99, 59.99, 69.99};
        double[] speakerPrices = {79.99, 99.99, 119.99, 139.99, 119.99, 139.99, 159.99, 179.99, 159.99, 179.99};
        double[] cameraPrices = {399.99, 449.99, 499.99, 549.99, 499.99, 549.99, 599.99, 649.99, 599.99, 649.99};
        double[] watchPrices = {199.99, 249.99, 299.99, 349.99, 299.99, 349.99, 399.99, 449.99, 399.99, 449.99};
        
        return new Product[] {
            new Product("Laptop", 1001, laptopPrices),
            new Product("Smartphone", 1002, phonePrices),
            new Product("Tablet", 1003, tabletPrices),
            new Product("Headphones", 1004, headphonePrices),
            new Product("Monitor", 1005, monitorPrices),
            new Product("Keyboard", 1006, keyboardPrices),
            new Product("Mouse", 1007, mousePrices),
            new Product("Speakers", 1008, speakerPrices),
            new Product("Camera", 1009, cameraPrices),
            new Product("Smartwatch", 1010, watchPrices)
        };
    }
    
    /**
     * Creates monthly sales arrays from weekly sales data
    */
    private static int[][] createMonthlySales(int[] weeklySales) {
        int[][] monthlySales = new int[3][4];
        int weeklyIndex = 0;
        for (int month = 0; month < 3; month++) {
            for (int week = 0; week < 4; week++) {
                monthlySales[month][week] = weeklySales[weeklyIndex];
                weeklyIndex++;
            }
        }
        return monthlySales;
    }
    
    /**
     * Calculates the average sales from an array
    */
    private static double findAverageSales(int[] sales) {
        int sum = 0;
        for (int sale : sales) sum += sale;
        return (double) sum / sales.length;
    }
    
    /**
     * Sorts an array of prices in ascending order
    */
    private static double[] sortPrices(double[] prices) {
        double[] sorted = Arrays.copyOf(prices, prices.length);
        Arrays.sort(sorted);
        return sorted;
    }
    
    /**
     * Creates a matrix with random values
     */
    private static int[][] createMatrix(int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = 1 + random.nextInt(9);
            }
        }
        return matrix;
    }
}