package org.conacry.caero.testdouble.entity;

import org.conacry.caero.domain.entity.aircraft.*;

public class AircraftStub {

    public static Aircraft getAircraftWithEmptySeats() {
        var model = ModelStub.getModel();
        var status = AircraftStatus.ACTIVE;

        return new AircraftBuilder().
                aircraftID(AircraftID.newID()).
                model(model).
                status(status).
                build();
    }

    public static Aircraft getAircraftAllParameters() {
        var model = ModelStub.getModel();
        var status = AircraftStatus.ACTIVE;

        return new AircraftBuilder().
                aircraftID(AircraftID.newID()).
                seats(SeatStub.getSeatList(5)).
                model(model).
                status(status).
                build();
    }

}
