package com.robvangastel.assign.api.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rob
 */

@Entity
public class Post implements Serializable {
    
    @Id
    @GeneratedValue
    @Column(name = "post_id", nullable = false)
    private Long id;
    
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.PERSIST)
    private Account account;
    
    @JsonManagedReference
    @ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "post_tag", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags = new ArrayList<Tag>();
        
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
