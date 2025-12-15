package org.carl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    @DisplayName("toTitleCase(): computer science and math -> Computer Science And Math")
    void toTitleCase1() {
        String name = "computer science and math";
        String expected = "Computer Science And Math";
        String actual = Util.toTitleCase(name);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("toTitleCase(): COMPUTER SCIENCE AND MATH -> Computer Science And Math")
    void toTitleCase2() {
        String name = "COMPUTER SCIENCE AND MATH";
        String expected = "Computer Science And Math";
        String actual = Util.toTitleCase(name);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("toTitleCase(): cOMPUTER sCIENCE aND mATH -> Computer Science And Math")
    void toTitleCase3() {
        String name = "cOMPUTER sCIENCE aND mATH";
        String expected = "Computer Science And Math";
        String actual = Util.toTitleCase(name);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("toTitleCase(): -> ")
    void toTitleCase4() {
        String name = "";
        String expected = "";
        String actual = Util.toTitleCase(name);
        Assertions.assertEquals(expected, actual);
    }
}
