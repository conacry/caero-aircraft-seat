package org.conacry.caero.testdouble.repository;

import org.conacry.caero.adapter.repository.model.SeatDbModel;
import org.conacry.caero.domain.entity.aircraft.AircraftID;
import org.conacry.caero.testdouble.entity.SeatStub;

import java.util.ArrayList;
import java.util.List;

public class SeatDbModelStub {

    public static SeatDbModel getSeatDbModel(AircraftID aircraftID) {
        var seat = SeatStub.getSeat();

        var id = seat.getSeatID().getValue();
        var number = seat.getNumber().getValue();
        var fareCondition = seat.getFareCondition().toString();

        var seatDbModel = new SeatDbModel();

        seatDbModel.setId(id);
        seatDbModel.setNumber(number);
        seatDbModel.setFareCondition(fareCondition);
        seatDbModel.setAircraftID(aircraftID.getValue());

        return seatDbModel;
    }

    public static List<SeatDbModel> getSeatDbModels(int count, AircraftID aircraftID) {
        var seatDbModels = new ArrayList<SeatDbModel>();

        for (int i = 0; i < count; i++){
            seatDbModels.add(getSeatDbModel(aircraftID));
        }

        return seatDbModels;
    }
}
