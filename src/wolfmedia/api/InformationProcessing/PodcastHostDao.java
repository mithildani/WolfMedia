package wolfmedia.api.InformationProcessing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import wolfmedia.DBConnection;
import wolfmedia.model.Artist;
import wolfmedia.model.PodcastHost;

public class PodcastHostDao {
	
	 public PodcastHostDao() {
	        
	    }
	 
	 public List<PodcastHost> getAllPodcastHosts() {
	        String sql = "SELECT * FROM PodcastHost";
	        List<PodcastHost> hosts = new ArrayList<>();
	        Connection connection = DBConnection.getConnection();
	        try (Statement stmt = connection.createStatement()) {
	            ResultSet rs = stmt.executeQuery(sql);
	            while (rs.next()) {
	                PodcastHost host = new PodcastHost();
	                host.setHostID(rs.getInt("HostID"));
	                host.setFirstName(rs.getString("FirstName"));
	                host.setLastName(rs.getString("LastName"));
	                host.setCity(rs.getString("City"));
	                host.setEmail(rs.getString("Email"));
	                host.setPhone(rs.getString("Phone"));

	                hosts.add(host);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return hosts;
	    }
	 
	 public PodcastHost getHostById(int hostId) {
	        String sql = "SELECT * FROM PodcastHost WHERE HostID = ?";
	        PodcastHost host = null;
	        Connection connection = DBConnection.getConnection();
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setInt(1, hostId);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	            	host = new PodcastHost();
	            	host.setHostID(rs.getInt("HostID"));
	            	host.setFirstName(rs.getString("FirstName"));
	            	host.setLastName(rs.getString("LastName"));
	            	host.setCity(rs.getString("City"));
	            	host.setEmail(rs.getString("Email"));
	            	host.setPhone(rs.getString("Phone"));

	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return host;
	    }
	 
	 public int updatePodcastHost(int hostId, String fName, String lName, String city, String email, String phone) throws SQLException {
	        String sql = "UPDATE PodcastHost SET FirstName = ?, LastName = ?, City = ?, Email = ?, Phone = ? WHERE HostID = ?";
	        Connection connection = DBConnection.getConnection();
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setString(1, fName);
	            statement.setString(2, lName);
	            statement.setString(3, city);
	            statement.setString(4, email);
	            statement.setString(5, phone);
	            statement.setInt(6, hostId);
	            return statement.executeUpdate();
	        }
	        catch (SQLException e) {
	            throw e;
	        }
	    }
	 
	 public int deletePodcastHost(int hostId) throws SQLException {
	        String sql = "DELETE FROM PodcastHost WHERE HostID = ?";
	        Connection connection = DBConnection.getConnection();
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setInt(1, hostId);
	            return statement.executeUpdate();
	        }
	        catch (SQLException e) {
	            throw e;
	        }
	 }
	 
	    public boolean insertPodcastHost(PodcastHost host) throws SQLException {
	        String sql = "INSERT INTO PodcastHost (HostID, FirstName, LastName, City, Email, Phone) " +
	                     "VALUES (?, ?, ?, ?, ?, ?)";
	        Connection connection = DBConnection.getConnection();
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        	stmt.setInt(1, host.getHostID());
	            stmt.setString(2, host.getFirstName());
	            stmt.setString(3, host.getLastName());
	            stmt.setString(4, host.getCity());
	            stmt.setString(5, host.getEmail());
	            stmt.setString(6, host.getPhone());
	            int rowsAffected = stmt.executeUpdate();
	            return (rowsAffected == 1);
	        } catch (SQLException e) {
	            throw e;
	        }
	    }
	    
	    public int assignHostToPodcast(int hostId, int podcastId) throws SQLException {
	        String query = "UPDATE Podcast SET HostID = ? WHERE PodcastID = ?";
	        Connection conn = DBConnection.getConnection();
	        try (
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setInt(1, hostId);
	            stmt.setInt(2, podcastId);

	            return stmt.executeUpdate();

	        } catch (SQLException e) {
	            throw new SQLException("Error assigning Podcast Host to Podcast: " + e.getMessage());
	        }
	    }

	 

}
