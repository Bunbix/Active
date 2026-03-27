/**
 * Person class representing a person with name, age, and email
 * First OOP Class
 */
public class Person {
    // Instance variables(attributes) - private for encapsulation
    private String name;
    private int age;
    private String email;
    
    // Constructor - to implement or initialize person objects 
    
    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
    
    // displayInfo() method - Displays the person's information - This method demonstrates abstraction by showing only essential info and only accessible within this method
    public void displayInfo() {
        // Local variable 
        String address = "123 Main St";  // LOCAL VARIABLE - can't access outside
        
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Email: " + email);

        System.out.println("Address (local var): " + address);
    }
    
    // calculateBirthYear() method 
    public int calculateBirthYear(int currentYear) {
        // Using return statement to send value back to caller 
        return currentYear - age;
    }
    
    // isAdult() method - Checks if the person is an adult (age >= 18)  return true if adult, false otherwise
    public boolean isAdult() {
        // Specialized boolean method - Boolean return statement
        return age >= 18;
    }
}