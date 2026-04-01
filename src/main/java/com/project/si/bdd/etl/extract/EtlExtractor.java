package com.project.si.bdd.etl.extract;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class EtlExtractor {

    public EtlMessage extract(EtlMessage message) {
        EtlMessage current = message == null ? new EtlMessage() : message;
        if (current.getId() == null || current.getId().trim().isEmpty()) {
            current.setId(UUID.randomUUID().toString());
        }
        return current;
    }
}
