package org.conacry.caero.testdouble.entity;

import org.conacry.caero.domain.entity.aircraft.*;

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

    public static List<Aircraft> getAircraftList(int count) {
        if (count == 0) {
            return Collections.emptyList();
        }

        var aircraftList = new ArrayList<Aircraft>();
        for (int i = 0; i < count; i++) {
            var model = Model.from("Model");
            var aircraftID = AircraftID.newID();
            var aircraft = new AircraftBuilder().
                    aircraftID(aircraftID).
                    model(model).
                    status(AircraftStatus.ACTIVE).
                    build();
            aircraftList.add(aircraft);
        }

        return aircraftList;
    }

}
