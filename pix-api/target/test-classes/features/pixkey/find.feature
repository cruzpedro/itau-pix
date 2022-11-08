# language: en

Feature: Pix Key creation feature

  Background:
    Given database is clean
    And execute sqlFile in database with name "pixkey_update.sql"

  Scenario: Find Pix Key by id
    When a GET request to resource "/api/pix-key/2598ddd0-5f09-11ed-9b6a-0242ac120002" is made
    Then it should return http status code 200
