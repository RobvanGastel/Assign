package com.robvangastel.assign.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Rob van Gastel
 */

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Study implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    private School school;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<User> studenten;

    public Study(School school, String name) {
        this.school = school;
        this.name = name;
    }
}
