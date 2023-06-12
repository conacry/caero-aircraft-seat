package org.conacry.caero.testdouble.entity;

import org.conacry.caero.domain.entity.aircraft.Model;
import org.conacry.caero.util.StringGenerator;

public final class ModelStub {

    public static Model getModel() {
        return Model.from(StringGenerator.getRandomString());
    }
}
