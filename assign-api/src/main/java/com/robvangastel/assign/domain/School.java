package com.robvangastel.assign.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Rob van Gastel
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class School implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Study> studies;

    private String name;
    private String schoolCode;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    @Column(nullable = false)
    private Timestamp dateCreated;

    public School(String name) {
        this.name = name;
    }
    /***
     * Before presist creates the date created of the response.
     */
    @PrePersist
    public void beforePersist(){
        this.dateCreated = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    public void addStudy(Study study) {
        studies.add(study);
    }
}
