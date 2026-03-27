import java.text.NumberFormat;

public class Main {
    public static void main(String[] args) {
        // Method 1: Simple approach
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        String result = percentFormat.format(0.1);
        System.out.println(result);
    }
}