package org.conacry.caero.boundary.usecase;

import org.conacry.caero.domain.entity.aircraft.Aircraft;
import org.conacry.caero.domain.entity.aircraft.AircraftID;

import java.util.List;

public interface ReadAircraftDataUseCase {
    Aircraft findByID(AircraftID aircraftID);
    List<Aircraft> findAll();
}
