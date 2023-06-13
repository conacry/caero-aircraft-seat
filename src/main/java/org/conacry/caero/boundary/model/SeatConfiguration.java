package org.conacry.caero.boundary.model;

import lombok.Data;

@Data
public class SeatConfiguration {

    private FirstClassSeatInfo firstClassSeatInfo;
    private BusinessSeatInfo businessSeatInfo;
    private EconomySeatInfo economySeatInfo;

    @Data
    public static class FirstClassSeatInfo {
        private int rowCount;
        private int seatsPerRow;
    }

    @Data
    public static class BusinessSeatInfo {
        private int rowCount;
        private int seatsPerRow;
    }

    @Data
    public static class EconomySeatInfo {
        private int rowCount;
        private int seatsPerRow;
    }
}
