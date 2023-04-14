package wolfmedia.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import wolfmedia.api.PaymentDao;
import wolfmedia.api.PodcastEpisodeDao;
import wolfmedia.api.SongDao;

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
            SongDao songDao = null;
            PaymentDao paymentDao = null;
            PodcastEpisodeDao podcastEpisodeDao = null;
            switch (option) {
                case 1:
                	System.out.print("Enter the media id: ");
                	int mediaId = scanner.nextInt();

                	System.out.print("Enter the month (1-12): ");
                	int sMonth = scanner.nextInt();

                	try {
                	    songDao = new SongDao();
                	    
                	    int songListeners = songDao.getMonthlySongPlayCount(mediaId, sMonth);
                	    
                	    System.out.println("Monthly listeners for song " + mediaId + " in month " + sMonth + ": " + songListeners);
                	} catch (SQLException e) {
                	    System.out.println("Error getting monthly listeners: " + e.getMessage());
                	}
                    break;
                case 2:
                	System.out.print("Enter the album id: ");
                	int albumId = scanner.nextInt();

                	System.out.print("Enter the month (1-12): ");
                	int amonth = scanner.nextInt();

                	try {
                	    songDao = new SongDao();
                	    int albumListeners = songDao.getMonthlyListenersForArtist(albumId, amonth);
                	    
                	    System.out.println("Monthly listeners for album " + albumId + " in month " + amonth + ": " + albumListeners);
                	} catch (SQLException e) {
                	    System.out.println("Error getting monthly listeners: " + e.getMessage());
                	}
                    break;
                case 3:
                	System.out.print("Enter the artist id: ");
                	int artistId = scanner.nextInt();

                	System.out.print("Enter the month (1-12): ");
                	int month = scanner.nextInt();

                	try {
                	    songDao = new SongDao();
                	    int monthlyListeners = songDao.getMonthlyListenersForArtist(artistId, month);
                	    
                	    System.out.println("Monthly listeners for artist " + artistId + " in month " + month + ": " + monthlyListeners);
                	} catch (SQLException e) {
                	    System.out.println("Error getting monthly listeners: " + e.getMessage());
                	}

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
                	System.out.print("Enter the year(YYYY): ");
                	int year = scanner.nextInt();

                	System.out.print("Enter the month (1-12): ");
                	int pmonth = scanner.nextInt();

                	try {
                	    paymentDao = new PaymentDao();
                	    double monthlyPayemnt = paymentDao.getTotalMonthlyPayment(year, pmonth);
                	    
                	    System.out.println("Total revenue in month " + pmonth + " for year " + year + " is " + monthlyPayemnt);
                	} catch (SQLException e) {
                	    System.out.println("Error getting monthly revenue: " + e.getMessage());
                	}
                    break;
                case 8:
                	System.out.print("Enter the year(YYYY): ");
                	int pyear = scanner.nextInt();

                	
                	try {
                	    paymentDao = new PaymentDao();
                	    double yearlyRevenue = paymentDao.getTotalYearlyRevenue(pyear);
                	    
                	    System.out.println("Total yearly revenue in year " + pyear + " is " + yearlyRevenue);
                	} catch (SQLException e) {
                	    System.out.println("Error getting yearly revenue: " + e.getMessage());
                	}
                    break;
                case 9:
                	System.out.print("Enter the artist id: ");
                	int arId = scanner.nextInt();
                	try {
                		songDao = new SongDao();
                		List<String> titles = songDao.getSongTitlesByArtist(arId);
                		System.out.println("Following are the song by artist with id: " + arId);
                        for (String title : titles) {
                            System.out.println(title);
                        }
                	}catch (SQLException e) {
                	    System.out.println("Error getting song by artist: " + e.getMessage());
                	}
                    
              
                    break;
                case 10:
                	System.out.print("Enter the album id: ");
                	int albId = scanner.nextInt();
                	try {
                		songDao = new SongDao();
                		List<String> titles = songDao.getSongByAlbum(albId);
                		System.out.println("Following are the song with album id: " + albId);
                        for (String title : titles) {
                            System.out.println(title);
                        }
                	}catch (SQLException e) {
                	    System.out.println("Error getting song by album: " + e.getMessage());
                	}
                    break;
                case 11:
                	System.out.print("Enter the podcast  id: ");
                	int podId = scanner.nextInt();
                	try {
                		podcastEpisodeDao = new PodcastEpisodeDao();
                		List<String> titles = podcastEpisodeDao.getPodcastEpisodeTitles(podId);
                		System.out.println("Following are the episodes in podcast with id: " + podId);
                        for (String title : titles) {
                            System.out.println(title);
                        }
                	}catch (SQLException e) {
                	    System.out.println("Error getting podcast episode titles by podcast id: " + e.getMessage());
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