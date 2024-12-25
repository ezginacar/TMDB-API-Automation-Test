package stepDefinitions;


import base.APIBaseTest;
import core.ScenarioContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import utils.Endpoints;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonStepDefinitions {
    private static final Logger logger = LogManager.getLogger(CommonStepDefinitions.class);
    private final ScenarioContext scenarioContext = ScenarioContext.getInstance();

    Map<String, Object> requestBody = new HashMap<>();

    @Given("Set baseUrl and valid apiKey")
    public void setBaseUrlAndValidApiKey() {
        APIBaseTest.getRequest();
    }



    @When("Send {string} request with {string} endpoint")
    public void sendRequstWithEndpoint(String method, String endpoint) {
        Endpoints path =  Endpoints.valueOf(endpoint);
        APIBaseTest.sendRequest(path.getEndpoint(), method,null);
    }

    @Then("Status code is {int}")
    public void statusCodeIs(int statusCode) {
        Assert.assertEquals(APIBaseTest.getResponse().getStatusCode(),statusCode);
        logger.info("Status code validated..");
    }


    @And("{string} in the response body should be an array")
    public void inTheResponseBodyShouldBeAnArray(String fieldName) {
        Object fieldValue = APIBaseTest.getResponseBodyAsJson().get(fieldName);
        Assert.assertTrue(fieldValue instanceof java.util.List, fieldName + " is not an array");
        logger.info("{} field in response body is an array..", fieldName);
    }

    @And("Each {string} should have {string} that is a string")
    public void eachShouldHaveThatIsAString(String parent, String child) {
        List<Map<String, Object>> results = (List<Map<String, Object>>) APIBaseTest.getResponseBodyAsJson().get(parent);
        for (int i = 0; i < results.size(); i++) {
            Assert.assertTrue(results.get(i).get(child) instanceof String,
                    String.format("%s[%d] doesn't hava a '%s' param that's a string", parent,i,child));
        }
        logger.info("Each {} field in {} is a string", child,parent);


    }

    @And("Each {string} should have {string} that is a integerArray")
    public void eachShouldHaveThatIsAIntegerArray(String parent, String child) {
        // Extract "result" array example
        List<Map<String, Object>> results = (List<Map<String, Object>>) APIBaseTest.getResponseBodyAsJson().get(parent);

        // Validate that "genre_ids" is an array of integers for each result
        for (int i = 0; i < results.size(); i++) {
            Object getChildObj = results.get(i).get(child);

            Assert.assertTrue(getChildObj instanceof List, String.format("%s[%d] doesn't have a '%s' field as a list", parent,i,child));

            // Check each element in the genre_ids list is an Integer
            List<?> items = (List<?>) getChildObj;
            for (int j = 0; j < items.size(); j++) {
                Assert.assertTrue(items.get(j) instanceof Integer,
                        String.format("Element at $.%s.%s[%d] is not an integer ", parent,child,i));
            }
        }
        logger.info(" {} field in {} is a an integerArray", child,parent);
    }

    @And("Each {string} should have {string} that contains a valid year")
    public void eachShouldHaveThatContainsAValidYear(String parent, String child) {
        List<Map<String, Object>> results = (List<Map<String, Object>>) APIBaseTest.getResponseBodyAsJson().get(parent);
        for (int i = 0; i < results.size(); i++) {
            String date = (String) results.get(i).get(child);
            Assert.assertTrue(date.matches("\\d{4}-\\d{2}-\\d{2}"),
                    "Result at index " + i + " does not have a valid date format in " + child);
        }
        logger.info(" {} field in {} is a valid year", child,parent);
    }


    @And("{string} in the response body should have at least {int} content")
    public void inTheResponseBodyShouldHaveAtLeastContent(String parent, int count) {
        Object fieldValue = APIBaseTest.getResponseBodyAsJson().get(parent);
        List<?> fieldList = (List<?>) fieldValue;
        Assert.assertTrue(fieldList.size() >= count,
                parent + " does not have at least " + count + " items");
        logger.info(" {} field has at liest {} content..", parent,count);
    }

    @And("The response should have an error message {string}")
    public void theResponseShouldHaveAnErrorMessage(String errorMsg) {
        Assert.assertEquals(errorMsg, APIBaseTest.getResponseBodyAsJson().get("status_message"));
        logger.info("Error message is validated: {}", errorMsg);
    }

    @And("The response should not have an error message")
    public void theResponseShouldNotHaveAnErrorMessage() {
        Assert.assertFalse(APIBaseTest.getResponseBodyAsJson().containsKey("status_message"));
        logger.info("Not seen any error message in the response..");
    }

    @When("Set given queryParam infos with integer type into the request body")
    public void setGivenQueryParamInfosWithIntegerTypeIntoTheRequestBody(DataTable dataTable) {
        List<Map<String, String>> queryParams = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> param : queryParams) {
            String key = param.get("key");
            Object value = param.get("value");

            requestBody.put(key, value);

        }

    }

    @When("Send {string} request with {string} endpoint and request body")
    public void sendRequestWithEndpointAndRequestBody(String method, String endpoint) {
        Endpoints path =  Endpoints.valueOf(endpoint);
        APIBaseTest.sendRequest(path.getEndpoint(), method,null,requestBody);
    }


    @When("Send {string} request with the modified endpoint")
    public void sendRequestWithTheModifiedEndpoint(String method) {
        String path = (String) scenarioContext.get("replacedPath");
        APIBaseTest.sendRequest(path, method,null);

    }
}
