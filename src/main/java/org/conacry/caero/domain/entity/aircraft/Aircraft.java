package org.conacry.caero.domain.entity.aircraft;

import org.conacry.caero.boundary.model.SeatConfiguration;
import org.conacry.caero.domain.entity.seat.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Aircraft {

    private final AircraftID aircraftID;
    private final Model model;
    private List<Seat> seats;

    Aircraft(AircraftID aircraftID, Model model, List<Seat> seats) {
        this.aircraftID = aircraftID;
        this.model = model;
        this.seats = seats;
    }

    public List<Seat> initSeats(SeatConfiguration seatConfiguration) {
        if (seatConfiguration == null) {
            //TODO throw exception
        }

        Map<SeatID, Seat> seatsStorage = new HashMap<>();

        var firstClassSeatsInfo = seatConfiguration.getFirstClassSeatInfo();
        var businessSeatsInfo = seatConfiguration.getBusinessSeatInfo();
        var economySeatsInfo = seatConfiguration.getEconomySeatInfo();

        if (firstClassSeatsInfo == null) {
            //TODO throw exception
        }
        if (businessSeatsInfo == null) {
            //TODO throw exception
        }
        if (economySeatsInfo == null) {
            //TODO throw exception
        }

        for (int i = 0; i <= firstClassSeatsInfo.getRowCount(); i++) {
            for (int j = 0; j <= firstClassSeatsInfo.getSeatsPerRow(); j++) {
                var letterSeat = numberToLetter(j);
                var seatNumber = i + letterSeat;
                var seat = new SeatBuilder().
                        seatID(SeatID.newID()).
                        number(SeatNumber.from(seatNumber)).
                        fareCondition(FareCondition.FIRST_CLASS).
                        build();
                seatsStorage.put(seat.getSeatID(), seat);
            }
        }

        for (int i = 0; i <= businessSeatsInfo.getRowCount(); i++) {
            for (int j = 0; j <= businessSeatsInfo.getSeatsPerRow(); j++) {
                var letterSeat = numberToLetter(j);
                var seatNumber = i + letterSeat;
                var seat = new SeatBuilder().
                        seatID(SeatID.newID()).
                        number(SeatNumber.from(seatNumber)).
                        fareCondition(FareCondition.BUSINESS).
                        build();
                seatsStorage.put(seat.getSeatID(), seat);
            }
        }

        for (int i = 0; i <= economySeatsInfo.getRowCount(); i++) {
            for (int j = 0; j <= economySeatsInfo.getSeatsPerRow(); j++) {
                var letterSeat = numberToLetter(j);
                var seatNumber = i + letterSeat;
                var seat = new SeatBuilder().
                        seatID(SeatID.newID()).
                        number(SeatNumber.from(seatNumber)).
                        fareCondition(FareCondition.ECONOMY).
                        build();
                seatsStorage.put(seat.getSeatID(), seat);
            }
        }

        return seatsStorage.values().stream().toList();
    }

    private String numberToLetter(int number) {
        if (number == 0) {
            /* TODO: throw new NumberNullException*/
        }
        if (number > 9) {
            /* TODO: throw new NumberLargeException*/
        }
        if (number == 1) {
            return "A";
        }
        if (number == 2) {
            return "B";
        }
        if (number == 3) {
            return "C";
        }
        if (number == 4) {
            return "D";
        }
        if (number == 5) {
            return "E";
        }
        if (number == 6) {
            return "F";
        }
        if (number == 7) {
            return "G";
        }
        if (number == 8) {
            return "H";
        }
        if (number == 9) {
            return "I";
        }
        return null;
    }

    public AircraftID getAircraftID() {
        return aircraftID;
    }

    public Model getModel() {
        return model;
    }

    public List<Seat> getSeats() {
        return seats;
    }
}


