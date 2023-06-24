package org.conacry.caero.adapter.repository.convertor;

import org.conacry.caero.adapter.repository.RepositoryError;
import org.conacry.caero.adapter.repository.model.AircraftDbModel;
import org.conacry.caero.domain.primitive.exception.CodedException;
import org.conacry.caero.testdouble.repository.AircraftDbModelStub;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AircraftConvertorTest {

    @Test
    void toEntity_AircraftDbModelIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> AircraftConvertor.toEntity(null));
        assertEquals(RepositoryError.AIRCRAFT_DB_MODEL_VALUE_IS_REQUIRED, ex.getCode());
    }

    @Test
    void toModel_AircraftIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> AircraftConvertor.toModel(null));
        assertEquals(RepositoryError.AIRCRAFT_VALUE_IS_REQUIRED, ex.getCode());
    }

    @Test
    void toEntity_AircraftDbModel_ReturnAircraft() {
        var aircraftDbModel = AircraftDbModelStub.getAircraftDbModel();
        var aircraft = AircraftConvertor.toEntity(aircraftDbModel);
        assertNotNull(aircraft);

    }

    @Test
    void toModel_Aircraft_ReturnAircraftDbModel() {

    }

}
