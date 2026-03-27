/**
 * PersonDemo class - the driver class to test the Person class
 * This demonstrates object instantiation and method calling
 */
public class PersonDemo {
    public static void main(String[] args) {
        // Create two instances of Person class
        Person person1 = new Person("John", 25, "john@example.com");
        Person person2 = new Person("Alice", 17, "alice@example.com");
        
        // Display information for both persons
        System.out.println("Person 1:");
        person1.displayInfo();
        System.out.println();  
        
        System.out.println("Person 2:");
        person2.displayInfo();
        System.out.println();  
        
        // Using return statement
        int currentYear = 2024;  // Assuming current year is 2024
        
        System.out.println("Person 1's Birth Year: " + 
                          person1.calculateBirthYear(currentYear));
        System.out.println("Person 2's Birth Year: " + 
                          person2.calculateBirthYear(currentYear));
        System.out.println();  // Blank line
        
        // Using specialized boolean method
        System.out.println("Person 1 is an adult: " + person1.isAdult());
        System.out.println("Person 2 is an adult: " + person2.isAdult());

        // Explanation for the local variable access and return statement behavior.

System.out.println("\n=== EXPLANATION: Local Variable Scope ===");
System.out.println("The 'address' variable in displayInfo() is LOCAL:");
System.out.println("1. It's declared INSIDE the displayInfo() method");
System.out.println("2. It only exists while displayInfo() is running");
System.out.println("3. PersonDemo CANNOT access it: person1.address = ERROR!");
System.out.println("4. This demonstrates METHOD SCOPE vs CLASS SCOPE");
    }
}

