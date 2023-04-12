package wolfmedia.model;

import java.sql.Date;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private Date registrationDate;
    private String phone;
    private String email;
    private float fee;
    
    // Default constructor
    public User() {
    }
    
    // Constructor with all fields
    public User(int id, String firstName, String lastName, String phone, String email, float fee) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.fee = fee;
    }
    
    // Constructor with required fields
    public User(String firstName, String lastName, Date registrationDate, float fee) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.registrationDate = registrationDate;
        this.fee = fee;
    }
    
    // Getters and setters for all fields
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
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
    
    public Date getRegistrationDate() {
        return registrationDate;
    }
    
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public float getFee() {
        return fee;
    }
    
    public void setFee(float fee) {
        this.fee = fee;
    }
}
