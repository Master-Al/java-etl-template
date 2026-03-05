package com.sdge.si.graphql.etl.domain.model;

import java.time.Instant;

public class EtlRecord {
    private String id;
    private String sourceSystem;
    private String rawPayload;
    private Instant extractedAt;

    public EtlRecord() {
    }

    public EtlRecord(String id, String sourceSystem, String rawPayload, Instant extractedAt) {
        this.id = id;
        this.sourceSystem = sourceSystem;
        this.rawPayload = rawPayload;
        this.extractedAt = extractedAt;
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

    public String getRawPayload() {
        return rawPayload;
    }

    public void setRawPayload(String rawPayload) {
        this.rawPayload = rawPayload;
    }

    public Instant getExtractedAt() {
        return extractedAt;
    }

    public void setExtractedAt(Instant extractedAt) {
        this.extractedAt = extractedAt;
    }
}
