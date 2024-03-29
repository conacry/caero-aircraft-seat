package org.conacry.caero.domain.entity.seat;

import org.conacry.caero.boundary.model.SeatConfiguration;
import org.conacry.caero.domain.entity.aircraft.AircraftError;

import java.util.*;

public class Seats {

    private static final Map<Integer, String> lettersMap = Map.ofEntries(
            Map.entry(1, "A"),
            Map.entry(2, "B"),
            Map.entry(3, "C"),
            Map.entry(4, "D"),
            Map.entry(5, "E"),
            Map.entry(6, "F"),
            Map.entry(7, "G"),
            Map.entry(8, "H"),
            Map.entry(9, "I")
    );

    public static Seats newSeats() {
        return new Seats(Collections.emptyList());
    }

    public static Seats of(List<Seat> seats) {
        return new Seats(seats);
    }

    private List<Seat> seats;

    private Seats(List<Seat> seats) {
        this.seats = seats;
    }

    public void initSeats(SeatConfiguration seatConfiguration) {
        var targetSeats = new ArrayList<Seat>();
        if (!this.seats.isEmpty()) {
            throw AircraftError.errListSeatsIsCreated();
        }
        if (seatConfiguration == null) {
            throw AircraftError.errSeatConfigurationValueIsRequired();
        }

        var currentRow = 1;

        var firstClassInfoOpt = seatConfiguration.getFirstClassSeatInfo();
        if (firstClassInfoOpt.isPresent()) {
            var firstClassInfo = firstClassInfoOpt.get();
            var firstClassSeats = createSeats(currentRow, firstClassInfo.getRowCount(), firstClassInfo.getSeatsPerRow(), FareCondition.FIRST_CLASS);
            targetSeats.addAll(firstClassSeats);
            currentRow = currentRow + firstClassInfo.getRowCount();
        }

        var businessInfoOpt = seatConfiguration.getBusinessSeatInfo();
        if (businessInfoOpt.isPresent()) {
            var businessSeatsInfo = businessInfoOpt.get();
            var businessSeats = createSeats(currentRow, businessSeatsInfo.getRowCount(), businessSeatsInfo.getSeatsPerRow(), FareCondition.BUSINESS);
            targetSeats.addAll(businessSeats);
            currentRow = currentRow + businessSeatsInfo.getRowCount();
        }

        var economyInfoOpt = seatConfiguration.getEconomySeatInfo();
        if (economyInfoOpt.isPresent()) {
            var economySeatInfo = economyInfoOpt.get();
            var economySeats = createSeats(currentRow, economySeatInfo.getRowCount(), economySeatInfo.getSeatsPerRow(), FareCondition.ECONOMY);
            targetSeats.addAll(economySeats);
        }

        this.seats = targetSeats;
    }

    private List<Seat> createSeats(int startWithNumber, int rowCount, int seatsPerRow, FareCondition fareCondition) {
        var stopAtRow = startWithNumber + rowCount;
        var targetSeats = new ArrayList<Seat>();
        for (int i = startWithNumber; i < stopAtRow; i++) {
            for (int j = 1; j <= seatsPerRow; j++) {
                var letterSeat = seatPositionToLetter(j);
                var seatNumber = String.join("", String.valueOf(i), letterSeat);
                var seat = new SeatBuilder().
                        seatID(SeatID.newID()).
                        number(SeatNumber.from(seatNumber)).
                        fareCondition(fareCondition).
                        build();
                targetSeats.add(seat);
            }
        }
        return targetSeats;
    }

    private String seatPositionToLetter(int position) {
        if (lettersMap.containsKey(position)) {
            return lettersMap.get(position);
        }

        throw AircraftError.errIllegalSeatsPerRowValue(position);
    }

    public List<Seat> toList() {
        return List.copyOf(this.seats);
    }
}
