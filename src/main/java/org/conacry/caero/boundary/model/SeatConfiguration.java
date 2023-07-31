package org.conacry.caero.boundary.model;

import lombok.*;

import java.util.Optional;

@Data
public class SeatConfiguration {

    private SeatInfo firstClassSeatInfo;
    private SeatInfo businessSeatInfo;
    private SeatInfo economySeatInfo;

    public Optional<SeatInfo> getFirstClassSeatInfo() {
        return Optional.ofNullable(firstClassSeatInfo);
    }

    public Optional<SeatInfo> getBusinessSeatInfo() {
        return Optional.ofNullable(businessSeatInfo);
    }

    public Optional<SeatInfo> getEconomySeatInfo() {
        return Optional.ofNullable(economySeatInfo);
    }

    @Data
    public static class SeatInfo {
        private int rowCount;
        private int seatsPerRow;
    }
}
