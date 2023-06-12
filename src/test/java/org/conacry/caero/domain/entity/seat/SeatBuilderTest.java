package org.conacry.caero.domain.entity.seat;

import org.conacry.caero.domain.primitive.exception.CodedException;
import org.conacry.caero.testdouble.entity.SeatNumberStub;
import org.junit.jupiter.api.Test;

import static org.conacry.caero.domain.entity.seat.FareCondition.ECONOMY;
import static org.junit.jupiter.api.Assertions.*;

class SeatBuilderTest {

    @Test
    void build_NoGivenAircraftID_ThrowEx() {
        var fareCondition = FareCondition.BUSINESS;
        var number = SeatNumberStub.getSeatNumber();
        var builder = new SeatBuilder();

        var ex = assertThrows(CodedException.class, () -> builder.
                fareCondition(fareCondition).
                number(number).
                build()
        );
        assertEquals(SeatError.SEAT_ID_IS_REQUIRED, ex.getCode());
    }

    @Test
    void build_NoGivenAircraftNumber_ThrowEx() {
        var fareCondition = FareCondition.BUSINESS;
        var seatId = SeatID.newID();
        var builder = new SeatBuilder();

        var ex = assertThrows(CodedException.class, () ->builder.
                fareCondition(fareCondition).
                seatID(seatId).
                build()
        );
        assertEquals(SeatError.SEAT_NUMBER_IS_REQUIRED, ex.getCode());
    }

    @Test
    void build_NoGivenAircraftFairCondition_ThrowEx() {
        var seatId = SeatID.newID();
        var number = SeatNumberStub.getSeatNumber();
        var builder = new SeatBuilder();

        var ex = assertThrows(CodedException.class, () ->builder.
                seatID(seatId).
                number(number).
                build()
        );
        assertEquals(SeatError.SEAT_FARE_CONDITION_IS_REQUIRED, ex.getCode());
    }
    @Test
    void from_SeatBuilderAllParams_Return() {
        var seatId = SeatID.newID();
        var fareCondition = FareCondition.FIRST_CLASS;
        var number = SeatNumberStub.getSeatNumber();

        var seat = new SeatBuilder().
                seatID(seatId).
                number(number).
                fareCondition(fareCondition).
                build();
        assertNotNull(seat);
        assertEquals(seatId, seat.getSeatID());
    }
}
