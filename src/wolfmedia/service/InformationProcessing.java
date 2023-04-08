package wolfmedia.service;

import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import wolfmedia.api.InformationProcessing.SongDao;
import wolfmedia.model.Song;

public class InformationProcessing {
    private Scanner scanner;

    public InformationProcessing() {
        // Create a scanner for user input
        scanner = new Scanner(System.in);
    }

    private static void printMenu() {
        System.out.println("Main Menu > Information Processing");
        System.out.println("");
        System.out.println("");
        System.out.println("Please select an option:");
        System.out.println("");
        System.out.println("1. Display all songs");
        System.out.println("2. Display a song by Media ID");
        System.out.println("3. Update a Song by Media ID");
        System.out.println("4**. Delete song by MediaID");
        System.out.println("5. Enter a new song");
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
                    System.out.print("Enter media id of song to update: ");
                    int updatedMediaId = scanner.nextInt();
                    scanner.nextLine(); // consume newline character
                    System.out.print("Enter new title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter new country: ");
                    String country = scanner.nextLine();
                    System.out.print("Enter new language: ");
                    String language = scanner.nextLine();
                    try {
                        int rowsUpdated = dao.updateSong(updatedMediaId, title, country, language);
                        if (rowsUpdated == 1) {
                            System.out.println("Song updated successfully.");

                        } else {
                            System.out.println("Song not found.");

                        }
                        break;
                    } catch (SQLException e) {
                        System.out.println("Error updating song: " + e.getMessage());
                    }

                case 4:
                    // System.out.print("Enter MediaID: ");
                    // int mediaIdToDelete = scanner.nextInt();
                    //
                    // try {
                    // boolean deleteResult = dao.deleteSong(mediaIdToDelete);
                    // if (deleteResult) {
                    // System.out.println("Song deleted successfully");
                    // } else {
                    // System.out.println("Failed to delete song");
                    // }
                    // break;
                    // } catch (SQLException e) {
                    // // TODO Auto-generated catch block
                    // e.printStackTrace();
                    // }
                    break;
                case 5:

                    // Add a new song
                    System.out.print("Enter media ID: ");
                    int songMediaId = scanner.nextInt();
                    scanner.nextLine(); // consume newline character
                    System.out.print("Enter track number: ");
                    int songTrackNumber = scanner.nextInt();
                    scanner.nextLine(); // consume newline character
                    System.out.print("Enter title: ");
                    String songTitle = scanner.nextLine();
                    System.out.print("Enter country: ");
                    String songCountry = scanner.nextLine();
                    System.out.print("Enter language: ");
                    String songLanguage = scanner.nextLine();

                    System.out.print("Enter duration (hh:mm:ss): ");
                    Time duration = null;
                    if (scanner.hasNextLine()) {
                        String durationStr = scanner.nextLine();

                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                            java.util.Date date = sdf.parse(durationStr);
                            duration = new Time(date.getTime());
                        } catch (Exception e) {
                            System.out.println("Invalid time format. Please try again.");
                            return;
                        }
                    } else {
                        System.out.println("No input available");
                        return;
                    }

                    System.out.print("Enter royalty rate (e.g. 12.50): ");
                    double songRoyaltyRate = scanner.nextDouble();
                    System.out.print("Enter album ID: ");
                    int songAlbumID = scanner.nextInt();

                    Song newSong = new Song(songMediaId, duration, songTitle, songRoyaltyRate, songCountry,
                            songLanguage, songAlbumID, songTrackNumber);
                    try {
                        boolean insertedSong = dao.insertSong(newSong);
                        if (insertedSong) {
                            System.out.println("Song added successfully with Media ID " + songMediaId);
                        } else {
                            System.out.println("Song insertion failed");
                        }
                    } catch (SQLException e) {
                        System.out.println("Error adding song: " + e.getMessage());
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
