# language: en

Feature: Pix Key creation feature

  Background:
    Given database is clean

  Scenario Outline: Create Pix Key
    Given create pix-key request is build with new information:
      | key   | type   | account_type  | account   | agency   | customer_name  | customer_last_name |
      | <key> | <type> | <accountType> | <account> | <agency> | <customerName> | <customerLastName> |
    When a Post request to resource "/api/pix-key" is made
    Then it should return http status code <statusCode>
    Examples:
      | key                                                                            | type    | accountType | account | agency | customerName | customerLastName | statusCode |
      | teste@mail.com                                                                 | EMAIL   | CORRENTE    | 0001    | 01     | User         | Test 1           | 200        |
      | testemail.com                                                                  | EMAIL   | CORRENTE    | 0001    | 01     | User         | Test 1           | 422        |
      | teste@mail.commmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm | EMAIL   | CORRENTE    | 0001    | 01     | User         | Test 1           | 422        |
      | 09709218026                                                                    | CPF     | CORRENTE    | 0001    | 01     | User         | Test 1           | 200        |
      | 09709218027                                                                    | CPF     | CORRENTE    | 0001    | 01     | User         | Test 1           | 422        |
      | 17103338000171                                                                 | CNPJ    | CORRENTE    | 0001    | 01     | User         | Test 1           | 200        |
      | 17103338000170                                                                 | CNPJ    | CORRENTE    | 0001    | 01     | User         | Test 1           | 422        |
      | +5500912344321                                                                 | CELULAR | CORRENTE    | 0001    | 01     | User         | Test 1           | 200        |
      | 5500912344321                                                                  | CELULAR | CORRENTE    | 0001    | 01     | User         | Test 1           | 422        |
      | +500912344321                                                                  | CELULAR | CORRENTE    | 0001    | 01     | User         | Test 1           | 422        |
      | +5500012344321                                                                 | CELULAR | CORRENTE    | 0001    | 01     | User         | Test 1           | 422        |
      | +55009123443210                                                                | CELULAR | CORRENTE    | 0001    | 01     | User         | Test 1           | 422        |

  Scenario Outline: Create Pix Key for customer PF when already exists 5 keys
    Given execute sqlFile in database with name "pixkey.sql"
    And create pix-key request is build with new information:
      | key   | type   | account_type  | account   | agency   | customer_name  | customer_last_name |
      | <key> | <type> | <accountType> | <account> | <agency> | <customerName> | <customerLastName> |
    When a Post request to resource "/api/pix-key" is made
    Then it should return http status code <statusCode>
    Examples:
      | key            | type    | accountType | account | agency | customerName | customerLastName | statusCode |
      | +5500912344326 | CELULAR | CORRENTE    | 1       | 0      | User         | Test 1           | 422        |
