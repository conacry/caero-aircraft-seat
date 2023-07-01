package org.conacry.caero.adapter.controller.convertor;

import org.conacry.caero.adapter.controller.ControllerError;
import org.conacry.caero.adapter.controller.request.CreateAircraftRequest;
import org.conacry.caero.boundary.model.SeatConfiguration;
import org.conacry.caero.domain.entity.aircraft.Aircraft;
import org.conacry.caero.domain.entity.aircraft.Model;
import org.conacry.caero.domain.primitive.exception.CodedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestConvertorTest {
    @Test
    void createRequestToModel_IsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> RequestConvertor.createRequestToModel(null));
        assertEquals(ControllerError.REQUEST_IS_REQUIRED, ex.getCode());
    }

    @Test
    void createRequestToModel_SeatConfigIsNull_ThrowEx() {
        var request = new CreateAircraftRequest();
        var ex = assertThrows(CodedException.class, () -> RequestConvertor.createRequestToModel(request));
        assertEquals(ControllerError.SEAT_CONFIG_IS_REQUIRED, ex.getCode());
    }

    @Test
    void createRequestToModel_NoExceptionThrow_ReturnAircraftCreateInfo() {
        var request = new CreateAircraftRequest();
        request.setModel("model");
        var seatConfig = new CreateAircraftRequest.SeatConfiguration();
        var firstClassSeatConfig = new CreateAircraftRequest.FirstClassSeatInfo();
        firstClassSeatConfig.setRowCount(1);
        firstClassSeatConfig.setSeatsPerRow(2);
        seatConfig.setFirstClassSeatInfo(firstClassSeatConfig);

        var economyClassSeatConfig = new CreateAircraftRequest.EconomySeatInfo();
        economyClassSeatConfig.setRowCount(1);
        economyClassSeatConfig.setSeatsPerRow(2);
        seatConfig.setEconomySeatInfo(economyClassSeatConfig);

        var businessClassSeatConfig = new CreateAircraftRequest.BusinessSeatInfo();
        businessClassSeatConfig.setRowCount(1);
        businessClassSeatConfig.setSeatsPerRow(2);
        seatConfig.setBusinessSeatInfo(businessClassSeatConfig);
        
        request.setSeatConfiguration(seatConfig);

        var info = RequestConvertor.createRequestToModel(request);
        assertNotNull(info);
    }
}