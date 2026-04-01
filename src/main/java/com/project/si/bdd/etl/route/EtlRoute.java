package com.project.si.bdd.etl.route;

import com.project.si.bdd.etl.extract.EtlExtractor;
import com.project.si.bdd.etl.extract.EtlMessage;
import com.project.si.bdd.etl.load.EtlLoader;
import com.project.si.bdd.etl.transform.EtlTransformer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class EtlRoute extends RouteBuilder {
    private final EtlExtractor extractor;
    private final EtlTransformer transformer;
    private final EtlLoader loader;

    @Inject
    public EtlRoute(EtlExtractor extractor, EtlTransformer transformer, EtlLoader loader) {
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
                    EtlMessage message = currentMessage(exchange);
                    message.setStatus("FAILED");
                    message.setErrorMessage(cause == null ? "Unknown error" : cause.getMessage());
                    exchange.getIn().setBody(message);
                });

        from("direct:etl")
                .routeId("etl-route")
                .bean(extractor, "extract")
                .bean(transformer, "transform")
                .bean(loader, "load");
    }

    private EtlMessage currentMessage(Exchange exchange) {
        EtlMessage message = exchange.getIn().getBody(EtlMessage.class);
        return message == null ? new EtlMessage() : message;
    }
}
