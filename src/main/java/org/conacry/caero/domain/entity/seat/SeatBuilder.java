package org.conacry.caero.domain.entity.seat;

public class SeatBuilder {

    private SeatID seatID;
    private SeatNumber number;
    private FareCondition fareCondition;

    public SeatBuilder seatID(SeatID seatID) {
        this.seatID = seatID;
        return this;
    }

    public SeatBuilder number(SeatNumber number) {
        this.number = number;
        return this;
    }

    public SeatBuilder fareCondition(FareCondition fareCondition) {
        this.fareCondition = fareCondition;
        return this;
    }

    private void checkRequiredFields() {
        if (this.seatID == null) {
            throw SeatError.errSeatIDIsRequired();
        }
        if(this.number == null){
            throw SeatError.errSeatNumberIsRequired();
        }
        if (this.fareCondition == null){
            throw SeatError.errSeatFareConditionIsRequired();
        }
    }

    public Seat build() {
        this.checkRequiredFields();
        return new Seat(this.seatID, this.number, this.fareCondition);
    }



    /*

    public Seat build() throws IllegalAccessException {
        this.checkRequiredFields();

        return new Seat(this.seatID,this.number,this.fareCondition);
    }
/**/

}
