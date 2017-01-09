package com.robvangastel.assign.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Tag> tags;
        
    private boolean done;
    private String title;
    private String description;
    private String dateCreated;
    private Date dateDone;
    
    public Post(String title, String description) {
        this.title = title;
        this.description = description;
        this.dateCreated = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
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
    public String getDateCreated() {
        return dateCreated;
    }

    /**
     * @return the dateDone
     */
    public Date getDateDone() {
        return dateDone;
    }

    /**
     * @param dateDone the dateDone to set
     */
    public void setDateDone(Date dateDone) {
        this.dateDone = dateDone;
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
