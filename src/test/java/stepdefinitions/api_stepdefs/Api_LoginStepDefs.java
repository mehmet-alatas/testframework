package stepdefinitions.api_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Api_LoginStepDefs {
    private Response response;

    // Base URL for the API
    private static final String BASE_URL = "http://localhost:3001";

    /**
     * Step 1: Successful login with valid credentials
     */
    @Given("the user has the username {string} and password {string}")
    public void the_user_has_the_username_and_password(String username, String password) {
        // Store credentials for use in the scenario
        System.setProperty("username", username);
        System.setProperty("password", password);
    }
       @When("the user sends a POST request to {string} with the username and password")
    public void theUserSendsAPOSTRequestToWithTheUsernameAndPassword(String arg0) {
           // Extract credentials
           String username = System.getProperty("username");
           String password = System.getProperty("password");

           // Send POST request using RestAssured
           response = RestAssured.given()
                   .contentType(ContentType.JSON)
                   .body("{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}")
                   .when()
                   .post(BASE_URL + "/login");
    }


    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) {
        // Assert that the response status code matches the expected one
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then("the response body should contain the message {string}")
    public void the_response_body_should_contain_the_message(String expectedMessage) {
        // Assert that the response body contains the expected message
        response.then().body("message", equalTo(expectedMessage));
    }

    @Then("the response body should contain the username {string}")
    public void the_response_body_should_contain_the_username(String expectedUsername) {
        // Assert that the response body contains the username
        response.then().body("user.username", equalTo(expectedUsername));
    }

    /**
     * Step 2: Login attempt with invalid credentials
     */
    @Given("the user has the username {string} and password {string} for invalid credentials")
    public void the_user_has_the_username_and_password_for_invalid_credentials(String username, String password) {
        // Store invalid credentials for use in the scenario
        System.setProperty("username", username);
        System.setProperty("password", password);
    }

    @When("the user sends a POST request to \"/login\" with the invalid username and password")
    public void the_user_sends_a_post_request_to_login_with_invalid_credentials() {
        // Extract invalid credentials
        String username = System.getProperty("username");
        String password = System.getProperty("password");

        // Send POST request using RestAssured with invalid credentials
        response = RestAssured.given()
            .contentType("application/json")
            .body("{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}")
            .when()
            .post(BASE_URL + "/login");
    }

    @Then("the response body should contain the error {string}")
    public void the_response_body_should_contain_the_error(String expectedErrorMessage) {
        // Assert that the response body contains the expected error message
        response.then().body("error", equalTo(expectedErrorMessage));
    }


}
