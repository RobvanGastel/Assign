package com.robvangastel.assign.firebase.domain;

import lombok.*;

/**
 * @author Rob van Gastel
 */
@lombok.Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Payload {

    private Notification notification;
    private Data data;
    private String to;
    private Priority priority;

    public Payload(Notification notification, String to, Priority priority) {
        this.notification = notification;
        this.priority = priority;
        this.to = to;
    }

    public Payload(Data data, String to, Priority priority) {
        this.data = data;
        this.priority = priority;
        this.to = to;
    }
}
