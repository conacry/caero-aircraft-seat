package org.conacry.caero.integration.adapter.repository;

import org.conacry.caero.adapter.repository.jpa.AircraftDao;
import org.conacry.caero.boundary.repository.AircraftRepository;
import org.conacry.caero.domain.entity.aircraft.Aircraft;
import org.conacry.caero.domain.entity.aircraft.AircraftID;
import org.conacry.caero.domain.entity.aircraft.AircraftStatus;
import org.conacry.caero.testdouble.entity.AircraftStub;
import org.conacry.caero.testdouble.entity.ModelStub;
import org.conacry.caero.util.annotation.IntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@IntegrationTest
class AircraftRepositoryTest {

    @Autowired
    private AircraftRepository aircraftRepository;

    @Autowired
    private AircraftDao aircraftDao;

    private static final Aircraft ACTIVE_AIRCRAFT = AircraftStub.getFullActiveAircraft();
    private static final Aircraft NOT_ACTIVE_AIRCRAFT = AircraftStub.getFullNotActiveAircraft();

    @BeforeEach
    void setUp() {
        aircraftRepository.save(ACTIVE_AIRCRAFT);
    }

    @AfterEach
    void tearDown() {
        aircraftDao.deleteAll();
    }

    @Test
    void findByID_AircraftNotExist_ReturnEmptyOptional() {
        var aircraftOpt = aircraftRepository.findByID(AircraftID.newID());
        assertTrue(aircraftOpt.isEmpty());
    }

    @Test
    void findByID_ActiveAircraftExist_ReturnAircraft() {
        var aircraftOpt = aircraftRepository.findByID(ACTIVE_AIRCRAFT.getAircraftID());
        assertTrue(aircraftOpt.isPresent());

        var aircraftFromDB = aircraftOpt.get();
        assertEquals(ACTIVE_AIRCRAFT, aircraftFromDB);
    }

    @Test
    void findByID_NotActiveAircraftID_ReturnEmptyOptional() {
        var aircraftOpt = aircraftRepository.findByID(NOT_ACTIVE_AIRCRAFT.getAircraftID());
        assertTrue(aircraftOpt.isEmpty());
    }

    @Test
    void update_AircraftUpdated() {
        var aircraftToUpdate = AircraftStub.getFullActiveAircraft();
        aircraftRepository.save(aircraftToUpdate);

        aircraftToUpdate.makeNotActive();
        assertEquals(AircraftStatus.NOT_ACTIVE, aircraftToUpdate.getStatus());

        var aircraftromDBOpt = aircraftRepository.getByID(aircraftToUpdate.getAircraftID());
        assertTrue(aircraftromDBOpt.isPresent());
        assertNotEquals(aircraftToUpdate, aircraftromDBOpt.get());

        aircraftRepository.update(aircraftToUpdate);

        aircraftromDBOpt = aircraftRepository.getByID(aircraftToUpdate.getAircraftID());
        assertTrue(aircraftromDBOpt.isPresent());
        assertEquals(aircraftToUpdate, aircraftromDBOpt.get());
    }

    @Test
    void findAll_ReturnAllAircraft() {
        var aircraftsToSave = AircraftStub.getListFullAircraft(10);
        aircraftsToSave.forEach(aircraftRepository::save);

        var aircraftsFromDB = aircraftRepository.findAll();
        assertNotNull(aircraftsFromDB);
        assertFalse(aircraftsFromDB.isEmpty());

        aircraftsFromDB.forEach(aircraft -> {
            assertNotNull(aircraftsFromDB);
            assertEquals(AircraftStatus.ACTIVE, aircraft.getStatus());
        });
    }

    @Test
    void existByModel_ActiveAircraftModel_ReturnTrue() {
        var isExists = aircraftRepository.existByModel(ACTIVE_AIRCRAFT.getModel());
        assertTrue(isExists);
    }

    @Test
    void existByModel_NotActiveAircraftModel_ReturnFalse() {
        var isExists = aircraftRepository.existByModel(NOT_ACTIVE_AIRCRAFT.getModel());
        assertFalse(isExists);
    }

    @Test
    void existByModel_UnknownAircraftModel_ReturnFalse() {
        var model = ModelStub.getModel();

        var isExists = aircraftRepository.existByModel(model);
        assertFalse(isExists);
    }
}
