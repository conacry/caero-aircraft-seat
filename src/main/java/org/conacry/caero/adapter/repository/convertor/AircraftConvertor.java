package org.conacry.caero.adapter.repository.convertor;

import org.conacry.caero.adapter.repository.RepositoryError;
import org.conacry.caero.adapter.repository.model.AircraftDbModel;
import org.conacry.caero.domain.entity.aircraft.Aircraft;
import org.conacry.caero.domain.entity.aircraft.AircraftBuilder;
import org.conacry.caero.domain.entity.aircraft.AircraftID;
import org.conacry.caero.domain.entity.aircraft.Model;

public final class AircraftConvertor {

    public static Aircraft toEntity(AircraftDbModel aircraftDbModel){
        if (aircraftDbModel == null) {
            throw RepositoryError.errAircraftDbModelIsRequired();
        }
        var aircraftID = aircraftDbModel.getId().toString();
        var model = aircraftDbModel.getModel();
        var seatsDbModel = aircraftDbModel.getSeat();

        var seats = SeatConvertor.toEntities(seatsDbModel);

        return new AircraftBuilder().
                aircraftID(AircraftID.from(aircraftID)).
                model(Model.from(model)).
                seats(seats).
                build();
    }

    public static AircraftDbModel toModel(Aircraft aircraft){
        if (aircraft == null) {
            throw RepositoryError.errAircraftIsRequired();
        }
        var aircraftID = aircraft.getAircraftID();
        var model = aircraft.getModel().getValue();
        var seats = aircraft.getSeats();

        var seatDbModels = SeatConvertor.toModels(seats, aircraftID);

        var aircraftDbModel = new AircraftDbModel();
        aircraftDbModel.setId(aircraftID.getValue());
        aircraftDbModel.setModel(model);
        aircraftDbModel.setSeat(seatDbModels);

        return aircraftDbModel;
    }
}
