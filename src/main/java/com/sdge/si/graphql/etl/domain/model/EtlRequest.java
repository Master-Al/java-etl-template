package com.sdge.si.graphql.etl.domain.model;

public class EtlRequest {
    private String sourceSystem;
    private String payload;
    private String correlationId;

    public EtlRequest() {
    }

    public EtlRequest(String sourceSystem, String payload, String correlationId) {
        this.sourceSystem = sourceSystem;
        this.payload = payload;
        this.correlationId = correlationId;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }
}
