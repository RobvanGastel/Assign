package com.robvangastel.assign.firebase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
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

    public String operation;
    public String notification_key_name;
    public String notification_key;
    public List<String> registration_ids = new ArrayList<>();
}
