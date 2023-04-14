package wolfmedia.service;

import java.util.List;
import java.util.Scanner;

import wolfmedia.api.SongDao;
import wolfmedia.model.Song;

public class Miscellaneous {
    private Scanner scanner;

    public Miscellaneous() {
        // Create a scanner for user input
        scanner = new Scanner(System.in);
    }

    private static void printMenu() {
        System.out.println("Main Menu > Miscellaneous");
        System.out.println("");
        System.out.println("");
        System.out.println("Please select an option:");
        System.out.println("");
        System.out.println("1. get all Songs");
        System.out.println("2. get Songs by MediaID");
        System.out.println("3. is Royalty Paid");

        System.out.println("");
        System.out.println("0. Return");
    }

    public void process() {
        while (true) {

            printMenu();

            // Get user input
            int option = scanner.nextInt();
            SongDao dao = new SongDao();
            // Handle user input
            switch (option) {
                case 1:
                    // Display all songs

                    List<Song> songs = dao.getAllSongs();
                    for (Song song : songs) {
                        System.out.println(song.getTitle() + " by " + song.getLanguage() + " artist, released on "
                                + song.getReleaseDate());
                    }
                    break;
                case 2:
                    // Display a song by Media ID
                    System.out.println("Enter Media ID:");
                    int mediaId = scanner.nextInt();
                    Song song = dao.getSongById(mediaId);
                    if (song != null) {
                        System.out.println(song.getTitle() + " by " + song.getLanguage() + " artist, released on "
                                + song.getReleaseDate());
                    } else {
                        System.out.println("Song with Media ID " + mediaId + " not found.");
                    }
                    break;
                case 3:
                    System.out.println("Not Implemented");
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