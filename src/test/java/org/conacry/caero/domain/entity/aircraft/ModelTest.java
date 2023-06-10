package org.conacry.caero.domain.entity.aircraft;

import org.conacry.caero.domain.primitive.exception.CodedException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    @Test
    void from_ValueIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> Model.from(null));
        assertEquals(AircraftError.MODEL_VALUE_IS_REQUIRED, ex.getCode());
    }

    @Test
    void from_ValueIsEmpty_ThrowEx() {
        var emptyValue = "";
        var ex = assertThrows(CodedException.class, () -> Model.from(emptyValue));
        assertEquals(AircraftError.MODEL_VALUE_IS_REQUIRED, ex.getCode());
    }

    @Test
    void from_IllegalValue_ThrowEx() {
        var illegalStrings = List.of("@b", "boeing#4", "airbus!-", "%");

        for (var illegalValue : illegalStrings) {
            var ex = assertThrows(CodedException.class, () -> Model.from(illegalValue));
            assertEquals(AircraftError.ILLEGAL_MODEL_VALUE, ex.getCode());
        }
    }

    @Test
    void from_ValidValue_ReturnModel() {
        var validValue = "Boeing-747";
        var model = Model.from(validValue);
        assertNotNull(model);
        assertEquals(validValue, model.getValue());
    }
}