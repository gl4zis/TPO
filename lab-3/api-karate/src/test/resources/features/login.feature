Feature: Login

Scenario: Successful manager login
  Given url 'http://localhost:8183/auth/login'
  And request { login: 'manager1', password: 'qwerty' }
  When method post
  Then status 200
  And match response.data != null
