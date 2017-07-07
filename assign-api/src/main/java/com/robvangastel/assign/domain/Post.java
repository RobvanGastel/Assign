package com.robvangastel.assign.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.robvangastel.assign.domain.serializers.PostSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Rob van Gastel
 */

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@JsonSerialize(using = PostSerializer.class)
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Hashtag parse regex
     */
    private static final String HASHTAG_REGEX = "^#\\w+([.]?\\w+)*|\\s#\\w+([.]?\\w+)*";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    private User user;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Reply> replies;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<String> tags = new ArrayList<>();

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Timestamp dateCreated;

    private boolean done;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Timestamp dateDone;

    /***
     *
     * @param user
     * @param title
     * @param description
     */
    public Post(User user, String title, String description) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.done = false;

        List<String> hashtags = new ArrayList<>();

        Pattern HASHTAG_PATTERN = Pattern.compile(HASHTAG_REGEX);
        Matcher matcher = HASHTAG_PATTERN.matcher(description);
        while (matcher.find()) {
            hashtags.add(matcher.group().replace(" ", "").replace("#", ""));
        }
        this.tags = hashtags;
    }

    @PrePersist
    public void beforePersist(){
        this.dateCreated = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    public void setDone(boolean done) {
        if(done) {
            this.done = true;
            this.dateDone = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
        } else {
            this.done = false;
            this.dateDone = null;
        }
    }
}
