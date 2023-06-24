package org.conacry.caero.adapter.controller.request;


import lombok.Data;
import org.conacry.caero.boundary.model.AircraftCreateInfo;

@Data
public class CreateAircraftRequest {
    private String model;
    private SeatConfiguration seatConfiguration;

    @Data
    public static class SeatConfiguration {
        private FirstClassSeatInfo firstClassSeatInfo;
        private BusinessSeatInfo businessSeatInfo;
        private EconomySeatInfo economySeatInfo;
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

    public AircraftCreateInfo toBoundaryModel() {
        return null;
    }
}

