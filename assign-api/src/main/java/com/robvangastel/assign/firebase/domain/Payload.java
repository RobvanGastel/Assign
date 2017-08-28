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

    public Payload(Notification notification, String to) {
        this.notification = notification;
        this.to = to;
    }

    public Payload(Data data, String to) {
        this.data = data;
        this.to = to;
    }
}
