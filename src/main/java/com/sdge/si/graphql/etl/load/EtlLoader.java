package com.sdge.si.graphql.etl.load;

import com.sdge.si.graphql.etl.extract.EtlMessage;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class EtlLoader {
    private final Map<String, String> storage = new ConcurrentHashMap<>();

    public EtlMessage load(EtlMessage message) {
        if (message == null || message.getId() == null) {
            return message;
        }
        storage.put(message.getId(), message.getNormalizedPayload());
        message.setStoredCount(storage.size());
        message.setStatus("LOADED");
        return message;
    }

    public String findStoredPayload(String id) {
        return storage.get(id);
    }
}
