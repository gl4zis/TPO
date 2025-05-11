Feature: User controller
  Background:
    * def baseUrl = 'http://localhost:8183/user'
    * def managerToken = 'Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJtYW5hZ2VyMSIsInJvbGUiOiJNQU5BR0VSIiwiaWF0IjoxNzQ2OTQ4ODcyLCJleHAiOjMzMzAzODU3NjcyfQ.mdswveY7saPm9Z5-4SxYbufYuPTut27Nr2Y8QGG7w5Dc-lQOOaU2J868EfNiBi-N'
    * def residentToken = 'Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJyb21hIiwicm9sZSI6Ik5PTl9SRVNJREVOVCIsImlhdCI6MTc0Njk1MDY5OCwiZXhwIjozMzMwMzg1OTQ5OH0.4M4sw71qvO-vw5mafLj1bMBcb3G4Ty-v-nmVlGuPscubuiO8YzrcnB12gCBtPrJK'

  Scenario: Successfully get staff list
    Given url baseUrl + '/staff'
    And header Authorization = managerToken
    When method GET
    Then status 200
    And match response[0].role == 'MANAGER'

  Scenario: Try to get staff list with resident credentials
    Given url baseUrl + '/staff'
    And header Authorization = residentToken
    When method GET
    Then status 403

  Scenario: Try to get staff list without credentials
    Given url baseUrl + '/staff'
    When method GET
    Then status 401

  Scenario: Invalid request method
    Given url baseUrl + '/staff'
    And header Authorization = managerToken
    When method POST
    Then status 405

  Scenario: Successfully get residents list
    Given url baseUrl + '/residents'
    And header Authorization = managerToken
    When method GET
    Then status 200
    And match response[0].role == 'RESIDENT'

  Scenario: Try to get residents list with resident credentials
    Given url baseUrl + '/residents'
    And header Authorization = residentToken
    When method GET
    Then status 403