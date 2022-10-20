public class Vehicle {
    private String registration_number;
    private String brand;
    private int max_velocity;
    private int capacity;
    private int vehicle_type;
    private double purchase_cost;
    private double vehicle_tax;

    public Vehicle(String registration_number, String brand, int max_velocity, int capacity, int vehicle_type, double purchase_cost, double vehicle_tax) {
        this.registration_number = registration_number;
        this.brand = brand;
        this.max_velocity = max_velocity;
        this.capacity = capacity;
        this.vehicle_type = vehicle_type;
        this.purchase_cost = purchase_cost;
        this.vehicle_tax = vehicle_tax;
    }

    public Vehicle() {

    }

    public String getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(String registration_number) {
        this.registration_number = registration_number;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getMax_velocity() {
        return max_velocity;
    }

    public void setMax_velocity(int max_velocity) {
        this.max_velocity = max_velocity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(int vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public double getPurchase_cost() {
        return purchase_cost;
    }

    public void setPurchase_cost(double purchase_cost) {
        this.purchase_cost = purchase_cost;
    }

    public double getVehicle_tax() {
        return vehicle_tax;
    }

    public void setVehicle_tax(double vehicle_tax) {
        this.vehicle_tax = vehicle_tax;
    }

    @Override
    public String toString() {
        return registration_number + ":" + brand + ":" + max_velocity + ":" + capacity + ":" + vehicle_type + ":" + purchase_cost + ":" + String.format("%.2f", vehicle_tax) + ",";
    }
}