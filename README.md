# Rest Assurred with Cucumber Api Automation Test for TMDB(The Movie Database)

This repository provides a structured approach for QA testing of The Movie Database (TMDB) API using Cucumber (BDD) and Rest Assured. The goal is to ensure comprehensive coverage of API functionalities, including data retrieval, authentication, and error handling.

## Prerequisites
1. **Java Installed:** Download from [Java]([https://www.postman.com/](https://www.oracle.com/tr/java/technologies/downloads/)).
2. **Maven Installed:** Download from [Maven]([https://nodejs.org/tr](https://maven.apache.org/install.html))
3. **IDE installed**
4. **API Keys:** Ensure you have a valid TMDB API key for testing.

### Setting Up the API Key
- Obtain your API key from [TMDB API Key Settings](https://www.themoviedb.org/settings/api).
- Add the API key to the project:
  1. Open to project.
  2. Go to 'src/test/resources/properties/prod.config.properties'
  3.  Assign your own APIKey to the "apiKey" variable than save


## Steps to Use

### 1. Clone the Repository
```bash
git clone [https://github.com/ezginacar/TMDB-Postman-API-Test.git](https://github.com/ezginacar/TMDB-API-Automation-Test.git)
```

### 2. Run Test using TestRunner.class
- Open the project
- Run project from 'src/test/java/runner/TestRunner.java'

### 3. Run Tests using Maven
```bash
- mvn clean test
```
-if you define new environment variables like dev, you have to define environment variables on 'src/test/resources/properties' (take a look prod.config.properties) and than run 
```bash
- mvn clean test -Denv=dev
```

## Reporting
- Cucumber generates HTML reports by default. After running the tests view the report at 'target/cucumber-reports.html'
