package org.conacry.caero.adapter.repository;

import org.conacry.caero.adapter.repository.convertor.AircraftConvertor;
import org.conacry.caero.adapter.repository.model.AircraftDbModel;
import org.conacry.caero.boundary.repository.AircraftRepository;
import org.conacry.caero.domain.entity.aircraft.Aircraft;
import org.conacry.caero.domain.entity.aircraft.AircraftID;
import org.conacry.caero.domain.entity.aircraft.Model;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@Repository
public class AircraftRepositoryImpl implements AircraftRepository {

    private final AircraftDao aircraftDao;

    public AircraftRepositoryImpl(AircraftDao aircraftDao) {
        this.aircraftDao = aircraftDao;
    }

    @Override
    public void save(Aircraft aircraft) {
        if (aircraft == null) {
            throw RepositoryError.errAircraftIsRequired();
        }

        var aircraftDbModel = AircraftConvertor.toModel(aircraft);
        aircraftDao.save(aircraftDbModel);
    }

    @Override
    public Optional<Aircraft> findByID(AircraftID aircraftID) {
        if (aircraftID == null) {
            throw RepositoryError.errAircraftIdIsRequired();
        }
        return aircraftDao.findById(aircraftID.getValue()).
                map(AircraftConvertor::toEntity);
    }

    @Override
    public List<Aircraft> findAll() {
        return AircraftConvertor.toEntities(aircraftDao.findAll());
    }

    @Override
    public boolean existByModel(Model model) {
        if (model == null) {
            throw RepositoryError.errModelIsRequired();
        }
        ExampleMatcher modelMather = ExampleMatcher.matching().
                withIgnorePaths("id").
                withMatcher("model", ignoreCase());

        var probe = new AircraftDbModel();
        probe.setModel(model.getValue());

        var example = Example.of(probe, modelMather);

        return aircraftDao.exists(example);
    }
}
