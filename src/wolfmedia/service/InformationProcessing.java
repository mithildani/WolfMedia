package wolfmedia.service;

import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import wolfmedia.api.InformationProcessing.ArtistDao;
import wolfmedia.api.InformationProcessing.PodcastHostDao;
import wolfmedia.api.InformationProcessing.SongDao;
import wolfmedia.model.Artist;
import wolfmedia.model.PodcastHost;
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

        System.out.println("1. enter Song");
        System.out.println("2. update Song");
        System.out.println("3. delete Song");
        System.out.println();
        System.out.println("4. enter Artist");
        System.out.println("5. update Artist");
        System.out.println("6. delete Artist");
        System.out.println();
        System.out.println("7. enter Podcast Host");
        System.out.println("8. update Podcast Host");
        System.out.println("9. delete Podcast Host");
        System.out.println();
        System.out.println("10. enter Podcast Episode");
        System.out.println("11. update Podcast Episode");
        System.out.println("12. delete Podcast Episode");
        System.out.println();
        System.out.println("13. assign Song To Album");
        System.out.println("14. assign Artist To Album");
        System.out.println("15. assign Artist To RecordLabel");
        System.out.println("16. assign Podcast Episode To Podcast");
        System.out.println("17. assign Host To Podcast");

        System.out.println("0. Return");
    }

    public void process() {
        while (true) {

            printMenu();

            // Get user input
            int option = scanner.nextInt();
            
            // Initialize all the Data Access Objects
            SongDao songDao = null;
            ArtistDao artistDao = null;
            PodcastHostDao hostDao =null;
            
            // Handle user input
            switch (option) {
                case 1:
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
                    	songDao = new SongDao();
                        boolean insertedSong = songDao.insertSong(newSong);
                        if (insertedSong) {
                            System.out.println("Song added successfully with Media ID " + songMediaId);
                        } else {
                            System.out.println("Song insertion failed");
                        }
                    } catch (SQLException e) {
                        System.out.println("Error adding song: " + e.getMessage());
                    }
                    break;
                case 2:
                	
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
                    	songDao = new SongDao();
                        int rowsUpdated = songDao.updateSong(updatedMediaId, title, country, language);
                        if (rowsUpdated == 1) {
                            System.out.println("Song updated successfully.");
                        } else {
                            System.out.println("Song not found.");
                        }
                        break;
                    } catch (SQLException e) {
                        System.out.println("Error updating song: " + e.getMessage());
                    }
                    break;
                case 3:

                    // System.out.print("Enter MediaID: ");
                    // int mediaIdToDelete = scanner.nextInt();
                    //
                    // try {
                	//songDao = new SongDao();
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
                    System.out.println("Not Impleented");
                    break; 
                case 4:
                	
                	// Add a new artist
                	System.out.print("Enter Artist ID: ");
                    int artistId = scanner.nextInt();
                    scanner.nextLine();
                	System.out.print("Enter artist name: ");
                	String artistName = scanner.nextLine();
                	
                	System.out.print("Enter status: ");
                	String artistStatus = scanner.nextLine();
                
                	System.out.print("Enter type: ");
                	String artistType = scanner.nextLine();
                	
                	System.out.print("Enter country: ");
                	String artistCountry = scanner.nextLine();
                	
                	System.out.print("Enter primary genre: ");
                	String artistPrimaryGenre = scanner.nextLine();
                	
                	Artist newArtist = new Artist(artistId, artistName, artistStatus, artistType, artistCountry, artistPrimaryGenre);
                	try {
                		artistDao = new ArtistDao();
                	    boolean insertedArtist = artistDao.insertArtist(newArtist);
                	    if (insertedArtist) {
                	        System.out.println("Artist added successfully with ID " + newArtist.getArtistID());
                	    } else {
                	        System.out.println("Artist insertion failed");
                	    }
                	} catch (SQLException e) {
                	    System.out.println("Error adding artist: " + e.getMessage());
                	}
                   
                    break;
                case 5:
                	//update an artist
                	System.out.print("Enter artist id of artist to update: ");
                    int updateArtistId = scanner.nextInt();
                    scanner.nextLine(); // consume newline character
                    System.out.print("Enter new name: ");
                    String updatedArtistName = scanner.nextLine();
                    System.out.print("Enter new status: ");
                    String updatedArtistStatus = scanner.nextLine();
                    System.out.print("Enter new type: ");
                    String updatedArtistType = scanner.nextLine();
                    System.out.print("Enter new country: ");
                    String updatedArtistCountry = scanner.nextLine();
                    System.out.print("Enter new primary genre: ");
                    String updatedArtistPrimaryGenre = scanner.nextLine();
                    try {
                    	artistDao = new ArtistDao();
                        int rowsUpdated = artistDao.updateArtist(updateArtistId, updatedArtistName, updatedArtistStatus, updatedArtistType, updatedArtistCountry, updatedArtistPrimaryGenre);
                        if (rowsUpdated == 1) {
                            System.out.println("Artist updated successfully.");
                        } else {
                            System.out.println("Artist not found.");
                        }
                        break;
                    } catch (SQLException e) {
                        System.out.println("Error updating Artist: " + e.getMessage());
                    }
                    
                    break;
                case 6:
                    System.out.println("Not Impleented");
                    break;
                case 7:
                	// Add a new host
                	System.out.print("Enter Host ID: ");
                    int hostId = scanner.nextInt();
                    scanner.nextLine();
                	System.out.print("Enter host's first name: ");
                	String fName = scanner.nextLine();
                	
                	System.out.print("Enter host's last name: ");
                	String lName = scanner.nextLine();
                
                	System.out.print("Enter city: ");
                	String city = scanner.nextLine();
                	
                	System.out.print("Enter email: ");
                	String email = scanner.nextLine();
                	
                	System.out.print("Enter phone: ");
                	String phone = scanner.nextLine();
                	
                	PodcastHost newHost = new PodcastHost(hostId, fName, lName, city, email, phone);
                	try {
                		hostDao = new PodcastHostDao();
                	    boolean insertedHost = hostDao.insertPodcastHost(newHost);
                	    if (insertedHost) {
                	        System.out.println("Host added successfully with ID " + newHost.getHostID());
                	    } else {
                	        System.out.println("Host insertion failed");
                	    }
                	} catch (SQLException e) {
                	    System.out.println("Error adding artist: " + e.getMessage());
                	}
                   
                    
                    break;
                case 8:
              
                	//update a PodcastHost
                	System.out.print("Enter HostID of the host to update: ");
                	int updateHostID = scanner.nextInt();
                	scanner.nextLine(); // consume newline character
                	System.out.print("Enter new first name: ");
                	String updatedFirstName = scanner.nextLine();
                	System.out.print("Enter new last name: ");
                	String updatedLastName = scanner.nextLine();
                	System.out.print("Enter new city: ");
                	String updatedCity = scanner.nextLine();
                	System.out.print("Enter new email: ");
                	String updatedEmail = scanner.nextLine();
                	System.out.print("Enter new phone number: ");
                	String updatedPhoneNumber = scanner.nextLine();
                	try {
                	hostDao = new PodcastHostDao();
                	int rowsUpdated = hostDao.updatePodcastHost(updateHostID, updatedFirstName, updatedLastName, updatedCity, updatedEmail, updatedPhoneNumber);
                	if (rowsUpdated == 1) {
                	System.out.println("Podcast host updated successfully.");
                	} else {
                	System.out.println("Podcast host not found.");
                	}
                	break;
                	} catch (SQLException e) {
                	System.out.println("Error updating Podcast Host: " + e.getMessage());
                	}
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
                case 12:
                    System.out.println("Not Impleented");
                    break;    
                case 13:
                	//song to album
                	System.out.print("Enter the ID of the song to assign: ");
                	int songMediaID = scanner.nextInt();
                	scanner.nextLine(); // consume newline character
                	System.out.print("Enter the ID of the album to assign the song to: ");
                	int albumId = scanner.nextInt();
                	scanner.nextLine(); // consume newline character
                    try {
                    	songDao = new SongDao();
                    	int rowsUpdated = songDao.assignSongToAlbum(songMediaID, albumId);
                    	if (rowsUpdated == 1) {
                    		System.out.println("Song assigned to album successfully.");
                    		} else {
                    		System.out.println("Song or album not found.");
                    		}
                    }catch (SQLException e) {
                    	System.out.println("Error assigning Song to Album: " + e.getMessage());
                    }
                    break;
                case 14:
                	//artist to album
                	
                	System.out.print("Enter artist ID: ");
                	int artistID = scanner.nextInt();
                	scanner.nextLine(); 
                	System.out.print("Enter album ID: ");
                	int albumID = scanner.nextInt();
                	scanner.nextLine(); 
                	try {
                		artistDao = new ArtistDao();
                		int rowsUpdated = artistDao.assignArtistToAlbum(artistID, albumID);

                    	if (rowsUpdated == 1) {
                    	    System.out.println("Artist assigned to album successfully.");
                    	} else {
                    	    System.out.println("Artist or album not found.");
                    	}
                	} catch (SQLException e) {
                		System.out.println("Error assigning Artist to Album: " + e.getMessage());
                	}

                    break;
                case 15:
                    // assign artist to record label
                	
                	System.out.print("Enter the artist ID: ");
                	int aId = scanner.nextInt();
                	scanner.nextLine(); 
                	
                	System.out.print("Enter the label name: ");
                	String labelName = scanner.next();
                	scanner.nextLine(); 
                	
                	try {
                		artistDao = new ArtistDao();
                		int rowsUpdated = artistDao.assignArtistToRecordLabel(aId, labelName);

                		if (rowsUpdated == 1) {
                		    System.out.println("Artist assigned to label successfully.");
                		} else {
                		    System.out.println("Artist or label not found.");
                		}
                	}catch (SQLException e) {
                		System.out.println("Error assigning Artist to Record Label: " + e.getMessage());
                	}
                    break;
                case 16:
                    System.out.println("Not Impleented");
                    break;
                case 17:
                	// assign podcast host to podcast
                	System.out.print("Enter the host ID: ");
                	int hId = scanner.nextInt();
                	scanner.nextLine();

                	System.out.print("Enter the podcast ID: ");
                	int pId = scanner.nextInt();
                	scanner.nextLine();

                	try {
                	    hostDao = new PodcastHostDao();
                	    int rowsUpdated = hostDao.assignHostToPodcast(hId, pId);

                	    if (rowsUpdated == 1) {
                	        System.out.println("Podcast host assigned to podcast successfully.");
                	    } else {
                	        System.out.println("Podcast host or podcast not found.");
                	    }
                	} catch (SQLException e) {
                	    System.out.println("Error assigning podcast host to podcast: " + e.getMessage());
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
