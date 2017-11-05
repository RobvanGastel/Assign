package com.robvangastel.assign.firebase.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
    private Boolean done;

    public Notification(String title, String body) {
        this.title = title;
        this.body = body;
        this.done = false;
    }

    public Notification(String title, String body, Boolean done) {
        this.title = title;
        this.body = body;
        this.done = done;
    }
}
