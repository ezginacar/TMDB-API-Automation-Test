Feature: As a user, i'd like to validate  user validation with apikey
  @ignore
  Scenario Outline: Valid error handling for inValid APIKeys
    Given Set baseUrl and "<invalidApiKey>" inValid apiKey
    When Send "Get" request with "MOVIE_POPULAR" endpoint
    Then Status code is 401
    And The response should have an error message "Invalid API key: You must be granted a valid key."
    Examples:
      |invalidApiKey |
      |invalidApiKey |
      |              |
      |123           |


  Scenario: Valid error handling for inValid APIKeys
    Given Set baseUrl and valid apiKey
    When Send "Get" request with "MOVIE_POPULAR" endpoint
    Then Status code is 200
    And The response should not have an error message
