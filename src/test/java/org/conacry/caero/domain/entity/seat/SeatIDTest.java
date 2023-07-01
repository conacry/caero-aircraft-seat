package org.conacry.caero.domain.entity.seat;

import org.conacry.caero.domain.primitive.exception.CodedException;
import org.conacry.caero.util.generator.StringGenerator;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SeatIDTest {

    @Test
    void newID_ReturnSeatID() {
        var seatID = SeatID.newID();
        assertNotNull(seatID);
    }

    @Test
    void from_ValueStrIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> SeatID.from(null));
        assertEquals(SeatError.SEAT_ID_VALUE_IS_REQUIRED, ex.getCode());
        assertFalse(ex.getMessage().isEmpty());
    }

    @Test
    void from_ValueStrIsEmpty_ThrowEx() {
        var emptyValueStr = "";
        var ex = assertThrows(CodedException.class, () -> SeatID.from(emptyValueStr));
        assertEquals(SeatError.SEAT_ID_VALUE_IS_REQUIRED, ex.getCode());
        assertFalse(ex.getMessage().isEmpty());
    }

    @Test
    void from_IllegalFormatOfValueStr_ThrowEx() {
        var strWithIllegalFormat = StringGenerator.getRandomString();
        var ex = assertThrows(CodedException.class, () -> SeatID.from(strWithIllegalFormat));
        assertEquals(SeatError.ILLEGAL_SEAT_ID_VALUE, ex.getCode());
        assertFalse(ex.getMessage().isEmpty());
        assertNotNull(ex.getCause());
    }

    @Test
    void from_ValidValueStr_ReturnSeatID() {
        var validValueStr = UUID.randomUUID().toString();
        var seatID = SeatID.from(validValueStr);
        assertNotNull(seatID);
        assertEquals(validValueStr, seatID.getValue().toString());
    }
}