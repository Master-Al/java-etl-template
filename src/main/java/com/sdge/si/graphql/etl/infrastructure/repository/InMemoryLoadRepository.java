package com.sdge.si.graphql.etl.infrastructure.repository;

import com.sdge.si.graphql.etl.domain.model.EtlTransformedRecord;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class InMemoryLoadRepository {
    private final Map<String, EtlTransformedRecord> storage = new ConcurrentHashMap<>();

    public void save(EtlTransformedRecord record) {
        if (record == null || record.getId() == null) {
            return;
        }
        storage.put(record.getId(), record);
    }

    public EtlTransformedRecord findById(String id) {
        return storage.get(id);
    }

    public int count() {
        return storage.size();
    }

    public void clear() {
        storage.clear();
    }
}
