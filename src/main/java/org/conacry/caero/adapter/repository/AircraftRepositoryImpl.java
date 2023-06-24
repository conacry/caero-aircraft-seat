package org.conacry.caero.adapter.repository;

import org.conacry.caero.adapter.repository.model.AircraftDbModel;
import org.conacry.caero.boundary.repository.AircraftRepository;
import org.conacry.caero.domain.entity.aircraft.Aircraft;
import org.conacry.caero.domain.entity.aircraft.AircraftID;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AircraftRepositoryImpl implements AircraftRepository {

    private final AircraftDao aircraftDao;

    public AircraftRepositoryImpl(AircraftDao aircraftDao) {
        this.aircraftDao = aircraftDao;
    }

    @Override
    public void save(Aircraft aircraft) {

    }

    @Override
    public Optional<Aircraft> findByID(AircraftID aircraftID) {
        return Optional.empty();
    }

    @Override
    public List<Aircraft> findAll() {
        return null;
    }
}
