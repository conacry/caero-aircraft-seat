package org.conacry.caero.adapter.repository.convertor;

import org.conacry.caero.adapter.repository.RepositoryError;
import org.conacry.caero.domain.primitive.exception.CodedException;
import org.conacry.caero.testdouble.entity.AircraftStub;
import org.conacry.caero.testdouble.repository.AircraftDbModelStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AircraftConvertorTest {

    @Test
    void toEntity_AircraftDbModelIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> AircraftConvertor.toEntity(null));
        assertEquals(RepositoryError.AIRCRAFT_DB_MODEL_IS_REQUIRED, ex.getCode());
    }

    @Test
    void toEntity_AllParamsIsValid_ReturnAircraft() {
        var aircraftDbModel = AircraftDbModelStub.getAircraftDbModel();
        var aircraft = AircraftConvertor.toEntity(aircraftDbModel);
        assertNotNull(aircraft);
    }

    @Test
    void toEntities_ListAircraftDbModelsIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> AircraftConvertor.toEntities(null));
        assertEquals(RepositoryError.LIST_AIRCRAFT_DB_MODEL_IS_REQUIRED, ex.getCode());
    }

    @Test
    void toEntities_AllParamsIsValid_ReturnListAircraft() {
        var aircraftDbModels = AircraftDbModelStub.getAircraftDbModels(5);
        var aircraft = AircraftConvertor.toEntities(aircraftDbModels);
        assertNotNull(aircraft);
    }

    @Test
    void toModel_AircraftIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> AircraftConvertor.toModel(null));
        assertEquals(RepositoryError.AIRCRAFT_IS_REQUIRED, ex.getCode());
    }

    @Test
    void toModel_AllParamsIsValid_ReturnAircraftDbModel() {
        var aircraft = AircraftStub.getFullActiveAircraft();
        var aircraftDbModel = AircraftConvertor.toModel(aircraft);
        assertNotNull(aircraftDbModel);
    }

    @Test
    void toModels_ListAircraftIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> AircraftConvertor.toModels(null));
        assertEquals(RepositoryError.LIST_AIRCRAFT_IS_REQUIRED, ex.getCode());
    }

    @Test
    void toModels_AllParamsIsValid_ReturnListAircraftDbModels() {
        var aircraft = AircraftStub.getListFullAircraft(5);
        var aircraftDbModels = AircraftConvertor.toModels(aircraft);
        assertNotNull(aircraftDbModels);
    }
}
