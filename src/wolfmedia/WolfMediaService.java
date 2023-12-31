package wolfmedia;

import java.sql.SQLException;
import java.util.Scanner;

import wolfmedia.service.InformationProcessing;
import wolfmedia.service.MaintainingMetadata;
import wolfmedia.service.MaintainingPayments;
import wolfmedia.service.Miscellaneous;
import wolfmedia.service.Reports;

public class WolfMediaService {

    private static void printMenu() {
        System.out.println("Main Menu");
        System.out.println("");
        System.out.println("");
        System.out.println("Please select an option:");
        System.out.println("");
        System.out.println("1. Information Processing");
        System.out.println("2. Maintaining Metadata");
        System.out.println("3. Maintaining Payments");
        System.out.println("4. Reports");
        System.out.println("5. Extras");
        System.out.println("");
        System.out.println("0. Quit");
        System.out.println("");
    }

    public static void closeService(Scanner scanner) {
        scanner.close();
        DBConnection.close();
        System.out.println("Goodbye!");
        System.exit(0);
    }

    public static void main(String[] args) throws SQLException {

        // Create a scanner for user input
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Welcome to Wolf Media Service!");
            System.out.println("");
            System.out.println("");

            // Display the dashboard
            while (true) {
                printMenu();
                // Get user input
                int option = scanner.nextInt();
                // Handle user input
                switch (option) {
                    case 1:
                        InformationProcessing ip = new InformationProcessing();
                        ip.process();
                        break;
                    case 2:
                        MaintainingMetadata mm = new MaintainingMetadata();
                        mm.process();
                        break;
                    case 3:
                        MaintainingPayments mp = new MaintainingPayments();
                        mp.process();
                        break;
                    case 4:
                        Reports r = new Reports();
                        r.process();
                        break;
                    case 5:
                        Miscellaneous m = new Miscellaneous();
                        m.process();
                        break;
                    case 0:
                        // Quit
                        closeService(scanner);
                        break;
                    default:
                        System.out.println("Invalid option.");
                        break;
                }

                scanner.nextLine();
                System.out.println("Press enter to continue...");
                scanner.nextLine();
            }

        } catch (Exception e) {
            closeService(scanner);
        }
    } 

    

}