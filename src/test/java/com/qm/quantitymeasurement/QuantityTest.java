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

        Quantity<LengthUnit> firstFeet =
                new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> secondFeet =
                new Quantity<>(1, LengthUnit.FEET);

        assertEquals(firstFeet, secondFeet);
    }

    @Test
    void shouldReturnTrueForFeetAndInchEquality() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> inch =
                new Quantity<>(12, LengthUnit.INCH);

        assertEquals(feet, inch);
    }

    @Test
    void shouldReturnTrueForFeetAndYardEquality() {

        Quantity<LengthUnit> feet =
                new Quantity<>(3, LengthUnit.FEET);
        Quantity<LengthUnit> yard =
                new Quantity<>(1, LengthUnit.YARD);

        assertEquals(feet, yard);
    }

    @Test
    void shouldReturnTrueForInchAndCentimeterEquality() {

        Quantity<LengthUnit> inch =
                new Quantity<>(1, LengthUnit.INCH);
        Quantity<LengthUnit> centimeter =
                new Quantity<>(2.54, LengthUnit.CENTIMETER);

        assertEquals(inch, centimeter);
    }

    @Test
    void shouldReturnFalseForDifferentValues() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> inch =
                new Quantity<>(11, LengthUnit.INCH);

        assertNotEquals(feet, inch);
    }

    @Test
    void shouldConvertFeetToInch() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1, LengthUnit.FEET);

        Quantity<LengthUnit> expected =
                new Quantity<>(12, LengthUnit.INCH);

        assertEquals(expected,
                feet.convertTo(LengthUnit.INCH));
    }

    @Test
    void shouldConvertYardToCentimeter() {

        Quantity<LengthUnit> yard =
                new Quantity<>(1, LengthUnit.YARD);

        Quantity<LengthUnit> expected =
                new Quantity<>(91.44,
                        LengthUnit.CENTIMETER);

        assertEquals(expected,
                yard.convertTo(LengthUnit.CENTIMETER));
    }

    @Test
    void shouldAddFeetAndInchValues() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1, LengthUnit.FEET);

        Quantity<LengthUnit> inch =
                new Quantity<>(2, LengthUnit.INCH);

        Quantity<LengthUnit> expected =
                new Quantity<>(35.56,
                        LengthUnit.CENTIMETER);

        assertEquals(expected,
                feet.add(inch));
    }

    @Test
    void shouldAddTwoFeetValues() {

        Quantity<LengthUnit> firstFeet =
                new Quantity<>(1, LengthUnit.FEET);

        Quantity<LengthUnit> secondFeet =
                new Quantity<>(1, LengthUnit.FEET);

        Quantity<LengthUnit> expected =
                new Quantity<>(60.96,
                        LengthUnit.CENTIMETER);

        assertEquals(expected,
                firstFeet.add(secondFeet));
    }

    @Test
    void shouldReturnSameResultForCommutativeAddition() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1, LengthUnit.FEET);

        Quantity<LengthUnit> inch =
                new Quantity<>(2, LengthUnit.INCH);

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

        Quantity<LengthUnit> feet =
                new Quantity<>(1, LengthUnit.FEET);

        Quantity<LengthUnit> inch =
                new Quantity<>(2, LengthUnit.INCH);

        Quantity<LengthUnit> expected =
                new Quantity<>(14, LengthUnit.INCH);

        assertEquals(
                expected,
                feet.add(inch, LengthUnit.INCH)
        );
    }

    @Test
    void shouldAddFeetAndInchInTargetUnitFeet() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1, LengthUnit.FEET);

        Quantity<LengthUnit> inch =
                new Quantity<>(2, LengthUnit.INCH);

        Quantity<LengthUnit> expected =
                new Quantity<>(1.17, LengthUnit.FEET);

        assertEquals(
                expected,
                feet.add(inch, LengthUnit.FEET)
        );
    }

    @Test
    void shouldAddFeetAndYardInTargetUnitYard() {

        Quantity<LengthUnit> feet =
                new Quantity<>(3, LengthUnit.FEET);

        Quantity<LengthUnit> yard =
                new Quantity<>(1, LengthUnit.YARD);

        Quantity<LengthUnit> expected =
                new Quantity<>(2, LengthUnit.YARD);

        assertEquals(
                expected,
                feet.add(yard, LengthUnit.YARD)
        );
    }

    @Test
    void shouldReturnTrueForGramAndKilogramEquality() {

        Quantity<WeightUnit> gram =
                new Quantity<>(1000,
                        WeightUnit.GRAM);

        Quantity<WeightUnit> kilogram =
                new Quantity<>(1,
                        WeightUnit.KILOGRAM);

        assertEquals(gram, kilogram);
    }

    @Test
    void shouldAddGramAndKilogram() {

        Quantity<WeightUnit> gram =
                new Quantity<>(500,
                        WeightUnit.GRAM);

        Quantity<WeightUnit> kilogram =
                new Quantity<>(1,
                        WeightUnit.KILOGRAM);

        Quantity<WeightUnit> expected =
                new Quantity<>(1500,
                        WeightUnit.GRAM);

        assertEquals(
                expected,
                gram.add(kilogram,
                        WeightUnit.GRAM)
        );
    }

}
