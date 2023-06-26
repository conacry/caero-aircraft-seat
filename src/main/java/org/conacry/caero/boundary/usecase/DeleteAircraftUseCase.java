package org.conacry.caero.boundary.usecase;

import org.conacry.caero.domain.entity.aircraft.AircraftID;

public interface DeleteAircraftUseCase {
    void deleteAircraftByID(String aircraftID);
}
