package com.robvangastel.assign.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.robvangastel.assign.domain.serializers.UserSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Rob van Gastel
 */

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(using = UserSerializer.class)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String role;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Post> posts = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Study study;

    private String studyName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    @LazyCollection(LazyCollectionOption.TRUE)
    private List<Reply> replies = new ArrayList<>();

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.PERSIST)
    private SocialLink socialLink;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.PERSIST)
    private Firebase firebase;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<String> tags = new ArrayList<>();

    @Column(nullable = false, unique = true)
    private String email;

    private String password;
    private String name;
    private String profileImage;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @Column(nullable = false)
    private Timestamp lastLoggedIn;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @Column(nullable = false)
    private Timestamp dateCreated;

    /***
     *
     * @param email
     * @param password
     * @param name
     * @param study
     * @param studyName
     * @param socialLink
     */
    public User(String email, String password, String name, Study study,
                String studyName, SocialLink socialLink) {
        this.role = Role.USER.toString();
        this.email = email;
        this.password = password;
        this.name = name;
        this.study = study;
        this.studyName = studyName;
        this.socialLink = socialLink;
        this.profileImage = "default.png";

        this.lastLoggedIn = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    /***
     *
     * @param email
     * @param password
     * @param name
     */
    public User(String email, String password, String name) {
        this.role = Role.USER.toString();
        this.email = email;
        this.password = password;
        this.name = name;
        this.profileImage = "default.png";

        this.lastLoggedIn = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    /***
     * Before presist creates the date the user is created.
     */
    @PrePersist
    public void beforePersist() {
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
