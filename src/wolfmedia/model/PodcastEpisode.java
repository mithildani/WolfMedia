package wolfmedia.model;
import java.sql.Time;


public class PodcastEpisode {
    private int mediaId;
    private Time duration;
    private String title;
    private double adrate;
    private double flatfee;
	private int podcastId;
	private String releasedateStr;

    // Empty constructor
    public PodcastEpisode() {
    }

    // Constructor with parameters
    public PodcastEpisode(int mediaId, int podcastId, String title, Time duration, double adrate, double flatfee, String releasedateStr) {
        this.mediaId = mediaId;
		this.podcastId = podcastId;
    	this.title = title;
        this.flatfee = flatfee;
		this.adrate = adrate;
		this.duration = duration;
		this.releasedateStr = releasedateStr;
    }

    // Getters and Setters
    public int getMediaID() {
        return mediaId;
    }

    public void setMediaID(int mediaID) {
        this.mediaId = mediaID;
    }
	
	public int getPodcastID() {
        return podcastId;
    }

    public void setPodcastID(int podcastID) {
        this.podcastId = podcastID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releasedateStr;
    }

    public void setReleaseDate(String releasedateStr) {
        this.releasedateStr = releasedateStr;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }
	
	public double getAdRate() {
        return adrate;
    }

    public void setAdRate(double adrate) {
        this.adrate = adrate;
    }
	
	public double getFlatFee() {
        return flatfee;
    }

    public void setFlatFee(double flatfee) {
        this.flatfee = flatfee;
    }

    
}
