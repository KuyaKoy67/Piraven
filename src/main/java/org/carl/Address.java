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

    public Address(int streetNo, String street, String city, Province province, String postalCode) {
        if (isPostalCodeValid(postalCode)) {
            this.streetNo = streetNo;
            this.street = street;
            this.city = city;
            this.province = province;
            this.postalCode = postalCode.toUpperCase();
        } else {
            this.streetNo = 0;
            this.street = null;
            this.city = null;
            this.province = null;
            this.postalCode = null;
        }

    }

    /**
     * checks if a postal code is valid or not. This postal code should have al length of 6
     * and must follow the format: CDCDCD (C is a character, D is a digit) and returns either
     * true or false
     * @param postalCode the postal code string
     * @return the boolean value true or false
     */
    private static boolean isPostalCodeValid(String postalCode) {
        if (postalCode.length() != 6) {
            return false;
        }

        for (int i = 0; i < postalCode.length(); i++) {
            if (i % 2 == 0) {
                char c = postalCode.charAt(i);
                if (!Character.isLetter(c)) {
                    return false;
                }
            } else {
                char c = postalCode.charAt(i);
                if (!(Character.isDigit(c))) {
                    return false;
                }
            }
        }

        return true;
    }

    public enum Province {
        QC, ON, MB, BC, AB, NB, NS, PE, NL
    }
}
