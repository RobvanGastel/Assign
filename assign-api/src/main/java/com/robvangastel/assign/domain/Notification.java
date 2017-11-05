package com.robvangastel.assign.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.robvangastel.assign.domain.serializers.NotificationSerializer;
import com.robvangastel.assign.domain.serializers.PostSerializer;
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
@JsonSerialize(using = NotificationSerializer.class)
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    private User user;

    @OneToOne(cascade = CascadeType.PERSIST)
    private User sender;

    @Column(nullable = false)
    private String title;

    private String body;
    private String icon;

    private Boolean readNotification;
    private Long postId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Timestamp dateCreated;

    public Notification(User user, User sender, Long postId, String title, String body) {
        this.user = user;
        this.sender = sender;
        this.postId = postId;
        this.title = title;
        this.body = body;
        this.readNotification = false;
    }

    public Notification(User user, String title) {
        this.user = user;
        this.title = title;
        this.readNotification = false;
    }

    @PrePersist
    public void beforePersist() {
        this.dateCreated = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
    }
}
