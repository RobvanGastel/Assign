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

    public Payload(Notification notification) {
        this.notification = notification;
    }

    public Payload(Data data) {
        this.data = data;
    }
}
