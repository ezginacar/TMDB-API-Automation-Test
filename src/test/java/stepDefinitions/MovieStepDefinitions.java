package stepDefinitions;

import base.APIBaseTest;
import core.ScenarioContext;
import io.cucumber.java.en.And;
import org.testng.Assert;
import utils.Endpoints;
import utils.EnvironmentManager;

public class MovieStepDefinitions {
    private final ScenarioContext scenarioContext = ScenarioContext.getInstance();
    @And("Replace movie_id value with valid id at {string} endpoint")
    public void replaceMovie_idValueWithValidIdAtEndpoint(String path) {
        Endpoints endpoint = Endpoints.valueOf(path);
        String currentPath = endpoint.getEndpoint().replace("${movie_id}", EnvironmentManager.getProperty("movieId"));
        scenarioContext.set("replacedPath", currentPath);
    }

    @And("The movie {string} info should be {string}")
    public void theMovieInfoShouldBe(String fieldName , String expectedInfo) {
        Assert.assertEquals(APIBaseTest.getResponseBodyAsJson().get(fieldName),expectedInfo);
    }
}
