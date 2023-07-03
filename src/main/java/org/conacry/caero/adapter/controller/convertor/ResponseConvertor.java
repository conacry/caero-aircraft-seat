package org.conacry.caero.adapter.controller.convertor;

import org.conacry.caero.adapter.controller.ControllerError;
import org.conacry.caero.adapter.controller.response.AircraftResponse;
import org.conacry.caero.domain.entity.aircraft.Aircraft;

import java.util.List;

public class ResponseConvertor {

    public static AircraftResponse aircraftToResponse(Aircraft aircraft) {
        if(aircraft == null){
            throw ControllerError.errAircraftIsNull();
        }
        var aircraftID = aircraft.getAircraftID().toString();
        var model = aircraft.getModel().toString();
        var status = aircraft.getStatus().toString();

        var aircraftResponse = new AircraftResponse();
        aircraftResponse.setAircraftID(aircraftID);
        aircraftResponse.setModel(model);
        aircraftResponse.setStatus(status);

        var seatResponses = aircraft.getSeats().stream().
                map(seat -> {
                    var seatResponse = new AircraftResponse.SeatResponse();
                    seatResponse.setSeatID(seat.getSeatID().toString());
                    seatResponse.setNumber(seat.getNumber().toString());
                    seatResponse.setFareCondition(seat.getFareCondition().toString());
                    return seatResponse;
                }).toList();
        aircraftResponse.setSeats(seatResponses);

        return aircraftResponse;
    }

    public static List<AircraftResponse> aircraftsToResponses(List<Aircraft> aircrafts) {
        if (aircrafts == null) {
            throw ControllerError.errAircraftListIsNull();
        }

        return aircrafts.stream()
                .map(ResponseConvertor::aircraftToResponse)
                .toList();
    }

    private ResponseConvertor() {}
}
