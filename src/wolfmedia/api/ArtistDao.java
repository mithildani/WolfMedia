package wolfmedia.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import wolfmedia.DBConnection;
import wolfmedia.model.Artist;

public class ArtistDao {

    public ArtistDao() {
        
    } 
    public List<Artist> getAllArtists() {
        String sql = "SELECT * FROM Artist";
        List<Artist> artists = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Artist artist = new Artist();
                artist.setArtistID(rs.getInt("ArtistID"));
                artist.setName(rs.getString("Name"));
                artist.setStatus(rs.getString("Status"));
                artist.setType(rs.getString("Type"));
                artist.setCountry(rs.getString("Country"));
                artist.setPrimaryGenre(rs.getString("primaryGenre"));

                artists.add(artist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artists;
    }

    public Artist getArtistById(int artistId) {
        String sql = "SELECT * FROM aachava2.Artist WHERE ArtistID = ?";
        Artist artist= null;
        Connection connection = DBConnection.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, artistId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	 artist = new Artist();
            	 artist.setArtistID(rs.getInt("ArtistID"));
                 artist.setName(rs.getString("Name"));
                 artist.setStatus(rs.getString("Status"));
                 artist.setType(rs.getString("Type"));
                 artist.setCountry(rs.getString("Country"));
                 artist.setPrimaryGenre(rs.getString("primaryGenre"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artist;
    }

    public int deleteArtist(int artistId) throws SQLException{
    	String sql = "DELETE FROM Artist WHERE ArtistID = ?";
    	Connection connection = DBConnection.getConnection();
    	try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, artistId);
            return statement.executeUpdate();
    	}
    	catch (SQLException e) {
            throw e;
        }
    }
    
    public int updateArtist(int artistId, String name, String status, String type, String country, String primaryGenre) throws SQLException {
        String sql = "UPDATE Artist SET Name = ?, Status = ?, Country = ?, PrimaryGenre = ? WHERE ArtistID = ?";
        Connection connection = DBConnection.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, status);
            statement.setString(3, country);
            statement.setString(4, primaryGenre);
            statement.setInt(5, artistId);
            return statement.executeUpdate();
        }
        catch (SQLException e) {
            throw e;
        }
    }
    
    public boolean insertArtist(Artist artist) throws SQLException {
        String sql = "INSERT INTO Artist (ArtistID, Name, Status, Type, Country, PrimaryGenre) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = DBConnection.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        	stmt.setInt(1, artist.getArtistID());
            stmt.setString(2, artist.getName());
            stmt.setString(3, artist.getStatus());
            stmt.setString(4, artist.getType());
            stmt.setString(5, artist.getCountry());
            stmt.setString(6, artist.getPrimaryGenre());
            int rowsAffected = stmt.executeUpdate();
            return (rowsAffected == 1);
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public int assignArtistToAlbum(int artistId, int albumId) throws SQLException {
        String query = "UPDATE Album SET ArtistID = ? WHERE AlbumID = ?";
        Connection connection = DBConnection.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, artistId);
            stmt.setInt(2, albumId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated;
        }
    }
    
    public int assignArtistToRecordLabel(int artistId, String labelName) throws SQLException{
        String query = "INSERT INTO Contract (LabelName, ArtistID, StartDate) VALUES (?, ?, CURDATE())";
        int rowsUpdated = 0;
        Connection conn = DBConnection.getConnection();
        try (
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, labelName);
            stmt.setInt(2, artistId);
            rowsUpdated = stmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error assigning artist to record label: " + ex.getMessage());
        }

        return rowsUpdated;
    }




}
