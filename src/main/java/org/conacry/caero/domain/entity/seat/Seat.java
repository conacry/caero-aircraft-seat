package org.conacry.caero.domain.entity.seat;

public class Seat {

    private final SeatID seatID;
    private final SeatNumber number;
    private final FareCondition fareCondition;

    Seat(SeatID seatID, SeatNumber number, FareCondition fareCondition) {
        this.seatID = seatID;
        this.number = number;
        this.fareCondition = fareCondition;
    }

    public SeatNumber getNumber() {
        return number;
    }

    public FareCondition getFareCondition() {
        return fareCondition;
    }

    public SeatID getSeatID() {
        return seatID;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatID=" + seatID +
                ", number=" + number +
                ", fareCondition=" + fareCondition +
                '}';
    }
}
