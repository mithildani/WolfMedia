package wolfmedia;
import java.sql.Date;
import java.sql.Time;

public class Song {
    private int mediaId;
    private Date releaseDate;
    private Time duration;
    private String title;
    private double royaltyRate;
    private String country;
    private String language;
    private int albumId;
    private int trackNumber;
    
    public Song() {

    }
    
    public Song(int mediaId, Time duration, String title, double royaltyRate, String country,
			String language, int albumId, int trackNumber) {
		super();
		this.mediaId = mediaId;
//		this.releaseDate = releaseDate;
		this.duration = duration;
		this.title = title;
		this.royaltyRate = royaltyRate;
		this.country = country;
		this.language = language;
		this.albumId = albumId;
		this.trackNumber = trackNumber;
	}

	public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRoyaltyRate() {
        return royaltyRate;
    }

    public void setRoyaltyRate(double royaltyRate) {
        this.royaltyRate = royaltyRate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }
}
