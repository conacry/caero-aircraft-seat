package org.conacry.caero.testdouble.controller;

import org.conacry.caero.adapter.controller.request.CreateAircraftRequest;

public class RequestStub {

    public static CreateAircraftRequest getFullCreateAircraftRequest() {
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

        return request;
    }
}
