package com.project.si.bdd.etl.extract;

public class EtlMessage {
    private String id;
    private String sourceSystem;
    private String payload;
    private String normalizedPayload;
    private String status;
    private String errorMessage;
    private int storedCount;

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

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getNormalizedPayload() {
        return normalizedPayload;
    }

    public void setNormalizedPayload(String normalizedPayload) {
        this.normalizedPayload = normalizedPayload;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getStoredCount() {
        return storedCount;
    }

    public void setStoredCount(int storedCount) {
        this.storedCount = storedCount;
    }
}
