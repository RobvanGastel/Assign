package com.robvangastel.assign.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    private User user;

    @Column(nullable = false)
    private String title;

    private String body;
    private String icon;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Timestamp dateCreated;

    public Notification(User user, String title, String body) {
        this.user = user;
        this.title = title;
        this.body = body;
    }

    public Notification(User user, String title) {
        this.user = user;
        this.title = title;
    }

    @PrePersist
    public void beforePersist() {
        this.dateCreated = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
    }

}
