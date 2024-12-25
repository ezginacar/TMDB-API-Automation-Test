package stepDefinitions;

import core.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.LoggingUtil;

public class Hooks {
    private static final Logger logger = LogManager.getLogger(Hooks.class);

    @Before
    public void setup(Scenario scenario) {
        logger.info("Test environment setup..");
        ScenarioContext scenarioContext = ScenarioContext.getInstance();
        LoggingUtil.logTestStarting(scenario.getName());

    }

    @After
    public void afterScenario(Scenario scenario){
        if(scenario.isFailed())
            logger.warn("Scenario, {} is failed, lets take a look below logs", scenario.getName());

    }

}
