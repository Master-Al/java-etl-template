package com.sdge.si.graphql.etl.infrastructure.service;

import com.sdge.si.graphql.etl.domain.model.EtlRecord;
import com.sdge.si.graphql.etl.domain.model.EtlRequest;
import com.sdge.si.graphql.etl.domain.port.Extractor;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Instant;
import java.util.UUID;

@ApplicationScoped
public class DefaultExtractor implements Extractor {

    @Override
    public EtlRecord extract(EtlRequest request) {
        String id = resolveId(request);
        String sourceSystem = request == null ? null : request.getSourceSystem();
        String payload = request == null ? null : request.getPayload();
        return new EtlRecord(id, sourceSystem, payload, Instant.now());
    }

    private String resolveId(EtlRequest request) {
        if (request == null) {
            return UUID.randomUUID().toString();
        }
        String correlationId = request.getCorrelationId();
        if (correlationId == null || correlationId.trim().isEmpty()) {
            return UUID.randomUUID().toString();
        }
        return correlationId.trim();
    }
}
