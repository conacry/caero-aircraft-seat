package org.conacry.caero.testdouble.entity;

import org.conacry.caero.domain.entity.aircraft.Aircraft;
import org.conacry.caero.domain.entity.aircraft.AircraftBuilder;
import org.conacry.caero.domain.entity.aircraft.AircraftID;
import org.conacry.caero.domain.entity.aircraft.Model;

public class AircraftStub {

    public static Aircraft getAircraftWithEmptySeats() {
        var model = ModelStub.getModel();

        return new AircraftBuilder().
                aircraftID(AircraftID.newID()).
                model(model).
                build();
    }

    public static Aircraft getAircraftAllParameters() {
        var model = ModelStub.getModel();

        return new AircraftBuilder().
                aircraftID(AircraftID.newID()).
                seats(SeatStub.getSeatList(5)).
                model(model).
                build();
    }

}
