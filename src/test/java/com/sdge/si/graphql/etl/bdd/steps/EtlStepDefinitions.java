package com.sdge.si.graphql.etl.bdd.steps;

import com.sdge.si.graphql.etl.domain.model.EtlLoadResult;
import com.sdge.si.graphql.etl.domain.model.EtlRequest;
import com.sdge.si.graphql.etl.domain.model.EtlTransformedRecord;
import com.sdge.si.graphql.etl.domain.port.Loader;
import com.sdge.si.graphql.etl.infrastructure.camel.EtlRoute;
import com.sdge.si.graphql.etl.infrastructure.repository.InMemoryLoadRepository;
import com.sdge.si.graphql.etl.infrastructure.service.DefaultExtractor;
import com.sdge.si.graphql.etl.infrastructure.service.DefaultTransformer;
import com.sdge.si.graphql.etl.infrastructure.service.InMemoryLoader;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Assertions;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EtlStepDefinitions {
    private CamelContext camelContext;
    private ProducerTemplate producerTemplate;
    private InMemoryLoadRepository repository;
    private EtlRequest request;
    private EtlLoadResult result;

    @Before
    public void setUp() throws Exception {
        repository = new InMemoryLoadRepository();
        Loader loader = new InMemoryLoader(repository);
        EtlRoute route = new EtlRoute(new DefaultExtractor(), new DefaultTransformer(), loader);
        camelContext = new DefaultCamelContext();
        camelContext.addRoutes(route);
        camelContext.start();
        producerTemplate = camelContext.createProducerTemplate();
    }

    @After
    public void tearDown() throws Exception {
        if (producerTemplate != null) {
            producerTemplate.stop();
        }
        if (camelContext != null) {
            camelContext.stop();
        }
        if (repository != null) {
            repository.clear();
        }
    }

    @Given("an ETL request from {string} with payload {string}")
    public void anEtlRequestFromWithPayload(String sourceSystem, String payload) {
        request = new EtlRequest(sourceSystem, payload, null);
    }

    @When("the ETL pipeline runs")
    public void theEtlPipelineRuns() {
        result = producerTemplate.requestBody("direct:etl", request, EtlLoadResult.class);
    }

    @Then("the load result status is {string}")
    public void theLoadResultStatusIs(String expectedStatus) {
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedStatus, result.getStatus());
    }

    @Then("the stored payload is normalized to {string}")
    public void theStoredPayloadIsNormalizedTo(String expectedPayload) {
        Assertions.assertNotNull(result);
        EtlTransformedRecord stored = repository.findById(result.getId());
        Assertions.assertNotNull(stored);
        Assertions.assertEquals(expectedPayload, stored.getNormalizedPayload());
    }
}
