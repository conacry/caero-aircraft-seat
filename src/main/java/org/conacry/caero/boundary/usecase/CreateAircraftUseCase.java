package org.conacry.caero.boundary.usecase;

import org.conacry.caero.boundary.model.AircraftCreateInfo;
import org.conacry.caero.domain.entity.aircraft.Aircraft;

public interface CreateAircraftUseCase {
    Aircraft execute(AircraftCreateInfo info);
}
