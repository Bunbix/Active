public class NewCar extends Car {
    private String color;
    
    // 2-parameter constructor
    public NewCar(double cost, String color) {
        super(cost);  // call superclass constructor
        this.color = color;
    }
    
    // equals method
    public boolean equals(NewCar other) {
        if (other == null) return false;
        return this.price == other.price && 
               this.color.equals(other.color);
    }
    
    // display method
    public void display() {
        System.out.printf("price = $%,.2f, color = %s%n", price, color);
    }
}