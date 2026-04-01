# Quarkus Camel ETL Design Pattern

This project models a simple ETL pipeline using Apache Camel inside Quarkus. The design keeps the flow explicit with one extractor, one transformer, one loader, and a Camel route that wires them together.

## Structure
- `src/main/java/com/project/si/bdd/etl/extract` - Single ETL message DTO.
- `src/main/java/com/project/si/bdd/etl/transform` - Simple transformer.
- `src/main/java/com/project/si/bdd/etl/load` - Simple in-memory loader.
- `src/main/java/com/project/si/bdd/etl/route` - Camel route that executes extract, transform, and load in order.
- `src/test/resources/features/etl.feature` - BDD feature.
- `src/test/java/com/project/si/bdd/etl/EtlCucumberTest.java` - Cucumber JUnit Platform suite.
- `src/test/java/com/project/si/bdd/etl/EtlStepDefinitions.java` - step definitions that run the Camel route.

## BDD Test
Run the BDD suite:

```bash
mvn test
```

The feature validates that raw payloads are normalized by the transformer and stored by the loader.
