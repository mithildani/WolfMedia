package wolfmedia.api.InformationProcessing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import wolfmedia.DBConnection;
import wolfmedia.model.Song;

public class SongDao {

    public SongDao() {}

    public Song getSongById(int mediaId) {
        String sql = "SELECT * FROM aachava2.Song WHERE MediaID = ?";
        Song song = null;
        Connection connection = DBConnection.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, mediaId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                song = new Song();
                song.setMediaId(rs.getInt("MediaID"));
                song.setReleaseDate(rs.getDate("ReleaseDate"));
                song.setDuration(rs.getTime("Duration"));
                song.setTitle(rs.getString("Title"));
                song.setRoyaltyRate(rs.getDouble("RoyaltyRate"));
                song.setCountry(rs.getString("Country"));
                song.setLanguage(rs.getString("Language"));
                song.setAlbumId(rs.getInt("AlbumID"));
                song.setTrackNumber(rs.getInt("TrackNumber"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return song;
    }

    public List<Song> getAllSongs() {
        String sql = "SELECT * FROM aachava2.Song";
        List<Song> songs = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Song song = new Song();
                song.setMediaId(rs.getInt("MediaID"));
                song.setReleaseDate(rs.getDate("ReleaseDate"));
                song.setDuration(rs.getTime("Duration"));
                song.setTitle(rs.getString("Title"));
                song.setRoyaltyRate(rs.getDouble("RoyaltyRate"));
                song.setCountry(rs.getString("Country"));
                song.setLanguage(rs.getString("Language"));
                song.setAlbumId(rs.getInt("AlbumID"));
                song.setTrackNumber(rs.getInt("TrackNumber"));
                songs.add(song);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songs;
    }

    public int updateSong(int mediaId, String title, String country, String language) throws SQLException {
        String sql = "UPDATE Song SET Title = ?, Country = ?, Language = ? WHERE MediaID = ?";
        Connection connection = DBConnection.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.setString(2, country);
            statement.setString(3, language);
            statement.setInt(4, mediaId);
            return statement.executeUpdate();
        }
    }

    // public boolean deleteSong(int mediaId) throws SQLException {
    // String sql = "DELETE FROM Song WHERE MediaID = ?";
    // PreparedStatement statement = connection.prepareStatement(sql);
    // statement.setInt(1, mediaId);
    //
    // int rowsDeleted = statement.executeUpdate();
    //
    // return rowsDeleted > 0;
    // }

    public boolean insertSong(Song song) throws SQLException {
        // Check if album exists
        String albumSql = "SELECT * FROM aachava2.Album WHERE AlbumID = ?";
        Connection connection = DBConnection.getConnection();
        try (PreparedStatement albumStmt = connection.prepareStatement(albumSql)) {
            albumStmt.setInt(1, song.getAlbumId());
            ResultSet albumRs = albumStmt.executeQuery();
            if (!albumRs.next()) {
                // Album does not exist
                return false;
            } else {
                // check if artist exists, if not create one
            }
        }

        // Check if artist exists

        // Insert song
        String songSql = "INSERT INTO aachava2.Song " +
                "(MediaID, ReleaseDate, Duration, Title, RoyaltyRate, Country, Language, AlbumID, TrackNumber) " +
                "VALUES (?, CURDATE(), ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement songStmt = connection.prepareStatement(songSql)) {
            songStmt.setInt(1, song.getMediaId());
            // songStmt.setDate(2, song.getReleaseDate());
            songStmt.setTime(2, song.getDuration());
            songStmt.setString(3, song.getTitle());
            songStmt.setDouble(4, song.getRoyaltyRate());
            songStmt.setString(5, song.getCountry());
            songStmt.setString(6, song.getLanguage());
            songStmt.setInt(7, song.getAlbumId());
            songStmt.setInt(8, song.getTrackNumber());

            int rowsAffected = songStmt.executeUpdate();
            return (rowsAffected == 1);
        } catch (SQLException e) {
            throw e;
        }
    }
    

