package wolfmedia.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.text.ParseException;

import wolfmedia.DBConnection;
import wolfmedia.api.InformationProcessing.ArtistDao;
import wolfmedia.api.InformationProcessing.PodcastEpisodeDao;
import wolfmedia.api.InformationProcessing.PodcastHostDao;
import wolfmedia.api.InformationProcessing.SongDao;
import wolfmedia.model.Artist;
import wolfmedia.model.PodcastHost;
import wolfmedia.model.PodcastEpisode;
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

    public void process() throws SQLException {
        while (true) {

            printMenu();

            // Get user input
            int option = scanner.nextInt();
            
            // Initialize all the Data Access Objects
            SongDao songDao = null;
            ArtistDao artistDao = null;
            PodcastHostDao hostDao =null;
            PodcastEpisodeDao peDao = null;
            
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
                	// Add PodCast episode
                	System.out.println("Enter the Media ID : ");
                	int peMediaId = scanner.nextInt();
                	scanner.nextLine();
                	System.out.println("Enter the Title : ");
                	String petitle = scanner.nextLine();
                	
                	System.out.println("Enter duration (hh:mm:ss): ");
                    Time peduration = null;
                    if (scanner.hasNextLine()) {
                        String pedurationStr = scanner.nextLine();

                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                            java.util.Date date = sdf.parse(pedurationStr);
                            peduration = new Time(date.getTime());
                        } catch (Exception e) {
                            System.out.println("Invalid time format. Please try again.");
                            return;
                        }
                    } else {
                        System.out.println("No input available");
                        return;
                    }
                	
                	System.out.println("Enter Ad Rate : ");
                	double peadrate = scanner.nextDouble();
                	
                	System.out.println("Enter Flat Fee : ");
                	double peflatfee = scanner.nextDouble();
                	
                	System.out.println("Enter Podcast ID : ");
                	int pepodcastid = scanner.nextInt();
                	scanner.nextLine();
                	
                	System.out.println("Enter Release Date (yyyy-mm-dd): ");
                	String pereleasedateStr = scanner.next();
                    if (scanner.hasNextLine()) {
                    	                        	
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                        java.util.Date pereleaseDate = null;
                        try {
                        	System.out.println(pereleaseDate);
                        	System.out.println(pereleasedateStr);
                            pereleaseDate = dateFormat.parse(pereleasedateStr);
                        } catch (Exception e) {
                            System.out.println("Invalid Date format. Please try again.");
                            return;
                        }
                    } else {
                        System.out.println("No input available");
                        return;
                    }
                	
				String pereleasedate = null;				
				String sql = "INSERT INTO Media (MediaID) " + "VALUES (?)";
				Connection connection = DBConnection.getConnection();
				try (PreparedStatement stmt = connection.prepareStatement(sql)) {
					stmt.setInt(1, peMediaId);
					stmt.executeQuery();
		        } catch (SQLException e) {
		            throw e;
		        }
					PodcastEpisode pe = new PodcastEpisode(peMediaId, pepodcastid, petitle, peduration, peadrate, peflatfee, pereleasedateStr);
                	try {
                		peDao = new PodcastEpisodeDao();
                		boolean insertedPodcastEpisode = peDao.insertPodcastEpisode(pe);
                		if (insertedPodcastEpisode) {
                			System.out.println("Podcast Episode inserted sucessfully");
                		}
                		else {
                			System.out.println("PodcastEpisode insertion failed");
                		}
                	}
                		catch (SQLException e) {
                    	    System.out.println("Error adding Podcast Episode: " + e.getMessage());
                		}
            
                    break;
                case 11:
                	// Update Podcast Episode
                	System.out.println("Enter MediaId of Podcast Episode to update : ");
                	int updatepemediaid = scanner.nextInt();
                	System.out.println("Enter new title : ");
                	String updatepetitle = scanner.next();
                	System.out.println("Enter new Ad Rate : ");
                	double updatepeadrate = scanner.nextDouble();
                	System.out.println("Enter new Flat Fee : ");
                	double updatepeflatfee = scanner.nextDouble();
                	System.out.println("Enter duration (hh:mm:ss): ");
                	scanner.nextLine();
                    Time updatepeduration = null;
                    if (scanner.hasNextLine()) {
                        String updatepedurationStr = scanner.nextLine();

                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                            java.util.Date date = sdf.parse(updatepedurationStr);
                            updatepeduration = new Time(date.getTime());
                        } catch (Exception e) {
                            System.out.println("Invalid time format. Please try again.");
                            return;
                        }
                    } else {
                        System.out.println("No input available");
                        return;
                    }
                    
                    System.out.println("Enter Release Date (yyyy-mm-dd): ");
                	String updatepereleasedateStr = scanner.next();
                    if (scanner.hasNextLine()) {
                    	                        	
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                        java.util.Date updatepereleaseDate = null;
                        try {
                        	System.out.println(updatepereleaseDate);
                        	System.out.println(updatepereleasedateStr);
                            updatepereleaseDate = dateFormat.parse(updatepereleasedateStr);
                        } catch (Exception e) {
                            System.out.println("Invalid Date format. Please try again.");
                            return;
                        }
                    } else {
                        System.out.println("No input available");
                        return;
                    }
                	
                	try {
                		peDao = new PodcastEpisodeDao();
                		int rowsUpdated = peDao.updatePodcastEpisode(updatepemediaid, updatepetitle, updatepeadrate, updatepeflatfee, updatepeduration, updatepereleasedateStr);
                		if (rowsUpdated == 1) {
                			System.out.println("Podcast Episode Updated successfully");
                		}
                		else {
                			System.out.println("Podcast Episode not found");
                		}
                		break;
                	}catch(SQLException e) {
                		System.out.println("Error update Podcast Episode " + e.getMessage());
                	}
                	break;
                	
                case 12:
                	// Delete Podcast Episode
                	
                	System.out.println("Enter MediaID of podcast episode to be deleted : ");
                	int deletepemediaid = scanner.nextInt();
                	try {
                		peDao = new PodcastEpisodeDao();
                		int deletedPodcastEpisode = peDao.deletePodcastEpisode(deletepemediaid);
                		System.out.println("LALAAL" + deletedPodcastEpisode);
                		if (deletedPodcastEpisode == 1) {
                			System.out.println("Podcast Episode deleted successfully");
                		}
                		else {
                			System.out.println("Podcast Episode deletion failed");
                		}
                	}
                		catch (SQLException e) {
                			System.out.println("Error deleting Podcast Episode : " + e.getMessage());
                		}
                    break;    
                case 13:
                    System.out.println("Not Impleented");
                    break;
                case 14:
                    System.out.println("Not Impleented");
                    break;
                case 15:
                    System.out.println("Not Impleented");
                    break;
                case 16:
                    System.out.println("Not Impleented");
                    break;
                case 17:
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
