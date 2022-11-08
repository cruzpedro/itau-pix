# language: en

Feature: Pix Key creation feature

  Background:
    Given database is clean
    And execute sqlFile in database with name "pixkey_update.sql"

  Scenario Outline: Update Pix Key
    Given create pix-key request is build with new information:
      | id   | account_type  | account   | agency   | customer_name  | customer_last_name |
      | <id> | <accountType> | <account> | <agency> | <customerName> | <customerLastName> |
    When a Put request to resource "/api/pix-key" is made
    Then it should return http status code <statusCode>
    Examples:
      | id                                   | accountType | account | agency | customerName | customerLastName | statusCode |
      | 2598ddd0-5f09-11ed-9b6a-0242ac120002 | CORRENTE    | 1       | 0      | User         | Test 2           | 200        |
