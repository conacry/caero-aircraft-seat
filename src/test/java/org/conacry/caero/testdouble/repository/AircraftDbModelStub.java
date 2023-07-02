package org.conacry.caero.testdouble.repository;

import org.conacry.caero.adapter.repository.model.AircraftDbModel;
import org.conacry.caero.domain.entity.aircraft.AircraftStatus;
import org.conacry.caero.testdouble.entity.AircraftStub;

import java.util.ArrayList;
import java.util.List;

public class AircraftDbModelStub {

    public static AircraftDbModel getAircraftDbModel() {
        var aircraft = AircraftStub.getFullActiveAircraft();

        var aircraftID = aircraft.getAircraftID();
        var model = aircraft.getModel().getValue();
        var seatsDdModels = SeatDbModelStub.getSeatDbModels(5, aircraftID);
        var status = aircraft.getStatus().name();

        var aircraftDbModel = new AircraftDbModel();
        aircraftDbModel.setId(aircraftID.getValue());
        aircraftDbModel.setModel(model);
        aircraftDbModel.setStatus(AircraftStatus.ACTIVE.toString());
        aircraftDbModel.setSeat(seatsDdModels);
        aircraftDbModel.setStatus(status);

        return aircraftDbModel;
    }

    public static List<AircraftDbModel> getAircraftDbModels(int count) {
        var aircraftDbModels = new ArrayList<AircraftDbModel>();
        for (int i = 0; i < count; i++){
            aircraftDbModels.add(getAircraftDbModel());
        }

        return aircraftDbModels;
    }

}
