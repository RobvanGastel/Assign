package com.robvangastel.assign.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Rob van Gastel
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
    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Post> posts = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Reply> replies = new ArrayList<>();

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.PERSIST)
    private SocialLink socialLink;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<String> tags = new ArrayList<>();

    @Column(nullable = false, unique = true)
    private String email;

    private String password;
    private String name;
    private String profileImage;
    private String schoolCode;
    private String study;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    @Column(nullable = false)
    private Timestamp lastLoggedIn;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    @Column(nullable = false)
    private Timestamp dateCreated;

    /***
     *
     * @param email
     * @param password
     * @param name
     * @param schoolCode
     * @param study
     * @param tags
     * @param socialLink
     */
    public User(String email, String password, String name, String schoolCode,
                String study, List<String> tags, SocialLink socialLink) {
        this.role = Role.USER.toString();
        this.email = email;
        this.password = password;
        this.name = name;
        this.schoolCode = schoolCode;
        this.study = study;
        this.tags = tags;
        this.socialLink = socialLink;
        this.profileImage = "default.png";

        this.lastLoggedIn = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    /***
     *
     * @param email
     * @param password
     * @param name
     * @param schoolCode
     */
    public User(String email, String password, String name, String schoolCode) {
        this.role = Role.USER.toString();
        this.email = email;
        this.password = password;
        this.name = name;
        this.schoolCode = schoolCode;
        this.profileImage = "default.png";

        this.lastLoggedIn = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    /***
     * Before presist creates the date the user is created.
     */
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

    /***
     * these methods have the @JsonIgnore so it doesnt return this hash in
     * the controllers.
     */
    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @JsonIgnore
    public List<Reply> getReplies() {
        return this.replies;
    }

    @JsonIgnore
    public List<Post> getPosts() {
        return this.posts;
    }
}
