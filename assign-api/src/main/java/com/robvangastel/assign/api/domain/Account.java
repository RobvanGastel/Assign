package com.robvangastel.assign.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Rob
 */

@Entity
public class Account implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    private String password;
    
    private String email;
    private String firstName;
    private String surname;
    private String education;
    private String phoneNumber;
    private String profileImage;
    
    private Date lastLoggedIn;
    private String dateCreated;
    
    public Account(String email, String password) {
        this.email = email;
        this.password = password;
        
        this.dateCreated = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    }

    Account() { // jpa only
    }
    
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
     * @return email the email to set
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
    public Date getLastLoggedIn() {
        return lastLoggedIn;
    }

    /**
     * @param lastLoggedIn the lastLoggedIn to set
     */
    public void setLastLoggedIn(Date lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }

    /**
     * @return the dateCreated
     */
    public String getDateCreated() {
        return dateCreated;
    }
}