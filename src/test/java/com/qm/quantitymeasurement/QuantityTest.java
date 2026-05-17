package com.qm.quantitymeasurement;

import com.qm.quantitymeasurement.enums.Unit;
import com.qm.quantitymeasurement.model.Quantity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuantityTest {
    @Test
    void shouldReturnTrueForSameFeetValues() {

        Quantity firstFeet = new Quantity(1, Unit.FEET);
        Quantity secondFeet = new Quantity(1, Unit.FEET);

        assertEquals(firstFeet, secondFeet);
    }
}
