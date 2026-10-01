public class UseCase3 {
    public static void main(String[] args) {
        Rentable car = new Car();
        Rentable bike = new Bike();
 
        double carRent = car.calculateRent(3);
        double bikeRent = bike.calculateRent(2);
 
        System.out.println("Car rent incl. tax: " + (carRent + carRent * Car.TAX_RATE));
        System.out.println("Bike rent incl. tax: " + (bikeRent + bikeRent * Bike.TAX_RATE));
        System.out.println("Total vehicles rented: " + Car.totalVehiclesRented);
    }
}
interface Rentable {
    static double TAX_RATE = 0.05;
    double calculateRent(int days);
}
class Car implements Rentable{
    static int totalVehiclesRented =0;
    public double calculateRent(int days) {
        totalVehiclesRented++;
        return days * 1500.0; // Rent for car is $50 per day
    }
}
class Bike implements Rentable{
    static int totalVehiclesRented =0;
    public double calculateRent(int days) {
        totalVehiclesRented++;
        return days * 500.0; // Rent for bike is $20 per day
    }
}
