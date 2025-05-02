Feature: Login

Scenario: Successful manager login
  Given url 'http://localhost:8183/auth/login'
  And request { login: 'manager1', password: 'qwerty' }
  When method post
  Then status 200
  And match response.data != null

Scenario: Invalid credentials
  Given url 'http://localhost:8183/auth/login'
  And request { login: 'some_login', password: 'some_password' }
  When method post
  Then status 401
  And match response.error != null

Scenario: Invalid body
  Given url 'http://localhost:8183/auth/login'
  And request { username: 'manager1', password: 'qwerty' }
  When method post
  Then status 400
  And match response.error != null

Scenario: Invalid request method
  Given url 'http://localhost:8183/auth/login'
  And request { username: 'manager1', password: 'qwerty' }
  When method put
  Then status 405
  And match response.error != null