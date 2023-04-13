package wolfmedia.api.InformationProcessing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import wolfmedia.DBConnection;
import wolfmedia.model.Song;

public class SongDao {

    public SongDao() {}

    public Song getSongById(int mediaId) {
        String sql = "SELECT * FROM Song WHERE MediaID = ?";
        Song song = null;
        Connection connection = DBConnection.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, mediaId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                song = new Song();
                song.setMediaId(rs.getInt("MediaID"));
                song.setReleaseDate(rs.getString("ReleaseDate"));
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
        String sql = "SELECT * FROM Song";
        List<Song> songs = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Song song = new Song();
                song.setMediaId(rs.getInt("MediaID"));
                song.setReleaseDate(rs.getString("ReleaseDate"));
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

    public int updateSong(int mediaId, String title, String country, String language, Time duration, String releasedate, double royaltyrate, int tracknumber) throws SQLException {
        String sql = "UPDATE Song SET Title = ?, Country = ?, Language = ?, Duration = ?, ReleaseDate = ?, RoyaltyRate = ?, TrackNumber = ? WHERE MediaID = ?";
        Connection connection = DBConnection.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.setString(2, country);
            statement.setString(3, language);
            statement.setTime(4, duration);
            statement.setString(5, releasedate);
            statement.setDouble(6, royaltyrate);
            statement.setInt(7, tracknumber);
            statement.setInt(8, mediaId);
            return statement.executeUpdate();
        }
    }

     public int deleteSong(int mediaId) throws SQLException {
     String sql = "DELETE FROM Media WHERE MediaID = ?";
     Connection connection = DBConnection.getConnection();
     PreparedStatement statement = connection.prepareStatement(sql);
     statement.setInt(1, mediaId);  
     int rowsDeleted = statement.executeUpdate();    
     return rowsDeleted;
     }

    public boolean insertSong(int mediaid, Time duration,String title,double royaltyrate,String country,String
            language,int albumid,int tracknumber,String releasedate) throws SQLException {
        // Check if album exists
//        String albumSql = "SELECT * FROM Album WHERE AlbumID = ?";
//        Connection connection = DBConnection.getConnection();
//        try (PreparedStatement albumStmt = connection.prepareStatement(albumSql)) {
//            albumStmt.setInt(1, song.getAlbumId());
//            ResultSet albumRs = albumStmt.executeQuery();
//            if (!albumRs.next()) {
//                // Album does not exist
//                return false;
//            } else {
//                // check if album exists, if not create one
//            }
//        }

        // Check if artist exists

        // Insert song
    	System.out.println("KAKAKAKA" + duration);
        String songSql = "INSERT INTO Song " +
                "(MediaID, Duration, Title, RoyaltyRate, Country, Language, AlbumID, TrackNumber, ReleaseDate) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = DBConnection.getConnection();
		try (PreparedStatement songStmt = connection.prepareStatement(songSql)) {
            songStmt.setInt(1, mediaid);
            songStmt.setTime(2, duration);
            songStmt.setString(3, title);
            songStmt.setDouble(4, royaltyrate);
            songStmt.setString(5, country);
            songStmt.setString(6, language);
            songStmt.setInt(7, albumid);
            songStmt.setInt(8, tracknumber);
            songStmt.setString(9, releasedate);

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
                    song.setReleaseDate(rs.getString("ReleaseDate"));
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
                    song.setReleaseDate(rs.getString("ReleaseDate"));
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
            PreparedStatement stmt = conn.prepareStatement("SELECT Count FROM Playbacks WHERE UserID = ? AND MediaID = ? AND MONTH(PlayedAt) = MONTH(CURDATE())");
            stmt.setInt(1, userId);
            stmt.setInt(2, mediaId);
            
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // If there is an existing record, increment the playcount
                int playCount = rs.getInt("Count") + 1;
                stmt = conn.prepareStatement("UPDATE Playbacks SET Count = ? WHERE UserID = ? AND MediaID = ? AND PlayedAt = CURDATE()");
                stmt.setInt(1, playCount);
                stmt.setInt(2, userId);
                stmt.setInt(3, mediaId);
                
                stmt.executeUpdate();
                System.out.println("You have played this song " + playCount + " times now!");
            } else {
                // If there is no existing record, create a new playback record with playcount 1
                stmt = conn.prepareStatement("INSERT INTO Playbacks (MediaID, UserID, PlayedAt, Count) VALUES (?, ?, CURDATE(), 1)");
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

    public int getMonthlySongPlayCount(int mediaId, int month) throws SQLException {
    	
    	Connection conn = DBConnection.getConnection();
    	int monthlyListeners = 0;
    	try {
//    		String query = "SELECT SUM(Count) AS MonthlyCount FROM Playbacks WHERE MONTH(PlayedAt) =" +month+ " AND MediaID =" + mediaId;
//    		System.out.println(query);
    		PreparedStatement stmt = conn.prepareStatement("SELECT SUM(Count) AS ml FROM Playbacks WHERE MONTH(PlayedAt) = ? AND MediaID = ?");
    		
    		stmt.setInt(1, month);
    		stmt.setInt(2, mediaId);
    		ResultSet rs = stmt.executeQuery();
    		
    		if(rs.next()) {
    			monthlyListeners = rs.getInt("ml");
    		}
    		
    	}catch(SQLException e) {
    		System.out.println("Error retrieving song play count by month: " + e.getMessage());
            throw e;
    	}     
		return monthlyListeners;
	}	
    
    public int getMonthlyAlbumPlayCount(int albumId, int month) throws SQLException {
    	Connection conn = DBConnection.getConnection();
    	int monthlyCount = 0;
    	try {
    		PreparedStatement stmt = conn.prepareStatement("select SUM(Count) as MonthlyCount from Playbacks where MediaID in (select MediaID from Song where AlbumID = ?) and month(PlayedAt) = ?");
    		stmt.setInt(1, albumId);
    		stmt.setInt(2, month);
    		ResultSet rs = stmt.executeQuery();
    		
    		if(rs.next()) {
    			monthlyCount = rs.getInt("MonthlyCount");
    		}
    		
    	}catch(SQLException e) {
    		System.out.println("Error retrieving monthly playcount of album: " + e.getMessage());
            throw e;
    	}     
		return monthlyCount;
	}

    public List<String> getSongTitlesByArtist(int artistId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        List<String> songTitles = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("select Title from Song s inner join Collaborated C on s.MediaID = C.MediaID where ArtistID = ?");
            stmt.setInt(1, artistId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                songTitles.add(rs.getString("Title"));
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving song titles by artist: " + e.getMessage());
            throw e;
        }
        return songTitles;
    }
    
    public List<String> getSongByAlbum(int albumId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        List<String> songTitles = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from Song where AlbumID = ?");
            stmt.setInt(1, albumId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                songTitles.add(rs.getString("Title"));
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving song titles by artist: " + e.getMessage());
            throw e;
        }
        return songTitles;
    }

}
