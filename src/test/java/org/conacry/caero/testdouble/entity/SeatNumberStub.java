package org.conacry.caero.testdouble.entity;

import org.conacry.caero.domain.entity.seat.SeatNumber;
import org.conacry.caero.util.generator.StringGenerator;

public final class SeatNumberStub {

    public static SeatNumber getSeatNumber() {
        return SeatNumber.from(StringGenerator.getRandomString(2));
    }
}
