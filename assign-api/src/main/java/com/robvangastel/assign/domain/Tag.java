package com.robvangastel.assign.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Rob on 23-4-2017.
 */

@Entity
public class Tag implements Serializable {
    
    @Id
    @GeneratedValue
    @Column(name = "tag_id", nullable = false)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String title;
    
    public Tag (String title) {
        this.title = title;
    }

    Tag() {
    }
        
    /**
     * @return the id
     */
    public Long getId() {
        return id;
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
} 
