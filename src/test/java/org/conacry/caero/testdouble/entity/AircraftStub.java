package org.conacry.caero.testdouble.entity;

import org.conacry.caero.domain.entity.aircraft.Aircraft;
import org.conacry.caero.domain.entity.aircraft.AircraftBuilder;
import org.conacry.caero.domain.entity.aircraft.AircraftID;
import org.conacry.caero.domain.entity.aircraft.AircraftStatus;

import java.util.ArrayList;
import java.util.List;

public class AircraftStub {

    public static Aircraft getAircraftWithEmptySeats() {
        var model = ModelStub.getModel();

        return new AircraftBuilder().
                aircraftID(AircraftID.newID()).
                model(model).
                status(AircraftStatus.ACTIVE).
                build();
    }

    public static Aircraft getFullAircraft() {
        var model = ModelStub.getModel();

        return new AircraftBuilder().
                aircraftID(AircraftID.newID()).
                seats(SeatStub.getSeatList(5)).
                model(model).
                status(AircraftStatus.ACTIVE).
                build();
    }

    public static List<Aircraft> getListFullAircraft(int count) {
        var aircraftList = new ArrayList<Aircraft>();

        for (int i = 0; i< count; i++) {
            aircraftList.add(getFullAircraft());
        }

        return aircraftList;
    }
}
