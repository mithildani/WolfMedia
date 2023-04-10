package wolfmedia.model;

public class PodcastHost {
    private int hostID;
    private String firstName;
    private String lastName;
    private String city;
    private String email;
    private String phone;

    public PodcastHost(int hostID, String firstName, String lastName, String city, String email, String phone) {
        this.hostID = hostID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.email = email;
        this.phone = phone;
    }

    public PodcastHost(String firstName, String lastName, String city, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.email = email;
        this.phone = phone;
    }

    public PodcastHost() {}

    public int getHostID() {
        return hostID;
    }

    public void setHostID(int hostID) {
        this.hostID = hostID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
