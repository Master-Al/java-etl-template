package com.sdge.si.graphql.etl.infrastructure.camel;

import com.sdge.si.graphql.etl.domain.model.EtlLoadResult;
import com.sdge.si.graphql.etl.domain.port.Extractor;
import com.sdge.si.graphql.etl.domain.port.Loader;
import com.sdge.si.graphql.etl.domain.port.Transformer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class EtlRoute extends RouteBuilder {
    private final Extractor extractor;
    private final Transformer transformer;
    private final Loader loader;

    @Inject
    public EtlRoute(Extractor extractor, Transformer transformer, Loader loader) {
        this.extractor = extractor;
        this.transformer = transformer;
        this.loader = loader;
    }

    @Override
    public void configure() {
        onException(Exception.class)
                .handled(true)
                .process(exchange -> {
                    Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
                    String message = cause == null ? "Unknown error" : cause.getMessage();
                    EtlLoadResult failure = EtlLoadResult.failure(null, message);
                    exchange.getIn().setBody(failure);
                });

        from("direct:etl")
                .routeId("etl-route")
                .bean(extractor, "extract")
                .bean(transformer, "transform")
                .bean(loader, "load");
    }
}
