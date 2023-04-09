package wolfmedia.service;

import java.util.Scanner;

public class Reports {
    private Scanner scanner;

    public Reports() {
        // Create a scanner for user input
        scanner = new Scanner(System.in);
    }

    private static void printMenu() {
        System.out.println("Main Menu > Reports");
        System.out.println("");
        System.out.println("");
        System.out.println("Please select an option:");
        System.out.println("");
        System.out.println("1. get Monthly Song PlayCount");
        System.out.println("2. get Monthly Album PlayCount");
        System.out.println("3. get Monthly Artist PlayCount");
        System.out.println("");
        System.out.println("4. get Host Payment");
        System.out.println("5. get Artist Payment");
        System.out.println("6. get Record Label Payment");
        System.out.println("");
        System.out.println("7. get Total Monthly Revenue");
        System.out.println("8. get Total Yearly Revenue");
        System.out.println("");
        System.out.println("9. get Songs By Artist");
        System.out.println("10. get Songs By Album");
        System.out.println("11. get Podcast Episodes By Podcast");
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
                case 4:
                    System.out.println("Not Impleented");
                    break;
                case 5:
                    System.out.println("Not Impleented");
                    break;
                case 6:
                    System.out.println("Not Impleented");
                    break;
                case 7:
                    System.out.println("Not Impleented");
                    break;
                case 8:
                    System.out.println("Not Impleented");
                    break;
                case 9:
                    System.out.println("Not Impleented");
                    break;
                case 10:
                    System.out.println("Not Impleented");
                    break;
                case 11:
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