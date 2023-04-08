package wolfmedia;

public class Artist {
    private int artistID;
    private String name;
    private String status;
    private String type;
    private String country;
    private String primaryGenre;

    // Empty constructor
    public Artist() {
    }

    // Constructor with parameters
    public Artist(String name, String status, String type, String country, String primaryGenre) {
        this.name = name;
        this.status = status;
        this.type = type;
        this.country = country;
        this.primaryGenre = primaryGenre;
    }

    // Getters and Setters
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPrimaryGenre() {
        return primaryGenre;
    }

    public void setPrimaryGenre(String primaryGenre) {
        this.primaryGenre = primaryGenre;
    }
}
