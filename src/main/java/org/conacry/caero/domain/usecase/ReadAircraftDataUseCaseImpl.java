package org.conacry.caero.domain.usecase;

import org.conacry.caero.boundary.repository.AircraftRepository;
import org.conacry.caero.boundary.usecase.ReadAircraftDataUseCase;
import org.conacry.caero.domain.entity.aircraft.Aircraft;
import org.conacry.caero.domain.entity.aircraft.AircraftID;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadAircraftDataUseCaseImpl implements ReadAircraftDataUseCase {

    private final AircraftRepository aircraftRepository;

    public ReadAircraftDataUseCaseImpl(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    @Override
    public Aircraft findByID(String aircraftIDStr) {
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
