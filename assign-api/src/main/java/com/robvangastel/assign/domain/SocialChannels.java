package com.robvangastel.assign.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Rob on 23-4-2017.
 */

@Entity
public class SocialChannels implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String TwitterId;
    private String FacebookId;
    
    public SocialChannels(String FacebookId) {
        this.FacebookId = FacebookId;
    }
    
    SocialChannels() {}

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the TwitterId
     */
    public String getTwitterId() {
        return TwitterId;
    }

    /**
     * @param TwitterId the TwitterId to set
     */
    public void setTwitterId(String TwitterId) {
        this.TwitterId = TwitterId;
    }

    /**
     * @return the FacebookId
     */
    public String getFacebookId() {
        return FacebookId;
    }

    /**
     * @param FacebookId the FacebookId to set
     */
    public void setFacebookId(String FacebookId) {
        this.FacebookId = FacebookId;
    }
}
