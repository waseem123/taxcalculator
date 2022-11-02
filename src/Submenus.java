import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Submenus {
    private static final String CREDENTIALS = "src/credentials.txt";

    public boolean login() {
        boolean status = false;
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("USERNAME - ");
            String username = sc.next();
            System.out.print("PASSWORD - ");
            String password = sc.next();
            File file = new File(CREDENTIALS);
            Scanner reader = new Scanner(file);
            if (reader.hasNext()) {
                String[] credentials = reader.nextLine().split(",");
                if (credentials[0].equals(username) && credentials[1].equals(password)) {
                    status = true;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return status;
    }

    public void getPropertyMenu() {
        while (true) {
            System.out.println("1. ADD PROPERTY DETAILS");
            System.out.println("2. CALCULATE PROPERTY TAX");
            System.out.println("3. DISPLAY ALL PROPERTIES");
            System.out.println("4. CLEAR DATA");
            System.out.println("5. BACK TO MAIN MENU");
            Scanner sc = new Scanner(System.in);
            Operations o = new Operations();
            switch (sc.nextInt()) {
                case 1:
                    o.addProperty();
                    break;
                case 2:
                    o.getPropertyTax();
                    break;
                case 3:
                    o.getAllProperties();
                    break;
                case 4:
                    o.resetPropertyData();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("ERROR : INVALID CHOICE. PLEASE SELECT THE OPTION BETWEEN 1 TO 4.");
            }
        }
    }

    public void getVehicleMenu() {
        while (true) {
            System.out.println("1. ADD VEHICLE DETAILS");
            System.out.println("2. CALCULATE VEHICLE TAX");
            System.out.println("3. DISPLAY ALL VEHICLES");
            System.out.println("4. CLEAR DATA");
            System.out.println("5. BACK TO MAIN MENU");
            Scanner sc = new Scanner(System.in);
            Operations o = new Operations();
            switch (sc.nextInt()) {
                case 1:
                    o.addVehicle();
                    break;
                case 2:
                    o.getVehicleTax();
                    break;
                case 3:
                    o.getAllVehicles();
                    break;
                case 4:
                    o.resetVehicleData();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("ERROR : INVALID CHOICE. PLEASE SELECT THE OPTION BETWEEN 1 TO 4.");
            }
        }

    }
}
