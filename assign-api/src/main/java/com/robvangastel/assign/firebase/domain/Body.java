package com.robvangastel.assign.firebase.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rob van Gastel
 */

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Body {

    private String operation;
    private String notification_key_name;
    private String notification_key;
    private List<String> registration_ids = new ArrayList<>();
}