    public int assignSongToAlbum(int songMediaId, int albumId) throws SQLException {
        String sql = "UPDATE Song SET AlbumID = ? WHERE MediaID = ?";
        Connection conn = DBConnection.getConnection();
        try (
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, albumId);
            pstmt.setInt(2, songMediaId);

            return pstmt.executeUpdate();
        }
    }

    public List<Song> getSongsByArtist(int aID) throws SQLException {
        List<Song> songs = new ArrayList<>();
        String query = "SELECT s.MediaID, s.ReleaseDate, s.Duration, s.Title, s.RoyaltyRate, " +
                       "s.Country, s.Language, s.AlbumID, s.TrackNumber " +
                       "FROM Song s INNER JOIN Collaborated c ON s.MediaID = c.MediaID " +
                       "INNER JOIN Artist a ON c.ArtistID = a.ArtistID " +
                       "WHERE a.ArtistID = ?";
        Connection conn = DBConnection.getConnection();
        try (
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, aID);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Song song = new Song();
                    song.setMediaId(rs.getInt("MediaID"));
                    song.setReleaseDate(rs.getDate("ReleaseDate"));
                    song.setDuration(rs.getTime("Duration"));
                    song.setTitle(rs.getString("Title"));
                    song.setRoyaltyRate(rs.getDouble("RoyaltyRate"));
                    song.setCountry(rs.getString("Country"));
                    song.setLanguage(rs.getString("Language"));
                    song.setAlbumId(rs.getInt("AlbumID"));
                    song.setTrackNumber(rs.getInt("TrackNumber"));
                    songs.add(song);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving songs by artist: " + e.getMessage());
            throw e;
        }
        return songs;
    }
    
    public List<Song> getSongsByAlbum(int aID) throws SQLException {
        List<Song> songs = new ArrayList<>();
        String query = "SELECT S.* FROM Song S INNER JOIN Album A on S.AlbumID = A.AlbumID where A.AlbumID = ?";
        Connection conn = DBConnection.getConnection();
        try (
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, aID);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Song song = new Song();
                    song.setMediaId(rs.getInt("MediaID"));
                    song.setReleaseDate(rs.getDate("ReleaseDate"));
                    song.setDuration(rs.getTime("Duration"));
                    song.setTitle(rs.getString("Title"));
                    song.setRoyaltyRate(rs.getDouble("RoyaltyRate"));
                    song.setCountry(rs.getString("Country"));
                    song.setLanguage(rs.getString("Language"));
                    song.setAlbumId(rs.getInt("AlbumID"));
                    song.setTrackNumber(rs.getInt("TrackNumber"));
                    songs.add(song);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving songs by artist: " + e.getMessage());
            throw e;
        }
        return songs;
    }
    
    public void simulateSongPlayback(int userId, int mediaId) throws SQLException {
    	Connection conn = DBConnection.getConnection();
    	try {
    		// Check if there is an existing playback record for this user and song on the current day
            PreparedStatement stmt = conn.prepareStatement("SELECT Count FROM aachava2.Playbacks WHERE UserID = ? AND MediaID = ? AND MONTH(PlayedAt) = MONTH(CURDATE())");
            stmt.setInt(1, userId);
            stmt.setInt(2, mediaId);
            
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // If there is an existing record, increment the playcount
                int playCount = rs.getInt("Count") + 1;
                stmt = conn.prepareStatement("UPDATE aachava2.Playbacks SET Count = ? WHERE UserID = ? AND MediaID = ? AND PlayedAt = CURDATE()");
                stmt.setInt(1, playCount);
                stmt.setInt(2, userId);
                stmt.setInt(3, mediaId);
                
                stmt.executeUpdate();
                System.out.println("You have played this song " + playCount + " times now!");
            } else {
                // If there is no existing record, create a new playback record with playcount 1
                stmt = conn.prepareStatement("INSERT INTO aachava2.Playbacks (MediaID, UserID, PlayedAt, Count) VALUES (?, ?, CURDATE(), 1)");
                stmt.setInt(1, mediaId);
                stmt.setInt(2, userId);
                
                stmt.executeUpdate();
                System.out.println("This was your first time playing the song!");
            }
    	} catch(SQLException e) {
    		System.out.println("Error retrieving songs by artist: " + e.getMessage());
            throw e;
    	}        
    }
    
    public int getMonthlyListenersForArtist(int artistId, int month) throws SQLException {
    	Connection conn = DBConnection.getConnection();
    	int monthlyListeners = 0;
    	try {
    		PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(DISTINCT P.UserID) AS MonthlyListeners FROM Playbacks P INNER JOIN Collaborated C ON P.MediaID = C.MediaID WHERE P.MediaID IN (SELECT DISTINCT C.MediaID FROM Collaborated WHERE C.ArtistID = ?) AND MONTH(P.PlayedAt) = ?");
    		stmt.setInt(1, artistId);
    		stmt.setInt(2, month);
    		ResultSet rs = stmt.executeQuery();
    		
    		if(rs.next()) {
    			monthlyListeners = rs.getInt("MonthlyListeners");
    		}
    		
    	}catch(SQLException e) {
    		System.out.println("Error retrieving songs by artist: " + e.getMessage());
            throw e;
    	}     
		return monthlyListeners;
	}



}
