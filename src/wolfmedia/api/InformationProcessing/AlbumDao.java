package wolfmedia.api.InformationProcessing;
import wolfmedia.model.Album;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;


import wolfmedia.DBConnection;
import wolfmedia.model.Song;

public class AlbumDao {
	public AlbumDao(){
	}
	public List<Album> getAllAlbums() {
		String sql = "SELECT * FROM Album";
		List<Album> albums = new ArrayList<>();
		Connection connection = DBConnection.getConnection();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Album album = new Album();
                album.setAlbumID(rs.getInt("AlbumID"));
                album.setArtistID(rs.getInt("ArtistID"));
                album.setName(rs.getString("Name"));
                album.setEdition(rs.getString("Edition"));
                album.setReleaseYear(rs.getDate("ReleaseYear"));

                albums.add(album);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return albums;
	}

	public Album getAlbumById(int albumId) {
        String sql = "SELECT * FROM aachava2.Album WHERE AlbumID = ?";
        Album album = null;
        Connection connection = DBConnection.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, albumId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	 album = new Album();
            	 album.setAlbumID(rs.getInt("AlbumID"));
                 album.setArtistID(rs.getInt("ArtistID"));
                 album.setName(rs.getString("Name"));
                 album.setEdition(rs.getString("Edition"));
                 album.setReleaseYear(rs.getDate("ReleaseYear"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return album;
    }
	
	public int updateAlbum(int albumId, String name, String edition, int releaseyear) throws SQLException {
        String sql = "UPDATE Album SET Name = ?, Edition = ?, ReleaseYear = ? WHERE AlbumID = ?";
        Connection connection = DBConnection.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, edition);
            statement.setInt(3, releaseyear);
            statement.setInt(4, albumId);
            return statement.executeUpdate();
        }
    }
	
	public boolean insertAlbum(Album album) throws SQLException {
        String sql = "INSERT INTO Album (AlbumID, ArtistID, Name, Edition, ReleaseYear) " +
                     "VALUES (?, ?, ?, ?, ?)";
        Connection connection = DBConnection.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        	stmt.setInt(1, album.getAlbumID());
            stmt.setInt(2, album.getArtistID());
            stmt.setString(3, album.getName());
            stmt.setString(4, album.getEdition());
            stmt.setDate(5, album.getReleaseYear());
            int rowsAffected = stmt.executeUpdate();
            return (rowsAffected == 1);
        } catch (SQLException e) {
            throw e;
        }
    }
}

