import java.util.Scanner;

/**
 * Population Growth
 * This program simulates population growth of organisms over time
 * based on user input for starting population, daily increase percentage,
 * and number of days.
 */
class PopulationGrowth {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Population Growth Simulator");
        System.out.println("============================");
        
        System.out.print("Enter the starting number of organisms (must be 2 or more): ");
        int startingOrganisms = scanner.nextInt();
        
        if (startingOrganisms < 2) {
            System.out.println("Error: Starting number of organisms must be 2 or more.");
            scanner.close();
            return;
        }
        
        System.out.print("Enter the average daily increase as percentage (must not be negative): ");
        double dailyIncreasePercent = scanner.nextDouble();
        
        if (dailyIncreasePercent < 0) {
            System.out.println("Error: Average daily increase percentage must not be negative.");
            scanner.close();
            return;
        }
        
        double dailyIncreaseDecimal = dailyIncreasePercent / 100;
        
        System.out.print("Enter the number of days (must be 1 or more): ");
        int numberOfDays = scanner.nextInt();
        
        if (numberOfDays < 1) {
            System.out.println("Error: Number of days must be 1 or more.");
            scanner.close();
            return;
        }
        
        scanner.close();
        
        System.out.println("\nPopulation Growth Simulation Results:");
        System.out.println("====================================");
        
        double currentPopulation = startingOrganisms;
        
        for (int day = 1; day <= numberOfDays; day++) {
            String formattedPopulation = String.format("Day %d: %.2f organisms", day, currentPopulation);
            System.out.println(formattedPopulation);
            
            if (day < numberOfDays) {
                currentPopulation = currentPopulation * (1 + dailyIncreaseDecimal);
            }
        }
    }
}