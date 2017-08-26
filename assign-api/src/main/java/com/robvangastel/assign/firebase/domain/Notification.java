package com.robvangastel.assign.firebase.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * @author Rob van Gastel
 */

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    private String title;
    private String body;
    private String icon;
}
