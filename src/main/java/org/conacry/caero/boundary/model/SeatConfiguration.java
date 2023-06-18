package org.conacry.caero.boundary.model;

import lombok.*;

import java.util.Optional;

@Data
public class SeatConfiguration {

    private FirstClassSeatInfo firstClassSeatInfo;
    private BusinessSeatInfo businessSeatInfo;
    private EconomySeatInfo economySeatInfo;

    public Optional<FirstClassSeatInfo> getFirstClassSeatInfo() {
        return Optional.ofNullable(firstClassSeatInfo);
    }

    public Optional<BusinessSeatInfo> getBusinessSeatInfo() {
        return Optional.ofNullable(businessSeatInfo);
    }

    public Optional<EconomySeatInfo> getEconomySeatInfo() {
        return Optional.ofNullable(economySeatInfo);
    }

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
