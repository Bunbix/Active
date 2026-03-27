import java.util.Scanner;
class TimeConverter {
  public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of minutes: ");
        double minutes = scanner.nextDouble();
        
        double hours = minutes / 60;
        double days = minutes / 1440;
        
        System.out.println("\n" + minutes + " minutes = " + hours + " hours");
        System.out.println(minutes + " minutes = " + days + " days");
        scanner.close();
    }   
}
