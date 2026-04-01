package com.project.si.bdd.etl.transform;

import com.project.si.bdd.etl.extract.EtlMessage;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Locale;

@ApplicationScoped
public class EtlTransformer {

    public EtlMessage transform(EtlMessage message) {
        EtlMessage current = message == null ? new EtlMessage() : message;
        String payload = current.getPayload();
        String normalizedPayload = payload == null ? "" : payload.trim().toUpperCase(Locale.ROOT);
        current.setNormalizedPayload(normalizedPayload);
        return current;
    }
}
