package org.conacry.caero.testdouble.entity;

import org.conacry.caero.domain.entity.seat.FareCondition;
import org.conacry.caero.domain.entity.seat.Seat;
import org.conacry.caero.domain.entity.seat.SeatBuilder;
import org.conacry.caero.domain.entity.seat.SeatID;

import java.util.ArrayList;
import java.util.List;

public final class SeatStub {

    public static Seat getSeat() {
        var seatId = SeatID.newID();
        var fareCondition = FareCondition.FIRST_CLASS;
        var number = SeatNumberStub.getSeatNumber();

        return new SeatBuilder().
                seatID(seatId).
                number(number).
                fareCondition(fareCondition).
                build();
    }

    public static List<Seat> getSeatList(int count) {
        List<Seat> seats = new ArrayList<Seat>();
        for (int i = 0; i < count; i++) {
            seats.add(getSeat());
        }

        return seats;
    }
}
