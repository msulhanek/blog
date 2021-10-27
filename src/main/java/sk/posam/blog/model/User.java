package sk.posam.blog.model;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numberOfPosts;
    private String username;
    private String password;
    private String name;
    private String surename;
    private String email;
    private Instant registerDate;
    private String profilePicture;
    private String note;

    public User() {
    }

    public User(Long id, int numberOfPosts, String username, String password, String name, String surename, String email, Instant registerDate, String profilePicture, String note) {
        this.id = id;
        this.numberOfPosts = numberOfPosts;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surename = surename;
        this.email = email;
        this.registerDate = registerDate;
        this.profilePicture = profilePicture;
        this.note = note;
    }

    public int getNumberOfPosts() {
        return numberOfPosts;
    }

    public void setNumberOfPosts(int numberOfPosts) {
        this.numberOfPosts = numberOfPosts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Instant registerDate) {
        this.registerDate = registerDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
