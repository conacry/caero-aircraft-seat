package org.conacry.caero.testdouble.entity;

import org.conacry.caero.domain.entity.aircraft.Aircraft;
import org.conacry.caero.domain.entity.aircraft.AircraftBuilder;
import org.conacry.caero.domain.entity.aircraft.AircraftID;
import org.conacry.caero.domain.entity.aircraft.AircraftStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public static Aircraft getFullActiveAircraft() {
        var model = ModelStub.getModel();
        var status = AircraftStatus.ACTIVE;

        return new AircraftBuilder().
                aircraftID(AircraftID.newID()).
                seats(SeatStub.getSeatList(5)).
                status(status).
                model(model).
                build();
    }

    public static Aircraft getFullNotActiveAircraft() {
        var model = ModelStub.getModel();
        var status = AircraftStatus.NOT_ACTIVE;

        return new AircraftBuilder().
                aircraftID(AircraftID.newID()).
                seats(SeatStub.getSeatList(5)).
                status(status).
                model(model).
                build();
    }

    public static List<Aircraft> getListFullAircraft(int count) {
        if (count <= 0) {
            return Collections.emptyList();
        }

        var aircraftList = new ArrayList<Aircraft>(count);

        for (int i = 0; i < count / 2; i++) {
            aircraftList.add(getFullActiveAircraft());
        }

        while (aircraftList.size() < count) {
            aircraftList.add(getFullNotActiveAircraft());
        }

        return aircraftList;
    }
}
