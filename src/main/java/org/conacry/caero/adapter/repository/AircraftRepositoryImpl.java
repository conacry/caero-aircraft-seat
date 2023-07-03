package org.conacry.caero.adapter.repository;

import org.conacry.caero.adapter.repository.convertor.AircraftConvertor;
import org.conacry.caero.adapter.repository.jpa.AircraftDao;
import org.conacry.caero.adapter.repository.model.AircraftDbModel;
import org.conacry.caero.boundary.repository.AircraftRepository;
import org.conacry.caero.domain.entity.aircraft.Aircraft;
import org.conacry.caero.domain.entity.aircraft.AircraftID;
import org.conacry.caero.domain.entity.aircraft.AircraftStatus;
import org.conacry.caero.domain.entity.aircraft.Model;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@Repository
public class AircraftRepositoryImpl implements AircraftRepository {

    private final AircraftDao aircraftDao;

    public AircraftRepositoryImpl(AircraftDao aircraftDao) {
        this.aircraftDao = aircraftDao;
    }

    @Transactional
    @Override
    public void save(Aircraft aircraft) {
        if (aircraft == null) {
            throw RepositoryError.errAircraftIsRequired();
        }

        var aircraftDbModel = AircraftConvertor.toModel(aircraft);
        aircraftDao.save(aircraftDbModel);
    }

    @Transactional
    @Override
    public void update(Aircraft aircraft) {
        save(aircraft);
    }

    @Override
    public Optional<Aircraft> getByID(AircraftID aircraftID) {
        if (aircraftID == null) {
            throw RepositoryError.errAircraftIdIsRequired();
        }

        return aircraftDao.findById(aircraftID.getValue())
                .map(AircraftConvertor::toEntity);
    }

    @Override
    public Optional<Aircraft> findByID(AircraftID aircraftID) {
        if (aircraftID == null) {
            throw RepositoryError.errAircraftIdIsRequired();
        }

        var aircraft = aircraftDao.findByIdAndStatus(aircraftID.getValue(), AircraftStatus.ACTIVE.name());

        return aircraft == null
                ? Optional.empty()
                : Optional.of(aircraft).map(AircraftConvertor::toEntity);
    }

    @Override
    public List<Aircraft> findAll() {
        var aircraftDBModels = aircraftDao.findAllByStatus(AircraftStatus.ACTIVE.name());
        return AircraftConvertor.toEntities(aircraftDBModels);
    }

    @Override
    public boolean existByModel(Model model) {
        if (model == null) {
            throw RepositoryError.errModelIsRequired();
        }

        var modelMather = ExampleMatcher.matching().
                withIgnorePaths("id").
                withMatcher("model", ignoreCase())
                .withMatcher("status", ignoreCase());

        var probe = new AircraftDbModel();
        probe.setModel(model.getValue());
        probe.setStatus(AircraftStatus.ACTIVE.name());

        var example = Example.of(probe, modelMather);

        return aircraftDao.exists(example);
    }
}
