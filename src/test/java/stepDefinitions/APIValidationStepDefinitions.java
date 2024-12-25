package stepDefinitions;


import base.APIBaseTest;
import io.cucumber.java.en.Given;


public class APIValidationStepDefinitions {
    @Given("Set baseUrl and {string} inValid apiKey")
    public void setBaseUrlAndInValidApiKey(String inValidApiKey) {
        APIBaseTest.setRequest(inValidApiKey);
    }
}
