package org.conacry.caero.adapter.repository.convertor;

import org.conacry.caero.adapter.repository.RepositoryError;
import org.conacry.caero.adapter.repository.model.SeatDbModel;
import org.conacry.caero.domain.entity.aircraft.AircraftID;
import org.conacry.caero.domain.entity.seat.*;

import java.util.ArrayList;
import java.util.List;

public final class SeatConvertor {

    public static Seat toEntity(SeatDbModel seatDbModel) {
        if (seatDbModel == null) {
            throw RepositoryError.errSeatDbModelIsRequired();
        }

        var seatID = SeatID.from(seatDbModel.getId().toString());
        var number = SeatNumber.from(seatDbModel.getNumber());
        var fareCondition = FareCondition.valueOf(seatDbModel.getFareCondition());

        return new SeatBuilder().
                seatID(seatID).
                number(number).
                fareCondition(fareCondition).
                build();
    }

    public static List<Seat> toEntities(List<SeatDbModel> seatsDbModel) {
        if (seatsDbModel == null) {
            throw RepositoryError.errListSeatsDbModelIsRequired();
        }

        var seats = new ArrayList<Seat>();

        for (SeatDbModel seatDbModel: seatsDbModel) {
            seats.add(SeatConvertor.toEntity(seatDbModel));
        }

        return seats;
    }
    public static SeatDbModel toModel(Seat seat, AircraftID aircraftID) {
        if (seat == null) {
            throw RepositoryError.errSeatIsRequired();
        }
        if (aircraftID == null) {
            throw RepositoryError.errAircraftIdIsRequired();
        }

        var seatID = seat.getSeatID().getValue();
        var number = seat.getNumber().getValue();
        var fareCondition = seat.getFareCondition().toString();
        var aircraftUUID = aircraftID.getValue();

        var seatDbModel = new SeatDbModel();
        seatDbModel.setId(seatID);
        seatDbModel.setNumber(number);
        seatDbModel.setFareCondition(fareCondition);
        seatDbModel.setAircraftID(aircraftUUID);

        return seatDbModel;
    }

    public static List<SeatDbModel> toModels(List<Seat> seats, AircraftID aircraftID) {
        if (seats == null) {
            throw RepositoryError.errListSeatsIsRequired();
        }
        if (aircraftID == null) {
            throw RepositoryError.errAircraftIdIsRequired();
        }

        var listSeatDbModel = new ArrayList<SeatDbModel>();

        for(Seat seat: seats) {
            listSeatDbModel.add(toModel(seat, aircraftID));
        }

        return listSeatDbModel;
    }


}
