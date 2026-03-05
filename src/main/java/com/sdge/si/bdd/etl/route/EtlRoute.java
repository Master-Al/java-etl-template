package com.sdge.si.bdd.etl.route;

import com.sdge.si.bdd.etl.extract.EtlMessage;
import com.sdge.si.bdd.etl.load.EtlLoader;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

import java.util.Locale;
import java.util.UUID;

@ApplicationScoped
public class EtlRoute extends RouteBuilder {
    private final EtlLoader loader;

    @Inject
    public EtlRoute(EtlLoader loader) {
        this.loader = loader;
    }

    @Override
    public void configure() {
        onException(Exception.class)
                .handled(true)
                .process(exchange -> {
                    Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
                    String message = cause == null ? "Unknown error" : cause.getMessage();
                    EtlMessage failure = currentMessage(exchange);
                    failure.setStatus("FAILED");
                    failure.setErrorMessage(message);
                    exchange.getIn().setBody(failure);
                });

        from("direct:etl")
                .routeId("etl-route")
                .process(exchange -> {
                    EtlMessage message = currentMessage(exchange);
                    if (message.getId() == null || message.getId().trim().isEmpty()) {
                        message.setId(UUID.randomUUID().toString());
                    }
                    exchange.getIn().setBody(message);
                })
                .process(exchange -> {
                    EtlMessage message = currentMessage(exchange);
                    String payload = message.getPayload();
                    String normalized = payload == null ? "" : payload.trim().toUpperCase(Locale.ROOT);
                    message.setNormalizedPayload(normalized);
                    exchange.getIn().setBody(message);
                })
                .process(exchange -> {
                    EtlMessage message = currentMessage(exchange);
                    loader.load(message);
                    exchange.getIn().setBody(message);
                });
    }

    public String findStoredPayload(String id) {
        return loader.findStoredPayload(id);
    }

    private EtlMessage currentMessage(Exchange exchange) {
        EtlMessage message = exchange.getIn().getBody(EtlMessage.class);
        if (message == null) {
            message = new EtlMessage();
        }
        return message;
    }
}
