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
        // преобразовать стринг в аиркрафт айди
        var aircraftID = AircraftID.from(aircraftIDStr);


        // получить аиркрафт из репозитория по аикрафт айди
        var aircraftOpt = aircraftRepository.findByID(aircraftID);

        // если опшинал пустое, то выбросить исключение аиркрафт нот фаунд
        if (aircraftOpt.isEmpty()) {
            throw UseCaseError.errAircraftNotFound(aircraftID);
        }

        // вызвать у аиркрафт метод мэйкНотАктив
        var aircraft = aircraftOpt.get();
        aircraft.makeNotActive();

        // обновить аиркрафт в репозитории
        aircraftRepository.update(aircraft);
    }
}
