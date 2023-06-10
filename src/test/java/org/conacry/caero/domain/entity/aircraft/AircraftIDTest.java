package org.conacry.caero.domain.entity.aircraft;

import org.conacry.caero.domain.primitive.exception.CodedException;
import org.conacry.caero.util.StringGenerator;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AircraftIDTest {

    @Test
    void newID_ReturnAircraftID() {
        var aircraftID = AircraftID.newID();
        assertNotNull(aircraftID);
    }

    @Test
    void from_ValueStrIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> AircraftID.from(null));
        assertEquals(AircraftError.AIRCRAFT_ID_VALUE_IS_REQUIRED, ex.getCode());
        assertFalse(ex.getMessage().isEmpty());
    }

    @Test
    void from_ValueStrIsEmpty_ThrowEx() {
        var emptyValueStr = "";
        var ex = assertThrows(CodedException.class, () -> AircraftID.from(emptyValueStr));
        assertEquals(AircraftError.AIRCRAFT_ID_VALUE_IS_REQUIRED, ex.getCode());
        assertFalse(ex.getMessage().isEmpty());
    }

    @Test
    void from_IllegalFormatOfValueStr_ThrowEx() {
        var strWithIllegalFormat = StringGenerator.getRandomString();
        var ex = assertThrows(CodedException.class, () -> AircraftID.from(strWithIllegalFormat));
        assertEquals(AircraftError.ILLEGAL_AIRCRAFT_ID_VALUE, ex.getCode());
        assertFalse(ex.getMessage().isEmpty());
        assertNotNull(ex.getCause());
    }

    @Test
    void from_ValidValueStr_ReturnAircraftID() {
        var validValueStr = UUID.randomUUID().toString();
        var aircraftID = AircraftID.from(validValueStr);
        assertNotNull(aircraftID);
        assertEquals(validValueStr, aircraftID.getValue().toString());
    }
}