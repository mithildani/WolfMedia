package wolfmedia;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class WolfMediaService {
    public static void main(String[] args) {

    	// Create a new instance of SongDao
        SongDao dao = new SongDao();

    	
    	 // Create a scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Display the dashboard
        while (true) {
            System.out.println("Welcome to Wolf Media Service!");
            System.out.println("Please select an option:");
            System.out.println("1. Display all songs");
            System.out.println("2. Display a song by Media ID");
            System.out.println("3. Update a Song by Media ID");
            System.out.println("4**. Delete song by MediaID");
            System.out.println("5. Quit");

            // Get user input
            int option = scanner.nextInt();

            // Handle user input
            switch (option) {
                case 1:
                    // Display all songs
                    List<Song> songs = dao.getAllSongs();
                    for (Song song : songs) {
                    	System.out.println(song.getTitle() + " by " + song.getLanguage() + " artist, released on " + song.getReleaseDate());
                    }
                    break;
                case 2:
                    // Display a song by Media ID
                    System.out.println("Enter Media ID:");
                    int mediaId = scanner.nextInt();
                    Song song = dao.getSongById(mediaId);
                    if (song != null) {
                    	System.out.println(song.getTitle() + " by " + song.getLanguage() + " artist, released on " + song.getReleaseDate());
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
//                	System.out.print("Enter MediaID: ");
//                    int mediaIdToDelete = scanner.nextInt();
//                    
//				try {
//					boolean deleteResult = dao.deleteSong(mediaIdToDelete);
//					if (deleteResult) {
//                        System.out.println("Song deleted successfully");
//                    } else {
//                        System.out.println("Failed to delete song");
//                    }
//                    break;
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
                    break;
                    
                case 5:
                    // Quit
                	System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);
				System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
            scanner.nextLine();
            // Wait for user input to continue
            System.out.println("Press enter to continue...");
            scanner.nextLine();
            scanner.nextLine();
        }
    }


}
