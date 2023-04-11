package wolfmedia.model;
import java.sql.Date;


public class Album {
    private int albumID;
    private int artistID;
    private String name;
    private String edition;
    private Date releaseyear;

    // Empty constructor
    public Album() {
    }

    // Constructor with parameters
    public Album(int albumId, int artistId, String name, String edition, Date releaseyear) {
        this.albumID = albumId;
		this.artistID = artistId;
    	this.name = name;
        this.edition = edition;
        this.releaseyear = releaseyear;
    }

    // Getters and Setters
    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }
	
	public int getArtistID() {
        return artistID;
    }

    public void setArtistID(int artistID) {
        this.artistID = artistID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Date getReleaseYear() {
        return releaseyear;
    }

    public void setReleaseYear(Date releaseyear) {
        this.releaseyear = releaseyear;
    }

    
}
