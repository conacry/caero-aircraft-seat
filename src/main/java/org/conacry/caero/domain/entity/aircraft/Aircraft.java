package org.conacry.caero.domain.entity.aircraft;

import org.conacry.caero.domain.entity.seat.Seat;

import java.util.List;

public class Aircraft {

    private final AircraftID aircraftID;
    private final Model model;
    private List<Seat> seats;

    Aircraft(AircraftID aircraftID, Model model, List<Seat> seats) {
        this.aircraftID = aircraftID;
        this.model = model;
        this.seats = seats;
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


