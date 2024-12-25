Feature: as user, i'd like see film details. So, i can get info the details

  Scenario: Get valid movie details from id
     Given Set baseUrl and valid apiKey
     And Replace movie_id value with valid id at "MOVIE_FROM_ID" endpoint
     When Send "Get" request with the modified endpoint
     Then Status code is 200
     And The movie "title" info should be "Absolution"


