import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("+----------------------------------+");
        System.out.println("|  WELCOME TO TAX CALCULATION APP  |");
        System.out.println("+----------------------------------+");

        System.out.println("PLEASE LOGIN TO CONTINUE - ");
        Submenus o = new Submenus();
        try {
            if (o.login()) {
                do {
                    System.out.println("1. PROPERTY TAX");
                    System.out.println("2. VEHICLE TAX");
                    System.out.println("3. TOTAL");
                    System.out.println("4. EXIT");
                    try {
                        switch (sc.nextInt()) {
                            case 1:
                                o.getPropertyMenu();
                                break;
                            case 2:
                                o.getVehicleMenu();
                                break;
                            case 3:
                                Operations op = new Operations();
                                op.getTotalTax();
                                break;
                            case 4:
                                System.out.println("THANKS VISIT AGAIN.");
                                return;
                            default:
                                System.out.println("ERROR : INVALID CHOICE. PLEASE SELECT THE OPTION BETWEEN 1 TO 4.");
                        }
                    } catch (Exception e) {
                        System.out.println("ERROR : INVALID INPUT.");
                        break;
                    }
                } while (true);
            } else {
                System.out.println("ERROR : LOGIN FAILURE! INVALID CREDENTIALS.");
            }
        } catch (Exception e) {
            System.out.println("ERROR : " + e.getMessage());
        }
    }
}