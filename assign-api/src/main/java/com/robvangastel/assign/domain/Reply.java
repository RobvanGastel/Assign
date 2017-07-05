package com.robvangastel.assign.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * @author Rob van Gastel
 */

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class Reply implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    private User user;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Post post;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    @Column(nullable = false)
    private Timestamp dateCreated;

    private boolean helped;

    public Reply(User user, Post post) {
        this.user = user;
        this.post = post;
        this.helped = false;
    }

    /***
     * Before presist creates the date created of the response.
     */
    @PrePersist
    public void beforePersist(){
        this.dateCreated = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
    }

}
