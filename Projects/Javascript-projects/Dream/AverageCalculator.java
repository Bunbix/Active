import java.util.Scanner;

class AverageCalculator {
    public static void main(String[] args) {
       Scanner input = new Scanner(System.in); 

        System.out.println("Enter first number: ");
        double num1 = input.nextDouble();

      System.out.println("Enter second number: ");  
       double num2 = input.nextDouble();

      System.out.println("Enter third number: ");
       double num3 = input.nextDouble();

double sum = num1 + num2 + num3;
double average = sum / 3;

System.out.println("The numbers are: " + num1 + ", " + num2 + ", " + num3);
System.out.println("Sum: " + sum);
System.out.println("Average: " + average);
    }

}