package com.robvangastel.assign.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
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
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.PERSIST)
    private User user;
    
    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "post_tag", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags = new ArrayList<Tag>();
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String description;
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Timestamp dateCreated;   
    
    private boolean done;
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Timestamp dateDone;

    /***
     *
     * @param user
     * @param title
     * @param description
     */
    public Post(User user, String title, String description) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.done = false;
    }

    /***
     *
     * @param user
     * @param title
     * @param description
     * @param tags
     */
    public Post(User user, String title, String description, List<Tag> tags) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.done = false;
    }

    @PrePersist
    public void beforePersist(){
        this.dateCreated = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
    }
}
