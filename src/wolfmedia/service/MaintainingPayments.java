package wolfmedia.service;

import java.util.Scanner;

public class MaintainingPayments {
    private Scanner scanner;

    public MaintainingPayments() {
        // Create a scanner for user input
        scanner = new Scanner(System.in);
    }

    private static void printMenu() {
        System.out.println("Main Menu > Maintaining Payments");
        System.out.println("");
        System.out.println("");
        System.out.println("Please select an option:");
        System.out.println("");
        System.out.println("1. create Song Payment");
        System.out.println("2. create Podcast Payment");
        System.out.println("3. create Subscriber Payment");

        System.out.println("");
        System.out.println("0. Return");
    }

    public void process() {
        while (true) {

            printMenu();

            // Get user input
            int option = scanner.nextInt();
            // Handle user input
            switch (option) {
                case 1:
                    System.out.println("Not Impleented");
                    break;
                case 2:
                    System.out.println("Not Impleented");
                    break;
                case 3:
                    System.out.println("Not Impleented");
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
            scanner.nextLine();
            // Wait for user input to continue
            System.out.println("Press enter to continue...");
            scanner.nextLine();
        }
    }
}