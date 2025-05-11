Feature: Login controller
  Scenario: Successful manager login
    Given url 'http://localhost:8183/auth/login'
    And request { login: 'manager1', password: 'qwerty' }
    When method POST
    Then status 200
    And match response.data != null

  Scenario: Invalid credentials
    Given url 'http://localhost:8183/auth/login'
    And request { login: 'some_login', password: 'some_password' }
    When method POST
    Then status 401
    And match response.error != null

  Scenario: Invalid body
    Given url 'http://localhost:8183/auth/login'
    And request { username: 'manager1', password: 'qwerty' }
    When method POST
    Then status 400
    And match response.error != null

  Scenario: Invalid request method
    Given url 'http://localhost:8183/auth/login'
    And request { username: 'manager1', password: 'qwerty' }
    When method PUT
    Then status 405
    And match response.error != null