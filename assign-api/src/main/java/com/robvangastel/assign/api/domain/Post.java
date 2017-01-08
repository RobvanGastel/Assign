package com.robvangastel.assign.api.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Rob
 */

@Entity
public class Post implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    private Long account_id;
    
    private boolean done;
    private String title;
    private String description;
    private String dateCreated;
    private Date dateDone;
    
    public Post(Long account_id, String title, String description) {
        this.account_id = account_id;
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
     * @return the account_id
     */
    public Long getAccount_id() {
        return account_id;
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
}
