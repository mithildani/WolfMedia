package wolfmedia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SongDao {
    private final Connection connection;

    public SongDao() {
        String url = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/aachava2";
        String user = "aachava2";
        String password = "200477490";
        try {
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to database", e);
        }
    }
    
    public Song getSongById(int mediaId) {
        String sql = "SELECT * FROM aachava2.Song WHERE MediaID = ?";
        Song song = null;
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
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.setString(2, country);
            statement.setString(3, language);
            statement.setInt(4, mediaId);
            return statement.executeUpdate();
        }
    }
    
//    public boolean deleteSong(int mediaId) throws SQLException {
//        String sql = "DELETE FROM Song WHERE MediaID = ?";
//        PreparedStatement statement = connection.prepareStatement(sql);
//        statement.setInt(1, mediaId);
//
//        int rowsDeleted = statement.executeUpdate();
//
//        return rowsDeleted > 0;
//    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
