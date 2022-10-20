public class Property {
    private int property_id;
    private double base_price;
    private boolean located_in_city;
    private int property_age;
    private int built_up_area;
    private double property_tax;

    public Property(int property_id, double base_price, int built_up_area, int property_age, boolean located_in_city, double property_tax) {
        this.property_id = property_id;
        this.base_price = base_price;
        this.located_in_city = located_in_city;
        this.property_age = property_age;
        this.built_up_area = built_up_area;
        this.property_tax = property_tax;
    }

    public int getProperty_id() {
        return property_id;
    }

    public void setProperty_id(int property_id) {
        this.property_id = property_id;
    }

    public double getBase_price() {
        return base_price;
    }

    public void setBase_price(double base_price) {
        this.base_price = base_price;
    }

    public boolean isLocated_in_city() {
        return located_in_city;
    }

    public void setLocated_in_city(boolean located_in_city) {
        this.located_in_city = located_in_city;
    }

    public int getProperty_age() {
        return property_age;
    }

    public void setProperty_age(int property_age) {
        this.property_age = property_age;
    }

    public int getBuilt_up_area() {
        return built_up_area;
    }

    public void setBuilt_up_area(int built_up_area) {
        this.built_up_area = built_up_area;
    }

    public double getProperty_tax() {
        return property_tax;
    }

    public void setProperty_tax(double property_tax) {
        this.property_tax = property_tax;
    }

    @Override
    public String toString() {
        return property_id + ":" + base_price + ":" + built_up_area + ":" + property_age + ":" + ((located_in_city) ? "Y" : "N") + ":" + String.format("%.2f", property_tax) + ",";
    }
}