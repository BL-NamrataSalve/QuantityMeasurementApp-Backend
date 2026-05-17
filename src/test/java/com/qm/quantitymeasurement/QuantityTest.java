package com.qm.quantitymeasurement;

import com.qm.quantitymeasurement.enums.LengthUnit;
import com.qm.quantitymeasurement.enums.WeightUnit;
import com.qm.quantitymeasurement.model.Quantity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class QuantityTest {
    @Test
    void shouldReturnTrueForSameFeetValues() {

        Quantity firstFeet = new Quantity(1, LengthUnit.FEET);
        Quantity secondFeet = new Quantity(1, LengthUnit.FEET);

        assertEquals(firstFeet, secondFeet);
    }

    @Test
    void shouldReturnTrueForFeetAndInchEquality() {

        Quantity feet = new Quantity(1, LengthUnit.FEET);
        Quantity inch = new Quantity(12, LengthUnit.INCH);

        assertEquals(feet, inch);
    }

    @Test
    void shouldReturnTrueForFeetAndYardEquality() {

        Quantity feet = new Quantity(3, LengthUnit.FEET);
        Quantity yard = new Quantity(1, LengthUnit.YARD);

        assertEquals(feet, yard);
    }

    @Test
    void shouldReturnTrueForInchAndCentimeterEquality() {

        Quantity inch = new Quantity(1, LengthUnit.INCH);
        Quantity centimeter = new Quantity(2.54, LengthUnit.CENTIMETER);

        assertEquals(inch, centimeter);
    }

    @Test
    void shouldReturnFalseForDifferentValues() {

        Quantity feet = new Quantity(1, LengthUnit.FEET);
        Quantity inch = new Quantity(11, LengthUnit.INCH);

        assertNotEquals(feet, inch);
    }

    @Test
    void shouldConvertFeetToInch() {

        Quantity feet = new Quantity(1, LengthUnit.FEET);

        Quantity expected =
                new Quantity(12, LengthUnit.INCH);

        assertEquals(expected,
                feet.convertTo(LengthUnit.INCH));
    }

    @Test
    void shouldConvertYardToCentimeter() {

        Quantity yard =
                new Quantity(1, LengthUnit.YARD);

        Quantity expected =
                new Quantity(91.44,
                        LengthUnit.CENTIMETER);

        assertEquals(expected,
                yard.convertTo(LengthUnit.CENTIMETER));
    }

    @Test
    void shouldAddFeetAndInchValues() {

        Quantity feet =
                new Quantity(1, LengthUnit.FEET);

        Quantity inch =
                new Quantity(2, LengthUnit.INCH);

        Quantity expected =
                new Quantity(35.56,
                        LengthUnit.CENTIMETER);

        assertEquals(expected,
                feet.add(inch));
    }

    @Test
    void shouldAddTwoFeetValues() {

        Quantity firstFeet =
                new Quantity(1, LengthUnit.FEET);

        Quantity secondFeet =
                new Quantity(1, LengthUnit.FEET);

        Quantity expected =
                new Quantity(60.96,
                        LengthUnit.CENTIMETER);

        assertEquals(expected,
                firstFeet.add(secondFeet));
    }

    @Test
    void shouldReturnSameResultForCommutativeAddition() {

        Quantity feet =
                new Quantity(1, LengthUnit.FEET);

        Quantity inch =
                new Quantity(2, LengthUnit.INCH);

        assertEquals(
                feet.add(inch),
                inch.add(feet)
        );
    }

    @Test
    void shouldThrowExceptionForNegativeValue() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity(-1, LengthUnit.FEET)
        );
    }

    @Test
    void shouldThrowExceptionForNullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity(1, null)
        );
    }

    @Test
    void shouldAddFeetAndInchInTargetUnitInch() {

        Quantity feet =
                new Quantity(1, LengthUnit.FEET);

        Quantity inch =
                new Quantity(2, LengthUnit.INCH);

        Quantity expected =
                new Quantity(14, LengthUnit.INCH);

        assertEquals(
                expected,
                feet.add(inch, LengthUnit.INCH)
        );
    }

    @Test
    void shouldAddFeetAndInchInTargetUnitFeet() {

        Quantity feet =
                new Quantity(1, LengthUnit.FEET);

        Quantity inch =
                new Quantity(2, LengthUnit.INCH);

        Quantity expected =
                new Quantity(1.17, LengthUnit.FEET);

        assertEquals(
                expected,
                feet.add(inch, LengthUnit.FEET)
        );
    }

    @Test
    void shouldAddFeetAndYardInTargetUnitYard() {

        Quantity feet =
                new Quantity(3, LengthUnit.FEET);

        Quantity yard =
                new Quantity(1, LengthUnit.YARD);

        Quantity expected =
                new Quantity(2, LengthUnit.YARD);

        assertEquals(
                expected,
                feet.add(yard, LengthUnit.YARD)
        );
    }

    @Test
    void shouldReturnTrueForGramAndKilogramEquality() {

        Quantity gram =
                new Quantity(1000,
                        WeightUnit.GRAM);

        Quantity kilogram =
                new Quantity(1,
                        WeightUnit.KILOGRAM);

        assertEquals(gram, kilogram);
    }

    @Test
    void shouldAddGramAndKilogram() {

        Quantity gram =
                new Quantity(500,
                        WeightUnit.GRAM);

        Quantity kilogram =
                new Quantity(1,
                        WeightUnit.KILOGRAM);

        Quantity expected =
                new Quantity(1500,
                        WeightUnit.GRAM);

        assertEquals(
                expected,
                gram.add(kilogram,
                        WeightUnit.GRAM)
        );
    }

    @Test
    void shouldThrowExceptionForDifferentMeasurementCategories() {

        Quantity length =
                new Quantity(1,
                        LengthUnit.FEET);

        Quantity weight =
                new Quantity(1,
                        WeightUnit.KILOGRAM);

        assertThrows(
                IllegalArgumentException.class,
                () -> length.add(weight)
        );
    }

}
