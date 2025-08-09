@api @reqres
Feature: ReqRes API Testing

  Scenario: Get list of users from page 2
    Given the ReqRes API is available
    When I send a GET request to "/api/users?page=2"
    Then the response status code should be 200
    And the response should contain a user with first name "Michael"

  Scenario: Create a new user
    Given the ReqRes API is available
    When I send a POST request to "/api/users" with body:
      """
      {
        "name": "John Doe",
        "job": "Software Engineer"
      }
      """
    Then the response status code should be 201
    And the response should contain field "name" with value "John Doe"

  Scenario: Get single user details
    Given the ReqRes API is available
    When I send a GET request to "/api/users/2"
    Then the response status code should be 200
    And the response should contain field "data.first_name" with value "Janet"