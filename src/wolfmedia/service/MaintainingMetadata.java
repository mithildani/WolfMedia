package wolfmedia.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import wolfmedia.api.InformationProcessing.SongDao;
import wolfmedia.model.Song;

public class MaintainingMetadata {
    private Scanner scanner;

    public MaintainingMetadata() {
        // Create a scanner for user input
        scanner = new Scanner(System.in);
    }

    private static void printMenu() {
        System.out.println("Main Menu > Maintaining Metadata");
        System.out.println("");
        System.out.println("");
        System.out.println("Please select an option:");
        System.out.println("");
        System.out.println("1. enter Song PlayCount");
        System.out.println("2. update Song PlayCount");
        System.out.println("");
        System.out.println("3. enter Monthly Artist Listener Count");
        System.out.println("4. update Monthly Artist Listener Count");
        System.out.println("");
        System.out.println("5. enter Podcast Subscriber");
        System.out.println("6. update Podcast Subscriber");
        System.out.println("");
        System.out.println("7. enter Podcast Rating Count");
        System.out.println("8. update Podcast Rating Count");
        System.out.println("");
        System.out.println("9. enter Podcast Episode Listener Count");
        System.out.println("10. update Podcast Episode Listener Count");
        System.out.println("");
        System.out.println("11. get Song by Artist");
        System.out.println("12. get Song by Album");
        System.out.println("13. get Podcast Episode");
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
                	System.out.print("Enter the artist id: ");
                	int aID= scanner.nextInt();

                	try {
                	     songDao = new SongDao();
                	    List<Song> songs = songDao.getSongsByArtist(aID);

                	    if (songs.isEmpty()) {
                	        System.out.println("No songs found for artist " + aID);
                	    } else {
                	        System.out.println("Songs for artist " + aID + ":");
                	        for (Song song : songs) {
                	            System.out.println(song.getTitle());
                	        }
                	    }
                	} catch (SQLException e) {
                	    System.out.println("Error retrieving songs by artist: " + e.getMessage());
                	}

                    break;
                case 12:
                	System.out.print("Enter the album id: ");
                	int albID= scanner.nextInt();

                	try {
                	     songDao = new SongDao();
                	    List<Song> songs = songDao.getSongsByAlbum(albID);

                	    if (songs.isEmpty()) {
                	        System.out.println("No songs found for album " + albID);
                	    } else {
                	        System.out.println("Songs for album " + albID + ":");
                	        for (Song song : songs) {
                	            System.out.println(song.getTitle());
                	        }
                	    }
                	} catch (SQLException e) {
                	    System.out.println("Error retrieving songs by artist: " + e.getMessage());
                	}

                    break;
                case 13:
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