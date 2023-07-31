package org.conacry.caero.domain.usecase;

import org.conacry.caero.boundary.repository.AircraftRepository;
import org.conacry.caero.boundary.usecase.DeleteAircraftUseCase;
import org.conacry.caero.domain.entity.aircraft.Aircraft;
import org.conacry.caero.domain.entity.aircraft.AircraftError;
import org.conacry.caero.domain.entity.aircraft.AircraftID;
import org.springframework.stereotype.Service;

@Service
public class DeleteAircraftUseCaseImpl implements DeleteAircraftUseCase {

    private final AircraftRepository aircraftRepository;

    public DeleteAircraftUseCaseImpl(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    @Override
    public void deleteAircraftByID(String aircraftIDStr) {
        var aircraftID = AircraftID.from(aircraftIDStr);

        var aircraftOpt = aircraftRepository.findByID(aircraftID);

        if (aircraftOpt.isEmpty()) {
            throw UseCaseError.errAircraftNotFound(aircraftID);
        }

        var aircraft = aircraftOpt.get();
        aircraft.makeNotActive();

        aircraftRepository.update(aircraft);
    }
}
