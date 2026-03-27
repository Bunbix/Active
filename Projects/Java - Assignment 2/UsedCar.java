public class UsedCar extends Car {
    private int mileage;
    
    // 2-parameter constructor
    public UsedCar(double cost, int mileage) {
        super(cost);  // call superclass constructor
        this.mileage = mileage;
    }
    
    // equals method
    public boolean equals(UsedCar other) {
        if (other == null) return false;
        return this.price == other.price && 
               this.mileage == other.mileage;
    }
    
    // display method
    public void display() {
        System.out.printf("price = $%,.2f, mileage = %,d%n", price, mileage);
    }
}