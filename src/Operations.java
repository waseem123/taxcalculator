import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Operations {
    String file_properties = "src/properties.txt";
    String file_vehicles = "src/vehicles.txt";
    private String file_credentials = "src/credentials.txt";


    public List<Property> getProperties() {
        List<Property> properties = new ArrayList<>();
        File file = new File(file_properties);
        Scanner sc;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (sc.hasNext()) {
            String[] lines = sc.nextLine().split(",");
            for (String line : lines) {
                String[] content = line.split(":");
                properties.add(new Property(
                        Integer.parseInt(content[0]),
                        Double.parseDouble(content[1]),
                        Integer.parseInt(content[2]),
                        Integer.parseInt(content[3]),
                        Objects.equals(content[4], "Y"),
                        Double.parseDouble(content[5]))
                );
            }
        }
        return properties;
    }

    public void addProperty() {
        Scanner sc = new Scanner(System.in);
        System.out.println("ENTER THE PROPERTY DETAILS - ");

        Validator validator = new Validator();
        boolean isNotValid;

        System.out.print("ENTER THE BASE VALUE OF LAND - ");
        String base_value_of_land;
        do {
            isNotValid = false;
            base_value_of_land = sc.next();
            if (validator.isValidNumber(base_value_of_land)) {
                double base_value = Double.parseDouble(base_value_of_land);
                if (!(base_value >= 5000 && base_value <= 50000)) {
                    isNotValid = true;
                    System.out.print("PLEASE ENTER THE VALID PURCHASE AMOUNT OF PROPERTY BETWEEN 5000 TO 50000 - ");
                }
            } else {
                isNotValid = true;
                System.out.print("PLEASE ENTER THE VALID PURCHASE AMOUNT OF PROPERTY BETWEEN 5000 TO 50000 - ");
            }
        } while (isNotValid);

        System.out.print("ENTER THE BUILT-UP AREA OF LAND - ");
        String built_up_area;
        do {
            isNotValid = false;
            built_up_area = sc.next();
            if (validator.isValidNumber(built_up_area)) {
                double area = Double.parseDouble(built_up_area);
                if (!(area >= 10 && area <= 30)) {
                    isNotValid = true;
                    System.out.print("PLEASE ENTER THE VALID BUILT-UP AREA BETWEEN 10 TO 30 - ");
                }
            } else {
                isNotValid = true;
                System.out.print("PLEASE ENTER THE VALID BUILT-UP AREA BETWEEN 10 TO 30 - ");
            }
        } while (isNotValid);

        System.out.print("ENTER THE AGE OF LAND IN YEARS - ");
        String age_of_land;
        do {
            isNotValid = false;
            age_of_land = sc.next();
            if (validator.isValidNumber(built_up_area)) {
                double age = Integer.parseInt(age_of_land);
                if (!(age >= 1 && age <= 30)) {
                    isNotValid = true;
                    System.out.print("PLEASE ENTER THE VALID AGE BETWEEN 1 TO 3 - ");
                }
            } else {
                isNotValid = true;
                System.out.print("PLEASE ENTER THE VALID AGE BETWEEN 1 TO 3 - ");
            }
        } while (isNotValid);
        System.out.print("IS THE LAND LOCATED IN CITY?(Y: YES, N: NO) - ");
        char city;
        do {
            isNotValid = false;
            city = sc.next().charAt(0);
            /*if (city != 'Y' || city != 'y' || city != 'n' || city != 'N') {
                isNotValid = true;
            }*/
            switch (city) {
                case 'Y':
                case 'y':
                case 'N':
                case 'n':
                    break;
                default:
                    isNotValid = true;
            }
        } while (isNotValid);


        List<Property> properties = getProperties();
        Property p;
        int id = 0;
        if (properties.size() > 0)
            id = properties.get(properties.size() - 1).getProperty_id();
        p = new Property(id + 1, Double.parseDouble(base_value_of_land), Integer.parseInt(built_up_area), Integer.parseInt(age_of_land), (city == 'Y' || city == 'y'), 0);

        properties.add(p);

        File file = new File(file_properties);
        FileWriter fw;
        try {
            fw = new FileWriter(file, true);
            fw.write(p.toString());
            fw.close();
            System.out.println("PROPERTY HAS SUCCESSFULLY ADDED.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getPropertyTax() {
        Scanner sc = new Scanner(System.in);
        List<Property> properties = getProperties();
        if (properties.size() > 0) {
            displayProperties(properties);
            System.out.print("ENTER THE PROPERTY ID TO CALCULATE THE TAX - ");
            int id = sc.nextInt() - 1;

            if (isPropertyPresent((id + 1))) {
                Property p = properties.get(id);
                double base_price = p.getBase_price();
                int built_up_area = p.getBuilt_up_area();
                int age = p.getProperty_age();
                double tax;

                if (p.isLocated_in_city()) {
                    tax = ((built_up_area * age * base_price) + (0.5 * built_up_area)) / 5;
                } else {
                    tax = (built_up_area * age * base_price) / 5;
                }
                p.setProperty_tax(tax);

                File file = new File(file_properties);
                FileWriter fw;
                try {
                    fw = new FileWriter(file);

                    for (Property property : properties) {
                        fw.write(property.toString());
                    }
                    fw.close();
                    System.out.println("PROPERTY TAX FOR PROPERTY ID - " + (id + 1) + " IS " + tax);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("ERROR : PROPERTY ID YOU ARE LOOKING FOR IS NOT PRESENT.");
            }
        } else {
            System.out.println("ERROR : NO PROPERTY PRESENT AT THIS MOMENT.");
        }
    }

    private boolean isPropertyPresent(int property_id) {
        List<Property> properties = getProperties();
        boolean flag = false;
        for (Property p : properties) {
            if (p.getProperty_id() == property_id) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public void getAllProperties() {
        displayProperties(getProperties());
    }

    private void displayProperties(List<Property> properties) {
        if (properties.size() > 0) {
            System.out.println("===================================================================================================");
            System.out.printf("%5s %17s %17s %17s %17s %17s", "ID", "BUILT-UP AREA", "BASE PRICE", "AGE(YEARS)", "IN CITY", "PROPERTY TAX");
            System.out.println();
            System.out.println("===================================================================================================");
            for (Property p : properties) {
                System.out.format("%5s %17s %17.2f %17s %17s %17.2f", p.getProperty_id(), p.getBuilt_up_area(), p.getBase_price(), p.getProperty_age(), p.isLocated_in_city() ? "Y" : "N", p.getProperty_tax());
                System.out.println();
            }
            System.out.println("===================================================================================================");
        } else {
            System.out.println("ERROR : NO DATA PRESENT AT THIS MOMENT.");
        }
    }

    public void addVehicle() {
        List<Vehicle> vehicles = getVehicles();
        Scanner sc = new Scanner(System.in);
        String brand;
        String max_velocity;
        String capacity;
        String vehicle_type;
        String purchase_cost;
        String reg_no;
        Validator validator = new Validator();
        System.out.print("ENTER THE VEHICLE REGISTRATION NUMBER - ");
        boolean isNotValid;
        do {
            isNotValid = false;
            reg_no = sc.next();
            if (!validator.isValidRegistrationNumber(reg_no)) {
                System.out.print("PLEASE ENTER A VALID REGISTRATION NUMBER - ");
                isNotValid = true;
            } else if (isVehiclePresent(reg_no)) {
                System.out.println("VEHICLE WITH SAME REGISTRATION NUMBER ALREADY EXISTS.");
                System.out.print("PLEASE ENTER A VALID REGISTRATION NUMBER - ");
                isNotValid = true;
            }
        } while (isNotValid);

        System.out.print("ENTER BRAND OF THE VEHICLE - ");
        do {
            isNotValid = false;
            brand = sc.next();
            if (!validator.isValidBrand(brand)) {
                System.out.print("ENTER A VALID BRAND OF THE VEHICLE - ");
                isNotValid = true;
            }
        } while (isNotValid);

        System.out.print("ENTER THE MAXIMUM VELOCITY OF THE VEHICLE(KMPH) - ");
        do {
            isNotValid = false;
            max_velocity = sc.next();
            if (validator.isValidNumber(max_velocity)) {
                int velocity = Integer.parseInt(max_velocity);
                if (!(velocity >= 120 && velocity <= 300)) {
                    isNotValid = true;
                    System.out.print(" ENTER THE MAXIMUM VELOCITY RANGING BETWEEN 120kmph TO 300kmph - ");
                }
            } else {
                isNotValid = true;
                System.out.print("PLEASE ENTER THE MAXIMUM VELOCITY RANGING BETWEEN 120kmph TO 300kmph - ");
            }
        } while (isNotValid);

        System.out.print("ENTER CAPACITY(NUMBER OF SEATS) OF THE VEHICLE - ");
        do {
            isNotValid = false;
            capacity = sc.next();
            if (validator.isValidNumber(capacity)) {
                int cap = Integer.parseInt(capacity);
                if (!(cap >= 2 && cap <= 50)) {
                    isNotValid = true;
                    System.out.print("PLEASE ENTER THE CAPACITY RANGING BETWEEN 2 TO 50 SEATS - ");
                }
            } else {
                isNotValid = true;
                System.out.print("PLEASE ENTER THE CAPACITY RANGING BETWEEN 2 TO 50 SEATS - ");
            }
        } while (isNotValid);

        do {
            isNotValid = false;
            System.out.println("CHOOSE THE TYPE OF THE VEHICLE - ");
            System.out.println("1. PETROL DRIVEN");
            System.out.println("2. DIESEL DRIVEN");
            System.out.println("3. CNG/LPG DRIVEN");
            vehicle_type = sc.next();
            if (validator.isValidNumber(vehicle_type)) {
                int vtype = Integer.parseInt(vehicle_type);
                if (!(vtype >= 1 && vtype <= 3)) {
                    isNotValid = true;
                    System.out.print("PLEASE ENTER THE VALID CHOICE BETWEEN 1 TO 3 - ");
                }
            } else {
                isNotValid = true;
                System.out.print("PLEASE ENTER THE VALID CHOICE BETWEEN 1 TO 3 - ");
            }
        } while (isNotValid);

        System.out.print("ENTER THE PURCHASE COST OF THE VEHICLE - ");
        do {
            purchase_cost = sc.next();
            if (validator.isValidNumber(purchase_cost)) {
                double p_cost = Double.parseDouble(purchase_cost);
                if (!(p_cost >= 50000 && p_cost <= 100000)) {
                    isNotValid = true;
                    System.out.print("PLEASE ENTER THE VALID PURCHASE AMOUNT - ");
                }
            } else {
                isNotValid = true;
                System.out.print("PLEASE ENTER THE VALID PURCHASE AMOUNT - ");
            }
        } while (isNotValid);


        Vehicle v = new Vehicle(reg_no, brand, Integer.parseInt(max_velocity), Integer.parseInt(capacity), Integer.parseInt(vehicle_type), Double.parseDouble(purchase_cost), 0.0);
        vehicles.add(v);
        File file = new File(file_vehicles);
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(v.toString());
            fw.close();
        } catch (
                IOException e) {
            System.out.println(e.getMessage());
        }
         /*   }
        } else {
            System.out.println("INVALID REGISTRATION NUMBER");
        }*/
    }

    private boolean isVehiclePresent(String reg_no) {
        List<Vehicle> vehicles = getVehicles();
        boolean flag = false;
        for (Vehicle v : vehicles) {
            if (v.getRegistration_number().equals(reg_no)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public void getVehicleTax() {
        Scanner sc = new Scanner(System.in);
        List<Vehicle> vehicles = getVehicles();
        if (vehicles.size() > 0) {
            displayVehicles(vehicles);
            System.out.print("ENTER THE REGISTRATION NO OF VEHICLE TO CALCULATE THE TAX - ");
            String id = sc.next();

            if (isVehiclePresent(id)) {
                int vehicleIndex = getVehicleIndex(id);
                Vehicle v = vehicles.get(vehicleIndex);
                int vehicle_type = v.getVehicle_type();
                int max_velocity = v.getMax_velocity();
                int capacity = v.getCapacity();
                double purchase_cost = v.getPurchase_cost();
                double tax = v.getVehicle_tax();

                switch (vehicle_type) {
                    case 1:
                        tax = max_velocity + capacity + ((purchase_cost * 10) / 100);
                        break;
                    case 2:
                        tax = max_velocity + capacity + ((purchase_cost * 11) / 100);
                        break;
                    case 3:
                        tax = max_velocity + capacity + ((purchase_cost * 12) / 100);
                        break;
                }
                v.setVehicle_tax(tax);
                File file = new File(file_vehicles);
                FileWriter fw;
                try {
                    fw = new FileWriter(file);

                    for (Vehicle vehicle : vehicles) {
                        fw.write(vehicle.toString());
                    }
                    fw.close();
                    System.out.println("VEHICLE TAX FOR REGISTRATION NO - " + id + " IS " + tax);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("ERROR : REGISTRATION NO YOU ARE LOOKING FOR IS NOT PRESENT.");
            }
        } else {
            System.out.println("ERROR : NO VEHICLE PRESENT AT THIS MOMENT.");
        }
    }

    private int getVehicleIndex(String id) {
        int index = -1;
        List<Vehicle> vehicles = getVehicles();
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getRegistration_number().equals(id))
                index = i;
        }
        return index;
    }

    public void getAllVehicles() {
        displayVehicles(getVehicles());
    }

    private List<Vehicle> getVehicles() {
        File file = new File(file_vehicles);
        List<Vehicle> vehicles = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String[] lines = sc.nextLine().split(",");
                for (String line : lines) {
                    String[] content = line.split(":");
                    vehicles.add(new Vehicle(
                            content[0],
                            content[1],
                            Integer.parseInt(content[2]),
                            Integer.parseInt(content[3]),
                            Integer.parseInt(content[4]),
                            Double.parseDouble(content[5]),
                            Double.parseDouble(content[6]))
                    );
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return vehicles;
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles.size() > 0) {
            System.out.println("==============================================================================================================================");
            System.out.printf("%17s %17s %17s %17s %17s %17s %17s", "REGISTRATION NO.", "BRAND", "MAX. VELOCITY", "NO. OF SEATS", "VEHICLE TYPE", "PURCHASE COST", "VEHICLE TAX");
            System.out.println();
            System.out.println("==============================================================================================================================");
            for (Vehicle v : vehicles) {
                String vehicle_type = "";
                switch (v.getVehicle_type()) {
                    case 1:
                        vehicle_type = "PETROL";
                        break;
                    case 2:
                        vehicle_type = "DIESEL";
                        break;
                    case 3:
                        vehicle_type = "CNG/LPG";
                        break;
                }
                System.out.format("%17s %17s %17s %17s %17s %17.2f %17.2f", v.getRegistration_number(), v.getBrand(), v.getMax_velocity(), v.getCapacity(), vehicle_type, v.getPurchase_cost(), v.getVehicle_tax());
                System.out.println();
            }
            System.out.println("==============================================================================================================================");
        } else {
            System.out.println("ERROR : NO DATA PRESENT AT THIS MOMENT.");
        }
    }

    public void getTotalTax() {
        List<Property> properties = getProperties();
        List<Vehicle> vehicles = getVehicles();

        int total_no_of_properties = properties.size();
        int total_no_of_vehicles = vehicles.size();

        double property_sum = 0.0;
        double vehicle_sum = 0.0;

        for (Property p : properties) {
            property_sum += p.getProperty_tax();
        }
        for (Vehicle v : vehicles) {
            vehicle_sum += v.getVehicle_tax();
        }

        System.out.println("+ ------------------------------------------------------------- +");
        System.out.printf("%1s %7s %17s %17s %17s %1s", "|", "SR. NO.", "PARTICULAR", "QUANTITY", "TAX", "|");
        System.out.println();
        System.out.println("+ ------------------------------------------------------------- +");
        System.out.printf("%1s %7s %17s %17s %17.2f %1s", "|", "1", "PROPERTIES", total_no_of_properties, property_sum, "|");
        System.out.println();
        System.out.printf("%1s %7s %17s %17s %17.2f %1s", "|", "2", "VEHICLES", total_no_of_vehicles, vehicle_sum, "|");
        System.out.println();
        System.out.println("+ ------------------------------------------------------------- +");
        System.out.printf("%1s %7s %17s %17s %17.2f %1s", "|", "TOTAL", "----------", total_no_of_properties + total_no_of_vehicles, property_sum + vehicle_sum, "|");
        System.out.println();
        System.out.println("+ ------------------------------------------------------------- +");
    }

    public void resetPropertyData() {
        System.out.println("ARE YOU SURE? DO YOU WANT TO CLEAR ALL DATA? (Y: YES | N: NO) - ");
        Scanner scanner = new Scanner(System.in);
        char choice = scanner.next().charAt(0);

        switch (choice) {
            case 'Y':
            case 'y':
                if (authenticate()) {
                    resetProperty();
                } else {
                    System.out.println("INVALID | WRONG PASSWORD. OPERATION COULD NOT BE COMPLETED.");
                }
                break;
            case 'N':
            case 'n':
                System.out.println("OPERATION CANCELLED.");
                break;
            default:
                System.out.println("INVALID INPUT.");

        }
    }

    private void resetProperty() {
        try {
            FileWriter fileWriter = new FileWriter(file_properties);
            fileWriter.write("");
            fileWriter.close();
            System.out.println("PROPERTY DATA RESET SUCCESSFUL.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean authenticate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("PLEASE ENTER YOUR PASSWORD - ");
        String password = scanner.next();
        File f = new File(file_credentials);
        boolean authentication = false;
        try {
            Scanner sc = new Scanner(f);
            if (sc.hasNext()) {
                String pass = sc.nextLine().split(",")[1];
                if (pass.equals(password)) {
                    authentication = true;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return authentication;
    }

    public void resetVehicleData() {
        System.out.println("ARE YOU SURE? DO YOU WANT TO CLEAR ALL DATA? (Y: YES | N: NO) - ");
        Scanner scanner = new Scanner(System.in);
        char choice = scanner.next().charAt(0);

        switch (choice) {
            case 'Y':
            case 'y':
                if (authenticate()) {
                    resetVehicle();
                } else {
                    System.out.println("INVALID | WRONG PASSWORD. OPERATION COULD NOT BE COMPLETED.");
                }
                break;
            case 'N':
            case 'n':
                System.out.println("OPERATION CANCELLED.");
                break;
            default:
                System.out.println("INVALID INPUT.");

        }
    }

    private void resetVehicle() {
        try {
            FileWriter fileWriter = new FileWriter(file_vehicles);
            fileWriter.write("");
            fileWriter.close();
            System.out.println("VEHICLE DATA RESET SUCCESSFUL.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
