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
    void shouldReturnTrueForFeetAndYardEquality() {

        Quantity feet = new Quantity(3, Unit.FEET);
        Quantity yard = new Quantity(1, Unit.YARD);

        assertEquals(feet, yard);
    }

    @Test
    void shouldReturnTrueForInchAndCentimeterEquality() {

        Quantity inch = new Quantity(1, Unit.INCH);
        Quantity centimeter = new Quantity(2.54, Unit.CENTIMETER);

        assertEquals(inch, centimeter);
    }

    @Test
    void shouldReturnFalseForDifferentValues() {

        Quantity feet = new Quantity(1, Unit.FEET);
        Quantity inch = new Quantity(11, Unit.INCH);

        assertNotEquals(feet, inch);
    }

    @Test
    void shouldConvertFeetToInch() {

        Quantity feet = new Quantity(1, Unit.FEET);

        Quantity expected =
                new Quantity(12, Unit.INCH);

        assertEquals(expected,
                feet.convertTo(Unit.INCH));
    }

    @Test
    void shouldConvertYardToCentimeter() {

        Quantity yard =
                new Quantity(1, Unit.YARD);

        Quantity expected =
                new Quantity(91.44,
                        Unit.CENTIMETER);

        assertEquals(expected,
                yard.convertTo(Unit.CENTIMETER));
    }

}
