package cydeo.steps;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ReqResStepDefs {

    private Response response;
    private String baseUrl;

    @Given("the ReqRes API is available")
    public void the_reqres_api_is_available() {
        baseUrl = "https://reqres.in";
    }

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        response = given()
                .baseUri(baseUrl)
                .when()
                .get(endpoint)
                .then()
                .extract().response();
    }

    @When("I send a POST request to {string} with body:")
    public void i_send_a_post_request_to_with_body(String endpoint, String body) {
        response = given()
                .baseUri(baseUrl)
                .headers("Content-Type", "application/json", "x-api-key", "reqres-free-v1")
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .extract().response();
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response should contain a user with first name {string}")
    public void the_response_should_contain_a_user_with_first_name(String firstName) {
        response.then().body("data.first_name", hasItem(firstName));
    }

    @Then("the response should contain field {string} with value {string}")
    public void the_response_should_contain_field_with_value(String jsonPath, String value) {
        response.then().body(jsonPath, equalTo(value));
    }
}