# Quarkus Camel ETL Design Pattern

This project models a simple ETL pipeline using Apache Camel inside Quarkus. The design uses a clean Extract -> Transform -> Load flow with explicit interfaces so each step can be swapped without touching the route.

## Structure
- `src/main/java/com/sdge/si/bdd/etl/extract` - Single ETL message DTO.
- `src/main/java/com/sdge/si/bdd/etl/transform` - (empty, reserved for future).
- `src/main/java/com/sdge/si/bdd/etl/load` - Simple in-memory loader.
- `src/main/java/com/sdge/si/bdd/etl/route` - Camel route with inline extract/transform and a load class.
- `src/test/resources/features/etl.feature` - BDD feature.
- `src/test/java/com/sdge/si/bdd/etl/EtlCucumberTest.java` - Cucumber JUnit Platform suite.
- `src/test/java/com/sdge/si/bdd/etl/EtlStepDefinitions.java` - step definitions that run the Camel route.

## BDD Test
Run the BDD suite:

```bash
mvn test
```

The feature validates that raw payloads are normalized by the transformer and stored by the loader.
