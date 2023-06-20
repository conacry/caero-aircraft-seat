package org.conacry.caero.testdouble.entity;

import org.conacry.caero.boundary.model.SeatConfiguration;
import org.conacry.caero.domain.entity.aircraft.Aircraft;
import org.conacry.caero.domain.entity.aircraft.AircraftBuilder;
import org.conacry.caero.domain.entity.aircraft.AircraftID;

public final class SeatConfigurationStub {

    public static SeatConfiguration getSeatConfiguration() {
        var firstClassSeatsInfo = new SeatConfiguration.FirstClassSeatInfo();
        firstClassSeatsInfo.setRowCount(5);
        firstClassSeatsInfo.setSeatsPerRow(6);

        var businessSeatsInfo = new SeatConfiguration.BusinessSeatInfo();
        businessSeatsInfo.setRowCount(10);
        businessSeatsInfo.setSeatsPerRow(6);

        var economySeatsInfo = new SeatConfiguration.EconomySeatInfo();
        economySeatsInfo.setRowCount(20);
        economySeatsInfo.setSeatsPerRow(9);

        var seatConfiguration = new SeatConfiguration();
        seatConfiguration.setFirstClassSeatInfo(firstClassSeatsInfo);
        seatConfiguration.setBusinessSeatInfo(businessSeatsInfo);
        seatConfiguration.setEconomySeatInfo(economySeatsInfo);

        return seatConfiguration;
    }

    public static SeatConfiguration getSeatConfigurationIllegalPerRow() {
        var firstClassSeatsInfo = new SeatConfiguration.FirstClassSeatInfo();
        firstClassSeatsInfo.setRowCount(5);
        firstClassSeatsInfo.setSeatsPerRow(20);

        var businessSeatsInfo = new SeatConfiguration.BusinessSeatInfo();
        businessSeatsInfo.setRowCount(10);
        businessSeatsInfo.setSeatsPerRow(10);

        var economySeatsInfo = new SeatConfiguration.EconomySeatInfo();
        economySeatsInfo.setRowCount(20);
        economySeatsInfo.setSeatsPerRow(10);

        var seatConfiguration = new SeatConfiguration();
        seatConfiguration.setFirstClassSeatInfo(firstClassSeatsInfo);
        seatConfiguration.setBusinessSeatInfo(businessSeatsInfo);
        seatConfiguration.setEconomySeatInfo(economySeatsInfo);

        return seatConfiguration;
    }
}
