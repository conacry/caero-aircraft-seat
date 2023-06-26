package org.conacry.caero.adapter.repository;

import org.conacry.caero.adapter.repository.model.AircraftDbModel;
import org.conacry.caero.boundary.repository.AircraftRepository;
import org.conacry.caero.domain.entity.aircraft.AircraftID;
import org.conacry.caero.domain.primitive.exception.CodedException;
import org.conacry.caero.testdouble.entity.AircraftStub;
import org.conacry.caero.testdouble.entity.ModelStub;
import org.conacry.caero.testdouble.repository.AircraftDbModelStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AircraftRepositoryImplTest {

    @Mock
    private AircraftDao aircraftDao;

    @Captor
    private ArgumentCaptor<AircraftDbModel> dbModelCaptor;

    private AircraftRepository aircraftRepository;

    @BeforeEach
    void setUp() {
        this.aircraftRepository = new AircraftRepositoryImpl(aircraftDao);
    }

    @Test
    void save_AircraftIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> aircraftRepository.save(null));
        assertEquals(RepositoryError.AIRCRAFT_IS_REQUIRED, ex.getCode());
    }

    @Test
    void save_AllParamsIsValid_DaoIsNotNull() {
        var aircraft = AircraftStub.getFullAircraft();

        aircraftRepository.save(aircraft);
        verify(aircraftDao).save(dbModelCaptor.capture());
        assertNotNull(dbModelCaptor.getValue());
    }

    @Test
    void findByID_AircraftIDIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> aircraftRepository.findByID(null));
        assertEquals(RepositoryError.AIRCRAFT_ID_IS_REQUIRED, ex.getCode());
    }

    @Test
    void findByID_AllParamsIsValid_ReturnAircraft() {
        var dbModel = AircraftDbModelStub.getAircraftDbModel();
        when(aircraftDao.findById(any(UUID.class))).thenReturn(Optional.of(dbModel));

        var aircraft = aircraftRepository.findByID(AircraftID.newID());
        assertNotNull(aircraft);

        assertEquals(dbModel.getId(), aircraft.get().getAircraftID().getValue());
    }

    @Test
    void findAll_NotParams_ReturnListAircraft() {
        var aircraftDbModels = AircraftDbModelStub.getAircraftDbModels(5);
        when(aircraftDao.findAll()).thenReturn(aircraftDbModels);

        var listAircraft = aircraftRepository.findAll();
        assertNotNull(listAircraft);

        assertEquals(aircraftDbModels.get(4).getId(), listAircraft.get(4).getAircraftID().getValue());
    }

    @Test
    void existByModel_ModelIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> aircraftRepository.existByModel(null));
        assertEquals(RepositoryError.MODEL_IS_REQUIRED, ex.getCode());
    }

    @Test
    void existByModel_AllParamsIsValid_ReturnBoolean() {
        var model = ModelStub.getModel();
        when(aircraftDao.exists(any(Example.class))).thenReturn(Boolean.TRUE);

        var modelIsTrue = aircraftRepository.existByModel(model);
        assertTrue(modelIsTrue);
    }


}
