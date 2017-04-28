package com.robvangastel.assign.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Created by Rob on 23-4-2017.
 */

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String role;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Post> posts = new ArrayList<Post>();
    
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.PERSIST)
    private SocialChannels socialChannel;
    
    @JsonIgnore
    private String password;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    private String firstName;
    private String surname;
    private String education;
    private String phoneNumber;
    private String profileImage;
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    @Column(nullable = false, unique = false)
    private Timestamp lastLoggedIn;
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    @Column(nullable = false, unique = false)
    private Timestamp dateCreated;
    
    /***
     * Short User constructor for testing
     * @param email
     * @param password 
     * @param firstName 
     * @param surname 
     * @param phoneNumber 
     */
    public User(String email, String password, String firstName,
                String surname, String phoneNumber) {
        this.role = Role.USER.toString();
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        
        this.socialChannel = new SocialChannels();
        
        this.dateCreated = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
        this.lastLoggedIn = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
    }
    
    /***
     * Complete User constructor
     * @param email
     * @param password
     * @param firstName
     * @param surname
     * @param phoneNumber
     * @param socialChannel 
     */
    public User(String email, String password, String firstName,
                String surname, String phoneNumber, SocialChannels socialChannel) {
        this.role = Role.USER.toString();
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        
        this.socialChannel = socialChannel;
        
        this.dateCreated = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
        this.lastLoggedIn = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    /***
     *
     * @param email
     * @param password
     * @param firstName
     */
    public User(String email, String password, String firstName) {
        this.role = Role.USER.toString();
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.surname = "";
        this.phoneNumber = "";

        this.socialChannel = new SocialChannels();

        this.dateCreated = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
        this.lastLoggedIn = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    User() {}
    
    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the education
     */
    public String getEducation() {
        return education;
    }

    /**
     * @param education the education to set
     */
    public void setEducation(String education) {
        this.education = education;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the profileImage
     */
    public String getProfileImage() {
        return profileImage;
    }

    /**
     * @param profileImage the profileImage to set
     */
    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    /**
     * @return the lastLoggedIn
     */
    public Timestamp getLastLoggedIn() {
        return lastLoggedIn;
    }

    /**
     * @param lastLoggedIn the lastLoggedIn to set
     */
    public void setLastLoggedIn(Timestamp lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }

    /**
     * @return the dateCreated
     */
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    /**
     * @return the posts
     */
    public List<Post> getPosts() {
        return posts;
    }

    /**
     * @param posts the posts to set
     */
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    /**
     * @return the socialChannel
     */
    public SocialChannels getSocialChannel() {
        return socialChannel;
    }

    /**
     * @param socialChannel the socialChannel to set
     */
    public void setSocialChannel(SocialChannels socialChannel) {
        this.socialChannel = socialChannel;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * @return the role
     */
    public Role getRole() {
        return Role.valueOf(role);
    }

    /**
     * @param role the role to set
     */
    public void setRole(Role role) {
        this.role = role.toString();
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}