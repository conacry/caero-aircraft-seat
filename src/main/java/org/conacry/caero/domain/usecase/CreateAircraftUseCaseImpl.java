package org.conacry.caero.domain.usecase;

import jakarta.annotation.Nullable;
import org.conacry.caero.boundary.model.AircraftCreateInfo;
import org.conacry.caero.boundary.repository.AircraftRepository;
import org.conacry.caero.boundary.usecase.CreateAircraftUseCase;
import org.conacry.caero.domain.entity.aircraft.Aircraft;
import org.conacry.caero.domain.entity.aircraft.AircraftBuilder;
import org.conacry.caero.domain.entity.aircraft.AircraftID;
import org.conacry.caero.domain.entity.aircraft.Model;

public class CreateAircraftUseCaseImpl implements CreateAircraftUseCase {

    private final AircraftRepository aircraftRepo;

    public CreateAircraftUseCaseImpl(AircraftRepository aircraftRepo) {
        this.aircraftRepo = aircraftRepo;
    }

    @Override
    public Aircraft execute(@Nullable AircraftCreateInfo info) {
        if (info == null) {
            throw UseCaseError.errAircraftCreateInfoRequired();
        }

        var model = Model.from(info.getModel());
        if (aircraftRepo.existByModel(model)){
            throw UseCaseError.errAircraftIsAlreadyExisted();
        }

        var aircraftID = AircraftID.newID();
        var aircraft = new AircraftBuilder().
                aircraftID(aircraftID).
                model(model).
                build();
        aircraft.initSeats(info.getSeatConfiguration());

        aircraftRepo.save(aircraft);

        return aircraft;
    }
}