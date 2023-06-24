package org.conacry.caero.domain.usecase;

import org.conacry.caero.boundary.repository.AircraftRepository;
import org.conacry.caero.boundary.usecase.ReadAircraftDataUseCase;
import org.conacry.caero.domain.entity.aircraft.Aircraft;
import org.conacry.caero.domain.entity.aircraft.AircraftID;

import java.util.List;

public class ReadAircraftDataUseCaseImpl implements ReadAircraftDataUseCase {

    private final AircraftRepository aircraftRepository;

    public ReadAircraftDataUseCaseImpl(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    @Override
    public Aircraft findByID(String aircraftIDStr) {
        //из стр aircraftID, from rep aircraft by ID, if optional is empty you should throw exception(AircraftNotFound exception)
        // if optional isn't empty you should return aircraft

        var aircraftID = AircraftID.from(aircraftIDStr);
        var aircraftOpt = aircraftRepository.findByID(aircraftID);

        return aircraftOpt.
                orElseThrow(() -> UseCaseError.errAircraftNotFound(aircraftID));
    }

    @Override
    public List<Aircraft> findAll() {
        return aircraftRepository.findAll();
    }
}
