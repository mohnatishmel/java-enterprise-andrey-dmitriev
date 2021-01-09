package by.itacademy.taskcore.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DaoDataValidatorTest {

    DaoDataValidator daoDataValidator;
    String validData;
    String validData1;
    String validData2;
    String validData3;
    String invalidData;
    String invalidData1;
    String invalidData2;
    String invalidData3;

    @BeforeEach
    void init() {
        daoDataValidator = new DaoDataValidator();

        validData = "1 ms";
        validData1 = "2 kmh";
        validData2 = "3 mph";
        validData3 = "1 kn";

        invalidData = "123 sdfnhlkjhn";
        invalidData1 = "nhf/fgbrr3 ms";
        invalidData2 = "nhf / fg 3 brr\n ms";
        invalidData3 = "/,. ms";
    }

    @Test
    void validatePositive() {
        assertAll(
                () -> assertTrue(daoDataValidator.validate(validData)),
                () -> assertTrue(daoDataValidator.validate(validData1)),
                () -> assertTrue(daoDataValidator.validate(validData2)),
                () -> assertTrue(daoDataValidator.validate(validData3))
        );
    }

    @Test
    void validateNegative() {
        assertAll(
                () -> assertFalse(daoDataValidator.validate(invalidData)),
                () -> assertFalse(daoDataValidator.validate(invalidData1)),
                () -> assertFalse(daoDataValidator.validate(invalidData2)),
                () -> assertFalse(daoDataValidator.validate(invalidData3))
        );
    }
}