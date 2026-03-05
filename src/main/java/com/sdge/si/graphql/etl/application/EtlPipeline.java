package com.sdge.si.graphql.etl.application;

import com.sdge.si.graphql.etl.domain.model.EtlLoadResult;
import com.sdge.si.graphql.etl.domain.model.EtlRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.ProducerTemplate;

@ApplicationScoped
public class EtlPipeline {
    private final ProducerTemplate producerTemplate;

    @Inject
    public EtlPipeline(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    public EtlLoadResult run(EtlRequest request) {
        return producerTemplate.requestBody("direct:etl", request, EtlLoadResult.class);
    }
}
