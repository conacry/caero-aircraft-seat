package org.conacry.caero.domain.entity.aircraft;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.conacry.caero.boundary.model.SeatConfiguration;
import org.conacry.caero.domain.entity.seat.Seat;
import org.conacry.caero.domain.entity.seat.Seats;

import java.util.List;

@EqualsAndHashCode
@ToString
public class Aircraft {
    private final AircraftID aircraftID;
    private final Model model;
    private AircraftStatus status;
    private Seats seats;

    Aircraft(AircraftID aircraftID, Model model, Seats seats, AircraftStatus status) {
        this.aircraftID = aircraftID;
        this.model = model;
        this.seats = seats;
        this.status = status;
    }

    public void initSeats(SeatConfiguration seatConfiguration) {
        seats.initSeats(seatConfiguration);
    }

    public void makeNotActive() {
        status = AircraftStatus.NOT_ACTIVE;
    }

    public AircraftID getAircraftID() {
        return aircraftID;
    }

    public Model getModel() {
        return model;
    }

    public List<Seat> getSeats() {
        return seats.toList();
    }

    public AircraftStatus getStatus() {
        return status;
    }
}


