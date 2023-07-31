package org.conacry.caero.adapter.controller.response;

import lombok.Data;

import java.util.List;

@Data
public class AircraftResponse {
    private String aircraftID;
    private String model;
    private String status;
    private List<SeatResponse> seats;

    @Data
    public static class SeatResponse {
        private String seatID;
        private String number;
        private String fareCondition;
    }
}
