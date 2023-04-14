package wolfmedia.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import wolfmedia.api.PodcastEpisodeDao;
import wolfmedia.api.SongDao;
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
        System.out.println("1. enter/update Song PlayCount");
        System.out.println("");
        System.out.println("2. enter Monthly Artist Listener Count");
        System.out.println("4. update Monthly Artist Listener Count");
        System.out.println("");
        System.out.println("5. enter Podcast Subscriber");
        System.out.println("6. update Podcast Subscriber");
        System.out.println("");
        System.out.println("7. enter/update Podcast Rating Count");
        
        System.out.println("");
        System.out.println("9. enter/update Podcast Episode Listener Count");
        
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
            PodcastEpisodeDao podcastEpisodeDao = null;
            switch (option) {
                case 1:
                	System.out.print("Enter the user id: ");
                    int userId = scanner.nextInt();
                    System.out.print("Enter the media id: ");
                    int mediaId = scanner.nextInt();
                    try {
                        songDao = new SongDao();
                        songDao.simulateSongPlayback(userId, mediaId);
                        
                        System.out.println("Song playback simulated successfully!");
                    } catch (SQLException e) {
                        System.out.println("Error simulating song playback: " + e.getMessage());
                    }
                    
                    
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
                	System.out.print("Enter the user id: ");
                	int uId = scanner.nextInt();
                	System.out.print("Enter the podcast episode id: ");
                	int episodeId = scanner.nextInt();

                	try {
                		podcastEpisodeDao = new PodcastEpisodeDao();
                		podcastEpisodeDao.subscribeToPodcast(uId, episodeId);
                	    
                		System.out.println("Podcast subscribed successfully!");
                	} catch (SQLException e) {
                	    System.out.println("Error simulating podcast episode playback: " + e.getMessage());
                	}

                    break;
                case 6:
                	System.out.print("Enter the user id: ");
                    int usrId = scanner.nextInt();
                    System.out.print("Enter the old podcast id: ");
                    int oId = scanner.nextInt();
                    System.out.print("Enter the new podcast id: ");
                    int nId = scanner.nextInt();
                    try {
                    	podcastEpisodeDao = new PodcastEpisodeDao();
                		
                        podcastEpisodeDao.updatePodcastSubscription(usrId, nId, oId);
              
                        break;
                    } catch (SQLException e) {
                        System.out.println("Error updating Supbscription: " + e.getMessage());
                    }
                    break;
                case 7:
                	System.out.print("Enter the user id: ");
                	int usId = scanner.nextInt();
                	System.out.print("Enter the podcast id: ");
                	int podcastId = scanner.nextInt();
                	System.out.print("Enter the rating (1-5): ");
                	int rating = scanner.nextInt();

                	try {
                		podcastEpisodeDao = new PodcastEpisodeDao();
                		podcastEpisodeDao.ratePodcast(usId, podcastId, rating);
                	    
                	    System.out.println("Podcast rating entered/updated successfully!");
                	} catch (SQLException e) {
                	    System.out.println("Error entering/updating podcast rating: " + e.getMessage());
                	}

                    break;
                case 8:
                    System.out.println("Not Impleented");
                    break;
                case 9:
                	System.out.print("Enter the user id: ");
                	int usersId = scanner.nextInt();
                	System.out.print("Enter the podcast id: ");
                	int podId = scanner.nextInt();
                	

                	try {
                		podcastEpisodeDao = new PodcastEpisodeDao();
                		podcastEpisodeDao.insertPodcastEpisodeStream(podId, usersId);
                	    
                	    System.out.println("Podcast rating entered/updated successfully!");
                	} catch (SQLException e) {
                	    System.out.println("Error entering/updating podcast rating: " + e.getMessage());
                	}
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