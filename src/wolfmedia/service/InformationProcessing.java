package wolfmedia.service;

import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import wolfmedia.api.ArtistDao;
import wolfmedia.api.PodcastEpisodeDao;
import wolfmedia.api.PodcastHostDao;
import wolfmedia.api.SongDao;
import wolfmedia.model.Artist;
import wolfmedia.model.PodcastHost;
import wolfmedia.model.PodcastEpisode;

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
			PodcastHostDao hostDao = null;
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
							break;
						}
					} else {
						System.out.println("No input available");
						break;
					}

					System.out.print("Enter royalty rate (e.g. 12.50): ");
					double songRoyaltyRate = scanner.nextDouble();
					System.out.print("Enter album ID: ");
					int songAlbumID = scanner.nextInt();

					System.out.println("Enter Release Date (yyyy-mm-dd): ");
					String songreleasedateStr = scanner.next();
					if (scanner.hasNextLine()) {

						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
						java.util.Date songreleaseDate = null;
						try {
							System.out.println(songreleaseDate);
							System.out.println(songreleasedateStr);
							songreleaseDate = dateFormat.parse(songreleasedateStr);
						} catch (Exception e) {
							System.out.println("Invalid Date format. Please try again.");
							break;
						}
					} else {
						System.out.println("No input available");
						break;
					}

					try {
						songDao = new SongDao();
						boolean insertedSong = songDao.insertSong(songMediaId, duration, songTitle, songRoyaltyRate,
								songCountry,
								songLanguage, songAlbumID, songTrackNumber, songreleasedateStr);
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
					int updatesongmediaid = scanner.nextInt();
					scanner.nextLine(); // consume newline character
					System.out.print("Enter new title: ");
					String updatesongtitle = scanner.nextLine();
					System.out.print("Enter new country: ");
					String updatesongcountry = scanner.nextLine();
					System.out.print("Enter new language: ");
					String updatesonglanguage = scanner.nextLine();
					System.out.println("Enter new Royalty rate : ");
					double updatesongroyaltyrate = scanner.nextDouble();
					System.out.println("Enter new  Track number : ");
					int updatesongtracknumber = scanner.nextInt();
					System.out.println("Enter new duration (hh:mm:ss): ");
					scanner.nextLine();
					Time updatesongduration = null;
					if (scanner.hasNextLine()) {
						String updatesongdurationStr = scanner.nextLine();

						try {
							SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
							java.util.Date date = sdf.parse(updatesongdurationStr);
							updatesongduration = new Time(date.getTime());
						} catch (Exception e) {
							System.out.println("Invalid time format. Please try again.");
							break;
						}
					} else {
						System.out.println("No input available");
						break;
					}
					System.out.println("Enter new Release Date (yyyy-mm-dd): ");
					String updatesongreleasedateStr = scanner.next();
					if (scanner.hasNextLine()) {

						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
						java.util.Date updatesongreleaseDate = null;
						try {
							System.out.println(updatesongreleaseDate);
							System.out.println(updatesongreleasedateStr);
							updatesongreleaseDate = dateFormat.parse(updatesongreleasedateStr);
						} catch (Exception e) {
							System.out.println("Invalid Date format. Please try again.");
							break;
						}
					} else {
						System.out.println("No input available");
						break;
					}

					try {
						songDao = new SongDao();
						int rowsUpdated = songDao.updateSong(updatesongmediaid, updatesongtitle, updatesongcountry,
								updatesonglanguage, updatesongduration, updatesongreleasedateStr, updatesongroyaltyrate,
								updatesongtracknumber);
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

					System.out.print("Enter MediaID od Song to be deleted : ");
					int deletesongmediaid = scanner.nextInt();

					try {
						songDao = new SongDao();
						int deletedSong = songDao.deleteSong(deletesongmediaid);
						if (deletedSong == 1) {
							System.out.println("Song deleted successfully");
						} else {
							System.out.println("Song deletion failed");
						}
					} catch (SQLException e) {
						System.out.println("Error deleting Song : " + e.getMessage());
					}
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

					Artist newArtist = new Artist(artistId, artistName, artistStatus, artistType, artistCountry,
							artistPrimaryGenre);
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
					// update an artist
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
						int rowsUpdated = artistDao.updateArtist(updateArtistId, updatedArtistName, updatedArtistStatus,
								updatedArtistType, updatedArtistCountry, updatedArtistPrimaryGenre);
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
					// Delete an artist
					System.out.print("Enter ArtistID of Artist to be deleted : ");
					int deleteartistid = scanner.nextInt();

					try {
						artistDao = new ArtistDao();
						int deletedArtist = artistDao.deleteArtist(deleteartistid);
						if (deletedArtist == 1) {
							System.out.println("Artist deleted successfully");
						} else {
							System.out.println("Artist deletion failed");
						}
					} catch (SQLException e) {
						System.out.println("Error deleting Artist : " + e.getMessage());
					}
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

					// update a PodcastHost
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
						int rowsUpdated = hostDao.updatePodcastHost(updateHostID, updatedFirstName, updatedLastName,
								updatedCity, updatedEmail, updatedPhoneNumber);
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
					// Delete Host
					System.out.print("Enter HostID of Host to be deleted : ");
					int deletehostid = scanner.nextInt();

					try {
						hostDao = new PodcastHostDao();
						int deletedHost = hostDao.deletePodcastHost(deletehostid);
						if (deletedHost == 1) {
							System.out.println("Podcast Host deleted successfully");
						} else {
							System.out.println("Podcast Host deletion failed");
						}
					} catch (SQLException e) {
						System.out.println("Error deleting Podcast Host : " + e.getMessage());
					}
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
							break;
						}
					} else {
						System.out.println("No input available");
						break;
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
							break;
						}
					} else {
						System.out.println("No input available");
						break;
					}

					PodcastEpisode pe = new PodcastEpisode(peMediaId, pepodcastid, petitle, peduration, peadrate,
							peflatfee, pereleasedateStr);
					try {
						peDao = new PodcastEpisodeDao();
						boolean insertedPodcastEpisode = peDao.insertPodcastEpisode(pe);
						if (insertedPodcastEpisode) {
							System.out.println("Podcast Episode inserted sucessfully");
						} else {
							System.out.println("PodcastEpisode insertion failed");
						}
					} catch (SQLException e) {
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
					System.out.println("Enter new duration (hh:mm:ss): ");
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

					System.out.println("Enter new Release Date (yyyy-mm-dd): ");
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
						int rowsUpdated = peDao.updatePodcastEpisode(updatepemediaid, updatepetitle, updatepeadrate,
								updatepeflatfee, updatepeduration, updatepereleasedateStr);
						if (rowsUpdated == 1) {
							System.out.println("Podcast Episode Updated successfully");
						} else {
							System.out.println("Podcast Episode not found");
						}
						break;
					} catch (SQLException e) {
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
						} else {
							System.out.println("Podcast Episode deletion failed");
						}
					} catch (SQLException e) {
						System.out.println("Error deleting Podcast Episode : " + e.getMessage());
					}
					break;
				case 13:
					// song to album
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
					} catch (SQLException e) {
						System.out.println("Error assigning Song to Album: " + e.getMessage());
					}
					break;
				case 14:
					// artist to album

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
					} catch (SQLException e) {
						System.out.println("Error assigning Artist to Record Label: " + e.getMessage());
					}
					break;
				case 16:
					// assign podcast episode to podcast

					System.out.println("Enter the mediaID odf the podcastepisode :");
					int ppemediaid = scanner.nextInt();
					System.out.println("Enter the podcastID : ");
					int ppepodcastid = scanner.nextInt();
					try {
						peDao = new PodcastEpisodeDao();
						int rowsUpdated = peDao.assignPodcastEpisodetoPodcast(ppemediaid, ppepodcastid);
						if (rowsUpdated == 1) {
							System.out.println("Podcast Episode was successfully assigned to Podcast");
						} else {
							System.out.println("Error assigning podcast episode to podcast");
						}
					} catch (SQLException e) {
						System.out.println("Error assigning podcast episode to podcast: " + e.getMessage());
					}
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
