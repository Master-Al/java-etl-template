package com.project.si.bdd.etl;

import com.project.si.bdd.etl.extract.EtlMessage;
import com.project.si.bdd.etl.load.EtlLoader;
import com.project.si.bdd.etl.route.EtlRoute;
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
    private EtlRoute route;
    private EtlLoader loader;
    private EtlMessage request;
    private EtlMessage result;

    @Before
    public void setUp() throws Exception {
        loader = new EtlLoader();
        route = new EtlRoute(loader);
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
    }

    @Given("an ETL request from {string} with payload {string}")
    public void anEtlRequestFromWithPayload(String sourceSystem, String payload) {
        request = new EtlMessage();
        request.setSourceSystem(sourceSystem);
        request.setPayload(payload);
    }

    @When("the ETL pipeline runs")
    public void theEtlPipelineRuns() {
        result = producerTemplate.requestBody("direct:etl", request, EtlMessage.class);
    }

    @Then("the load result status is {string}")
    public void theLoadResultStatusIs(String expectedStatus) {
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedStatus, result.getStatus());
    }

    @Then("the stored payload is normalized to {string}")
    public void theStoredPayloadIsNormalizedTo(String expectedPayload) {
        Assertions.assertNotNull(result);
        String stored = loader.findStoredPayload(result.getId());
        Assertions.assertEquals(expectedPayload, stored);
    }
}
