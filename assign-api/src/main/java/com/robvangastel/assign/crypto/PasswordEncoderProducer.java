package com.robvangastel.assign.crypto;

import com.robvangastel.assign.crypto.bcrypt.BCryptPasswordEncoder;
import com.robvangastel.assign.crypto.plain.PlainPasswordEncoder;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 *
 * @author Rob van Gastel
 */
 
@Dependent
public class PasswordEncoderProducer {

    @Produces
    @Crypto
    public PasswordEncoder passwordEncoder(InjectionPoint ip) {
        Crypto crypto = ip.getAnnotated().getAnnotation(Crypto.class);
        Crypto.Type type = crypto.value();
        PasswordEncoder encoder;
        switch (type) {
            case PLAIN:
                encoder = new PlainPasswordEncoder();
                break;
            case BCRYPT:
                encoder = new BCryptPasswordEncoder();
                break;
            default:
                encoder = new PlainPasswordEncoder();
                break;
        }

        return encoder;
    }

}
