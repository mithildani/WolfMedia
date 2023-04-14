package wolfmedia.service;

import java.sql.SQLException;
import java.util.Scanner;

import wolfmedia.api.PaymentDao;

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
            int year;
            int month;
            boolean createdpayment;
            PaymentDao paymentdao;
            // Handle user input
            switch (option) {
                case 1:
                    System.out.println("Enter Media Id: ");
                    int media_id = scanner.nextInt();
                    System.out.println("Enter Year: ");
                    year = scanner.nextInt();
                    System.out.println("Enter Month: ");
                    month = scanner.nextInt();
                    try {
                        paymentdao = new PaymentDao();
                        createdpayment = paymentdao.createSongPayment(media_id, year, month);
                        if (createdpayment) {
                            System.out.println("Payment created");
                        } else {
                            System.out.println("Payment creation failed");
                        }
                    } catch (SQLException e) {
                        System.out.println("Payment creation failed: " + e.getMessage());
                    }

                    break;
                case 2:
                    System.out.println("Enter Host Id: ");
                    int host_id = scanner.nextInt();
                    System.out.println("Enter Year: ");
                    year = scanner.nextInt();
                    System.out.println("Enter Month: ");
                    month = scanner.nextInt();
                    try {
                        paymentdao = new PaymentDao();
                        createdpayment = paymentdao.createPodcastPayment(host_id, year, month);
                        if (createdpayment) {
                            System.out.println("Payment created");
                        } else {
                            System.out.println("Payment creation failed");
                        }
                    } catch (SQLException e) {
                        System.out.println("Payment creation failed: " + e.getMessage());
                    }

                    break;
                case 3:
                    System.out.println("Enter User Id: ");
                    int user_id = scanner.nextInt();
                    System.out.println("Enter Year: ");
                    year = scanner.nextInt();
                    System.out.println("Enter Month: ");
                    month = scanner.nextInt();
                    try {
                        paymentdao = new PaymentDao();
                        createdpayment = paymentdao.createSubscriberPayment(user_id, year, month);
                        if (createdpayment) {
                            System.out.println("Payment created");
                        } else {
                            System.out.println("Payment creation failed");
                        }
                    } catch (SQLException e) {
                        System.out.println("Payment creation failed: " + e.getMessage());
                    }
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