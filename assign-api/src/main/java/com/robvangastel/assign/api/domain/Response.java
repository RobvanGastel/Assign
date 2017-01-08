package com.robvangastel.assign.api.domain;

import java.io.Serializable;
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
public class Response implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private Long user_id;
    private Long post_id;
    private String dateCreated;
    
    public Response(Long user_id, Long post_id) {
        this.user_id = user_id;
        this.post_id = post_id;
        this.dateCreated = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

    }
    
    Response() {}

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the user_id
     */
    public Long getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    /**
     * @return the post_id
     */
    public Long getPost_id() {
        return post_id;
    }

    /**
     * @param post_id the post_id to set
     */
    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

    /**
     * @return the dateCreated
     */
    public String getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}
