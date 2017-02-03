package com.robvangastel.assign.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Rob
 */

@Entity
public class Post implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.PERSIST)
    private Account account;
    
    @JsonManagedReference
    @ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "post_tag", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags = new ArrayList<Tag>();
    
    @Column(nullable = false, unique = true)
    private String title;
    
    @Column(nullable = false, unique = true)
    private String description;
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    @Column(nullable = false, unique = false)
    private Timestamp dateCreated;   
    
    private boolean done;
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Timestamp dateDone;
    
    public Post(String title, String description) {
        this.title = title;
        this.description = description;
        
        this.done = false; 
        
        this.dateCreated = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
    }
    
    Post() {}

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the done
     */
    public boolean isDone() {
        return done;
    }

    /**
     * @param done the done to set
     */
    public void setDone(boolean done) {
        this.done = done;
        this.dateDone = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the dateCreated
     */
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    /**
     * @return the account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * @return the tags
     */
    public List<Tag> getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
