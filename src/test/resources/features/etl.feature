Feature: Camel ETL pipeline
  As an integration developer
  I want a clear ETL pipeline
  So that raw payloads are normalized and loaded

  Scenario: Transform and load a payload
    Given an ETL request from "crm" with payload "  hello world  "
    When the ETL pipeline runs
    Then the load result status is "LOADED"
    And the stored payload is normalized to "HELLO WORLD"
