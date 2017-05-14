package com.robvangastel.assign.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.persistence.*;

/**
 * Created by Rob on 23-4-2017.
 */

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String role;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Post> posts;
    
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.PERSIST)
    private SocialChannels socialChannel;
    
    @JsonIgnore
    private String password;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    private String firstName;
    private String education;

    @Lob
    @Basic(fetch=FetchType.EAGER)
    private byte[] profileImage;
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    @Column(nullable = false)
    private Timestamp lastLoggedIn;
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    @Column(nullable = false)
    private Timestamp dateCreated;
    
    /***
     * Complete User constructor
     * @param email
     * @param password
     * @param firstName
     * @param socialChannel 
     */
    public User(String email, String password, String firstName, SocialChannels socialChannel) {
        this.role = Role.USER.toString();
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        
        this.socialChannel = socialChannel;
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

        this.socialChannel = new SocialChannels();
        this.lastLoggedIn = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    @PrePersist
    public void beforePersist(){
        this.dateCreated = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
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
}