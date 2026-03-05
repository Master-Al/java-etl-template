package com.sdge.si.graphql.etl.infrastructure.service;

import com.sdge.si.graphql.etl.domain.model.EtlLoadResult;
import com.sdge.si.graphql.etl.domain.model.EtlTransformedRecord;
import com.sdge.si.graphql.etl.domain.port.Loader;
import com.sdge.si.graphql.etl.infrastructure.repository.InMemoryLoadRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class InMemoryLoader implements Loader {
    private final InMemoryLoadRepository repository;

    @Inject
    public InMemoryLoader(InMemoryLoadRepository repository) {
        this.repository = repository;
    }

    @Override
    public EtlLoadResult load(EtlTransformedRecord record) {
        if (record == null || record.getId() == null) {
            return EtlLoadResult.failure(null, "Missing record id");
        }
        repository.save(record);
        return EtlLoadResult.success(record.getId(), repository.count());
    }
}
