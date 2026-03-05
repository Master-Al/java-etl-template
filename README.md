# Quarkus Camel ETL Design Pattern

This project models a simple ETL pipeline using Apache Camel inside Quarkus. The design uses a clean Extract -> Transform -> Load flow with explicit interfaces so each step can be swapped without touching the route.

## Structure
- `src/main/java/com/sdge/si/graphql/etl/domain/model` - ETL request and record models.
- `src/main/java/com/sdge/si/graphql/etl/domain/port` - Extractor, Transformer, Loader interfaces.
- `src/main/java/com/sdge/si/graphql/etl/application` - ETL pipeline entrypoint.
- `src/main/java/com/sdge/si/graphql/etl/infrastructure/camel` - Camel route wiring the ETL stages.
- `src/main/java/com/sdge/si/graphql/etl/infrastructure/service` - Default ETL step implementations.
- `src/main/java/com/sdge/si/graphql/etl/infrastructure/repository` - In-memory loader repository.
- `src/test/resources/features/etl.feature` - BDD feature.
- `src/test/java/com/sdge/si/graphql/etl/bdd/EtlCucumberTest.java` - Cucumber JUnit Platform suite.
- `src/test/java/com/sdge/si/graphql/etl/bdd/steps/EtlStepDefinitions.java` - step definitions that run the Camel route.

## BDD Test
Run the BDD suite:

```bash
mvn test
```

The feature validates that raw payloads are normalized by the transformer and stored by the loader.
