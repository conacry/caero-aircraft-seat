package org.conacry.caero.adapter.repository.convertor;

import org.conacry.caero.adapter.repository.RepositoryError;
import org.conacry.caero.adapter.repository.model.AircraftDbModel;
import org.conacry.caero.domain.entity.aircraft.*;

import java.util.List;
import java.util.stream.Collectors;

public final class AircraftConvertor {

    private AircraftConvertor() {}

    public static Aircraft toEntity(AircraftDbModel aircraftDbModel){
        if (aircraftDbModel == null) {
            throw RepositoryError.errAircraftDbModelIsRequired();
        }

        var aircraftID = aircraftDbModel.getId().toString();
        var model = aircraftDbModel.getModel();
        var status = AircraftStatus.valueOf(aircraftDbModel.getStatus());

        var seatsDbModel = aircraftDbModel.getSeat();
        var seats = SeatConvertor.toEntities(seatsDbModel);

        return new AircraftBuilder().
                aircraftID(AircraftID.from(aircraftID)).
                model(Model.from(model)).
                status(status).
                seats(seats).
                build();
    }

    public static List<Aircraft> toEntities(List<AircraftDbModel> aircraftDbModels) {
        if (aircraftDbModels == null) {
            throw RepositoryError.errListAircraftDbModelsIsRequired();
        }
        return aircraftDbModels.stream().
                map(AircraftConvertor::toEntity).
                toList();
    }

    public static AircraftDbModel toModel(Aircraft aircraft){
        if (aircraft == null) {
            throw RepositoryError.errAircraftIsRequired();
        }

        var aircraftID = aircraft.getAircraftID();
        var model = aircraft.getModel().getValue();
        var seats = aircraft.getSeats();
        var status = aircraft.getStatus();

        var seatDbModels = SeatConvertor.toModels(seats, aircraftID);

        var aircraftDbModel = new AircraftDbModel();
        aircraftDbModel.setId(aircraftID.getValue());
        aircraftDbModel.setModel(model);
        aircraftDbModel.setSeat(seatDbModels);
        aircraftDbModel.setStatus(status.toString());

        return aircraftDbModel;
    }

    public static List<AircraftDbModel> toModels(List<Aircraft> aircraft) {
        if (aircraft == null) {
            throw RepositoryError.errListAircraftIsRequired();
        }
        return aircraft.stream().
                map(AircraftConvertor::toModel).
                toList();
    }
}
