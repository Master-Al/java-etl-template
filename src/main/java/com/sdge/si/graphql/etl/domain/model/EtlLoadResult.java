package com.sdge.si.graphql.etl.domain.model;

import java.time.Instant;

public class EtlLoadResult {
    private String id;
    private String status;
    private Instant loadedAt;
    private int storedCount;
    private String errorMessage;

    public EtlLoadResult() {
    }

    public EtlLoadResult(String id, String status, Instant loadedAt, int storedCount, String errorMessage) {
        this.id = id;
        this.status = status;
        this.loadedAt = loadedAt;
        this.storedCount = storedCount;
        this.errorMessage = errorMessage;
    }

    public static EtlLoadResult success(String id, int storedCount) {
        return new EtlLoadResult(id, "LOADED", Instant.now(), storedCount, null);
    }

    public static EtlLoadResult failure(String id, String message) {
        return new EtlLoadResult(id, "FAILED", Instant.now(), 0, message);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getLoadedAt() {
        return loadedAt;
    }

    public void setLoadedAt(Instant loadedAt) {
        this.loadedAt = loadedAt;
    }

    public int getStoredCount() {
        return storedCount;
    }

    public void setStoredCount(int storedCount) {
        this.storedCount = storedCount;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
