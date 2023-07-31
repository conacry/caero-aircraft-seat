package org.conacry.caero.domain.entity.aircraft;

import org.conacry.caero.domain.entity.seat.Seat;
import org.conacry.caero.domain.entity.seat.Seats;

import java.util.Collections;
import java.util.List;

public class AircraftBuilder {

    private AircraftID aircraftID;
    private Model model;
    private Seats seats;
    private AircraftStatus status;

    public AircraftBuilder aircraftID(AircraftID aircraftID){
        this.aircraftID = aircraftID;
        return this;
    }

    public AircraftBuilder model(Model model){
        this.model = model;
        return this;
    }

    public AircraftBuilder seats(List<Seat> seats){
        this.seats = Seats.of(seats);
        return this;
    }

    public AircraftBuilder status(AircraftStatus status) {
        this.status = status;
        return this;
    }

    private void checkRequiredFields() {
        if(this.aircraftID == null){
            throw AircraftError.errAircraftIDIsRequired();
        }
        if(this.model == null){
            throw AircraftError.errModelIsRequired();
        }
        if (this.status == null) {
            throw AircraftError.errStatusIsRequired();
        }
    }

    private void fillDefaultValues() {
        if(this.seats == null){
            this.seats = Seats.newSeats();
        }
    }

    public Aircraft build() {
        this.checkRequiredFields();
        this.fillDefaultValues();

        return new Aircraft(
                this.aircraftID,
                this.model,
                this.seats,
                this.status);
    }
}
