package org.conacry.caero.boundary.repository;

import org.conacry.caero.domain.entity.aircraft.Aircraft;
import org.conacry.caero.domain.entity.aircraft.AircraftID;
import org.conacry.caero.domain.entity.aircraft.Model;

import java.util.List;
import java.util.Optional;

public interface AircraftRepository {
    void save(Aircraft aircraft);
    void update(Aircraft aircraft);
    Optional<Aircraft> getByID(AircraftID aircraftID);
    Optional<Aircraft> findByID(AircraftID aircraftID);
    List<Aircraft> findAll();
    boolean existByModel(Model model);
}
