package base;


import utils.Endpoints;
import utils.EnvironmentManager;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.LoggingUtil;


import java.util.Map;

import static io.restassured.RestAssured.given;

public class APIBaseTest {

    private static String baseUrl = EnvironmentManager.getProperty("baseUrl");
    private static String apiKey= EnvironmentManager.getProperty("apiKey");
    private static RequestSpecification request;
    private static Response response;

    private static void setRequest() {
        APIBaseTest.request = given()
                .baseUri(baseUrl)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + apiKey);
    }

    public static void setRequest(String apiKey) {
        APIBaseTest.request = given()
                .baseUri(baseUrl)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + apiKey);
    }

    public static RequestSpecification getRequest() {
        if(request == null)
            setRequest();
        return request;
    }

    public static Response getResponse() {
        return response;
    }

    public static void setResponse(Response response) {
        APIBaseTest.response = response;
    }

    public static Response sendRequest(String endpoint, String method, Map<String, Object> body){
        switch (method.toUpperCase()){
            case "GET":
                response = request.when().get(endpoint);
                break;
            case "POST":
                response = request.body(body).when().post(endpoint);
                break;
            case "PUT":
                response = request.body(body).when().put(endpoint);
                break;
            case "DELETE":
                response = request.when().delete(endpoint);
                break;
            default:
                throw new IllegalArgumentException("Unsupported HTTP Method: " + method);
        }
        setResponse(response);
        LoggingUtil.logRequestBodyIfExists(body);
        LoggingUtil.logResponseDetails(response.statusCode(), response.getBody().asString());
        return response;
    }


    public static Response sendRequest(String endpoint, String method, Map<String, Object> body,Map<String, Object> queryParams){
        switch (method.toUpperCase()){
            case "GET":
                response = request.queryParams(queryParams).when().get(endpoint);
                break;
            case "POST":
                response = request.queryParams(queryParams).body(body).when().post(endpoint);
                break;
            case "PUT":
                response = request.queryParams(queryParams).body(body).when().put(endpoint);
                break;
            case "DELETE":
                response = request.queryParams(queryParams).when().delete(endpoint);
                break;
            default:
                throw new IllegalArgumentException("Unsupported HTTP Method: " + method);
        }
        setResponse(response);
        LoggingUtil.logRequestBodyIfExists(body);
        LoggingUtil.logResponseDetails(response.statusCode(), response.getBody().asString());
        return response;
    }

    public static Map<String,Object > getResponseBodyAsJson(){
        return response.jsonPath().getMap("$");
    }



/*

    //test
    public static void main(String[] args) {
        getRequest();
        sendRequest(Endpoints.MOVIE_POPULAR.getEndpoint(), "get", null);
    }

 */






}
