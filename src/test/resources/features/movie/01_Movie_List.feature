Feature: As a User, I want to list movies. So, I can test data retrieval and search functionalities

  Scenario: Get popullar movie list
    Given Set baseUrl and valid apiKey
    When Send "Get" request with "MOVIE_POPULAR" endpoint
    Then Status code is 200
    And "results" in the response body should be an array
    And "results" in the response body should have at least 1 content
    And Each "results" should have "title" that is a string
    And Each "results" should have "genre_ids" that is a integerArray
    And Each "results" should have "release_date" that contains a valid year


  Scenario Outline: Valid error handling for invalid page
    Given Set baseUrl and valid apiKey
    When Set given queryParam infos with integer type into the request body
      | key  | value         |
      | page | <invalidPage> |
    When Send "Get" request with "MOVIE_POPULAR" endpoint and request body
    Then Status code is 400
    And The response should have an error message "Invalid API key: You must be granted a valid key."
    Examples:
      | invalidPage |
      | 0           |
      | -1          |
      | 500         |

