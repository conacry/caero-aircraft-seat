package org.conacry.caero.domain.entity.seat;

import org.conacry.caero.domain.entity.seat.SeatError;
import org.conacry.caero.domain.entity.seat.SeatNumber;
import org.conacry.caero.domain.primitive.exception.CodedException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SeatNumberTest {

    @Test
    void from_ValueIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> SeatNumber.from(null));
        assertEquals(SeatError.SEAT_NUMBER_VALUE_IS_REQUIRED, ex.getCode());
    }

    @Test
    void from_ValueIsEmpty_ThrowEx() {
        var emptyValue = "";
        var ex = assertThrows(CodedException.class, () -> SeatNumber.from(emptyValue));
        assertEquals(SeatError.SEAT_NUMBER_VALUE_IS_REQUIRED, ex.getCode());
    }

    @Test
    void from_IllegalValue_ThrowEx() {
        var illegalStrings = List.of("@b", "F#", "2!-", "%");

        for (var illegalValue : illegalStrings) {
            var ex = assertThrows(CodedException.class, () -> SeatNumber.from(illegalValue));
            assertEquals(SeatError.ILLEGAL_SEAT_NUMBER_VALUE, ex.getCode());
        }
    }

    @Test
    void from_ValidValue_ReturnSeatNumber() {
        var validValue = "22F";
        var seatNumber = SeatNumber.from(validValue);
        assertNotNull(seatNumber);
        assertEquals(validValue, seatNumber.getValue());
    }
}