package com.robvangastel.assign.api.domain;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Rob
 */
@Entity
public class SocialChannels implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
        
    @OneToOne(cascade = CascadeType.PERSIST)
    private Account account;
    
    private String TwitterId;
    private String FacebookId;
    
    public SocialChannels(Account account, String TwitterId, String FacebookId) {
        this.account = account;
        this.TwitterId = TwitterId;
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
}
