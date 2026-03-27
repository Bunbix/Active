public class Car {
    protected double price;
    
    // 1-parameter constructor
    public Car(double cost) {
        price = cost * 2;
    }
    
    // getPrice method - accessor
    public double getPrice() {
        return price;
    }
}