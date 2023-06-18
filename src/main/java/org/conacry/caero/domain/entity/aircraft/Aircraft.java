package org.conacry.caero.domain.entity.aircraft;

import org.conacry.caero.boundary.model.SeatConfiguration;
import org.conacry.caero.domain.entity.seat.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aircraft {
    private final AircraftID aircraftID;
    private final Model model;
    private List<Seat> seats;

    private static final HashMap<Integer, String> lettersMap = new HashMap<>();
    {
        lettersMap.put(1, "A");
        lettersMap.put(2, "B");
        lettersMap.put(3, "C");
        lettersMap.put(4, "D");
        lettersMap.put(5, "E");
        lettersMap.put(6, "F");
        lettersMap.put(7, "G");
        lettersMap.put(8, "H");
        lettersMap.put(9, "I");
    }

    Aircraft(AircraftID aircraftID, Model model, List<Seat> seats) {
        this.aircraftID = aircraftID;
        this.model = model;
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
            if (!lettersMap.containsKey(firstClassInfo.getSeatsPerRow())) {
                throw AircraftError.errIllegalSeatsPerRowValue(firstClassInfo.getSeatsPerRow());
            }
            var firstClassSeats = createSeats(currentRow, firstClassInfo.getRowCount(), firstClassInfo.getSeatsPerRow(), FareCondition.FIRST_CLASS);
            targetSeats.addAll(firstClassSeats);
            currentRow = currentRow + firstClassInfo.getRowCount();
        }

        var businessInfoOpt = seatConfiguration.getBusinessSeatInfo();
        if (businessInfoOpt.isPresent()) {
            var businessSeatsInfo = businessInfoOpt.get();
            if (!lettersMap.containsKey(businessSeatsInfo.getSeatsPerRow())) {
                throw AircraftError.errIllegalSeatsPerRowValue(businessSeatsInfo.getSeatsPerRow());
            }
            var businessSeats = createSeats(currentRow, businessSeatsInfo.getRowCount(), businessSeatsInfo.getSeatsPerRow(), FareCondition.BUSINESS);
            targetSeats.addAll(businessSeats);
            currentRow = currentRow + businessSeatsInfo.getRowCount();
        }

        var economyInfoOpt = seatConfiguration.getEconomySeatInfo();
        if (economyInfoOpt.isPresent()) {
            var economySeatInfo = economyInfoOpt.get();
            if (!lettersMap.containsKey(economySeatInfo.getSeatsPerRow())) {
                throw AircraftError.errIllegalSeatsPerRowValue(economySeatInfo.getSeatsPerRow());
            }
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
                var letterSeat = lettersMap.get(j);
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


