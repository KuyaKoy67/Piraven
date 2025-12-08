package org.carl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Address {
    private int streetNo;
    private String street;
    private String city;
    private Province province;
    private String postalCode;

    public enum Province {
        QC, ON, MB, BC, AB, NB, NS, PE, NL
    }

    private static boolean isPostalCodeValid(String postalCode) {
        if (postalCode.length() != 6) {
            return false;
        }

        String numbers = "0123456789";

        for (int i = 0; i < postalCode.length(); i++) {
            if (i % 2 == 0) {
                char c = postalCode.charAt(i);
                if (!Character.isLetter(c)) {
                    return false;
                }
            } else {
                char c = postalCode.charAt(i);
                if (!(c >= '0' && c <= '9')) {
                    return false;
                }
            }
        }

        return true;
    }
}
