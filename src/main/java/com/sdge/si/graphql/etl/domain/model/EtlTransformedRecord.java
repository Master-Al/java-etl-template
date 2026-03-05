package com.sdge.si.graphql.etl.domain.model;

import java.time.Instant;

public class EtlTransformedRecord {
    private String id;
    private String sourceSystem;
    private String normalizedPayload;
    private Instant transformedAt;

    public EtlTransformedRecord() {
    }

    public EtlTransformedRecord(String id, String sourceSystem, String normalizedPayload, Instant transformedAt) {
        this.id = id;
        this.sourceSystem = sourceSystem;
        this.normalizedPayload = normalizedPayload;
        this.transformedAt = transformedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getNormalizedPayload() {
        return normalizedPayload;
    }

    public void setNormalizedPayload(String normalizedPayload) {
        this.normalizedPayload = normalizedPayload;
    }

    public Instant getTransformedAt() {
        return transformedAt;
    }

    public void setTransformedAt(Instant transformedAt) {
        this.transformedAt = transformedAt;
    }
}
