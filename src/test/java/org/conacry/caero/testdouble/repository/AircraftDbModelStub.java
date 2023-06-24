package org.conacry.caero.testdouble.repository;

import org.conacry.caero.adapter.repository.model.AircraftDbModel;
import org.conacry.caero.domain.entity.aircraft.AircraftID;
import org.conacry.caero.testdouble.entity.AircraftStub;

public class AircraftDbModelStub {

    public static AircraftDbModel getAircraftDbModel() {
        var aircraft = AircraftStub.getAircraftAllParameters();

        var aircraftID = aircraft.getAircraftID();
        var model = aircraft.getModel().getValue();
        var seatsDdModels = SeatDbModelStub.getSeatDbModels(5, aircraftID);

        var aircraftDbModel = new AircraftDbModel();
        aircraftDbModel.setId(aircraftID.getValue());
        aircraftDbModel.setModel(model);
        aircraftDbModel.setSeat(seatsDdModels);

        return aircraftDbModel;
    }

}
