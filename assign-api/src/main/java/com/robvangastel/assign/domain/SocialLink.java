package com.robvangastel.assign.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Rob van Gastel
 */

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SocialLink implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String twitter;
    private String facebook;
    private String phonenumber;

    public SocialLink(String twitter, String facebook, String phonenumber) {
        this.twitter = twitter;
        this.facebook = facebook;
        this.phonenumber = phonenumber;
    }
}
