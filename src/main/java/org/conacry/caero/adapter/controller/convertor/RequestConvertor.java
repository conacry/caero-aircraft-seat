package org.conacry.caero.adapter.controller.convertor;

import org.conacry.caero.adapter.controller.ControllerError;
import org.conacry.caero.adapter.controller.request.CreateAircraftRequest;
import org.conacry.caero.boundary.model.AircraftCreateInfo;
import org.conacry.caero.boundary.model.SeatConfiguration;

public final class RequestConvertor {

    public static AircraftCreateInfo createRequestToModel(CreateAircraftRequest request) {
        if (request == null) {
            throw ControllerError.errRequestIsRequired();
        }

        if (request.getSeatConfiguration() == null) {
            throw ControllerError.errRequestNullIsRequired();
        }

        var seatConfig = new SeatConfiguration();

        if (request.getSeatConfiguration().getEconomySeatInfo() != null) {
            var economySeatConfig = new SeatConfiguration.EconomySeatInfo();
            economySeatConfig.setRowCount(request.getSeatConfiguration().getEconomySeatInfo().getRowCount());
            economySeatConfig.setSeatsPerRow(request.getSeatConfiguration().getEconomySeatInfo().getSeatsPerRow());
            seatConfig.setEconomySeatInfo(economySeatConfig);
        }

        if (request.getSeatConfiguration().getFirstClassSeatInfo() != null) {
            var firstSeatConfig = new SeatConfiguration.FirstClassSeatInfo();
            firstSeatConfig.setRowCount(request.getSeatConfiguration().getFirstClassSeatInfo().getRowCount());
            firstSeatConfig.setSeatsPerRow(request.getSeatConfiguration().getFirstClassSeatInfo().getSeatsPerRow());
            seatConfig.setFirstClassSeatInfo(firstSeatConfig);
        }

        if (request.getSeatConfiguration().getBusinessSeatInfo() != null) {
            var businessSeatConfig = new SeatConfiguration.BusinessSeatInfo();
            businessSeatConfig.setRowCount(request.getSeatConfiguration().getBusinessSeatInfo().getRowCount());
            businessSeatConfig.setSeatsPerRow(request.getSeatConfiguration().getBusinessSeatInfo().getSeatsPerRow());
            seatConfig.setBusinessSeatInfo(businessSeatConfig);
        }

        return new AircraftCreateInfo(request.getModel(), seatConfig);
    }

    private RequestConvertor() {}
}
