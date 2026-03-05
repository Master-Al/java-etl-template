package com.sdge.si.graphql.etl.infrastructure.service;

import com.sdge.si.graphql.etl.domain.model.EtlRecord;
import com.sdge.si.graphql.etl.domain.model.EtlTransformedRecord;
import com.sdge.si.graphql.etl.domain.port.Transformer;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Instant;
import java.util.Locale;

@ApplicationScoped
public class DefaultTransformer implements Transformer {

    @Override
    public EtlTransformedRecord transform(EtlRecord record) {
        String normalized = normalize(record == null ? null : record.getRawPayload());
        String id = record == null ? null : record.getId();
        String sourceSystem = record == null ? null : record.getSourceSystem();
        return new EtlTransformedRecord(id, sourceSystem, normalized, Instant.now());
    }

    private String normalize(String payload) {
        if (payload == null) {
            return "";
        }
        return payload.trim().toUpperCase(Locale.ROOT);
    }
}
