package com.qm.quantitymeasurement;

import com.qm.quantitymeasurement.enums.Unit;
import com.qm.quantitymeasurement.model.Quantity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class QuantityTest {
    @Test
    void shouldReturnTrueForSameFeetValues() {

        Quantity firstFeet = new Quantity(1, Unit.FEET);
        Quantity secondFeet = new Quantity(1, Unit.FEET);

        assertEquals(firstFeet, secondFeet);
    }

    @Test
    void shouldReturnTrueForFeetAndInchEquality() {

        Quantity feet = new Quantity(1, Unit.FEET);
        Quantity inch = new Quantity(12, Unit.INCH);

        assertEquals(feet, inch);
    }

    @Test
    void shouldReturnFalseForDifferentValues() {

        Quantity feet = new Quantity(1, Unit.FEET);
        Quantity inch = new Quantity(11, Unit.INCH);

        assertNotEquals(feet, inch);
    }

}
