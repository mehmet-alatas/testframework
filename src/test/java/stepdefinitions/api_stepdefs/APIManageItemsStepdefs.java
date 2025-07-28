package stepdefinitions.api_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.junit.Assert.*;

public class APIManageItemsStepdefs {

    private Response response;
    private static final String BASE_URL = "http://localhost:3001"; // API URL
    private static final String ITEM_ENDPOINT = "/items"; // Items endpoint
    private static int firstItem; // first item


    @When("the user sends a POST request to {string} with the name {string}")
    public void theUserSendsAPOSTRequestToWithTheName(String endpoint, String name) {
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{\"name\": \"" + name + "\"}") // Send JSON with name
                .post(BASE_URL + endpoint); // Make POST request to the endpoint
       response.prettyPrint();
        firstItem = response.jsonPath().getInt("id");
    }

    @Then("the response status code should be {int}")
    public void theResponsestatusCodeShouldBe(int expectedStatusCode) {
        // Assert that the status code is correct
        assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @And("the response body should contain the item name {string}")
    public void theResponseBodyShouldContainTheItemName(String expectedName) {
        // Assert that the response body contains the item name
        assertTrue(response.getBody().asString().contains(expectedName));
    }

    @And("the response body should contain the new item ID")
    public void theResponseBodyShouldContainTheNewItemID() {
        // Assert that the response contains the ID of the newly created item
        assertNotNull(response.jsonPath().get("id"));
    }


    @When("the user sends a GET request to {string}")
    public void theUserSendsAGETRequestTo(String endpoint) {
        // Send GET request to retrieve all items
        response = RestAssured.get(BASE_URL + endpoint);
        response.prettyPrint();
    }


    @Then("the response statuscode should be {int}")
    public void theResponseStatuscodeShouldBee(int expectedStatusCode) {
        // Assert that the status code is correct
        assertEquals(expectedStatusCode, response.getStatusCode());
    }


    @And("the response body should be a JSON array of items")
    public void theResponseBodyShouldBeAJSONArrayOfItems() {
        // Assert that the response body is a valid JSON array
        assertTrue(response.getBody().asString().startsWith("["));
        assertTrue(response.getBody().asString().endsWith("]"));
    }


    @When("the user sends a POST request to {string} with an empty name")
    public void theUserSendsAPOSTRequestToWithAnEmptyName(String endpoint) {
        // Send POST request with an empty name
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{\"name\": \"\"}") // Empty name
                .post(BASE_URL + endpoint);

    }

    @And("the responsebody should contain the error {string}")
    public void theResponsebodyShouldContainTheError(String errorMessage) {
        // Assert that the error message is in the response body
        response.prettyPrint();
        assertTrue(response.asString().contains(errorMessage));
    }


    @When("the user sends a PUT request to {string} with the name {string}")
    public void theUserSendsAPUTRequestToWithTheName(String endpoint, String name) {
        // Send PUT request to update an item with a new name
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{\"name\": \"" + name + "\"}") // Updated name
                .put(BASE_URL + endpoint + firstItem); // PUT request to update item
    }

    @And("the response body should contain the updated name {string}")
    public void theResponseBodyShouldContainTheUpdatedName(String updatedName) {
        // Assert that the updated name is in the response body
        assertTrue(response.getBody().asString().contains(updatedName));
    }



    @When("the user sends a PUT request to {string} with an empty name")
    public void theUserSendsAPUTRequestToWithAnEmptyName(String endpoint) {
        // Send PUT request with empty name
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{\"name\": \"\"}") // Empty name
                .put(BASE_URL + endpoint+ firstItem); // PUT request to update item
    }

    @When("the user sends a DELETE request to {string}")
    public void theUserSendsADELETERequestTo(String endpoint) {
        // Send DELETE request to remove an item
        response = RestAssured.delete(BASE_URL + endpoint+firstItem);
    }

    @And("the responsebody should contain the message {string}")
    public void theResponsebodyShouldContainTheMessage(String message) {
        // Assert that the response contains the expected delete message
        assertTrue(response.getBody().asString().contains(message));
    }

    @And("the responsebody should contain the item ID {int}")
    public void theResponsebodyShouldContainTheItemID(int id) {
        // Assert that the response body contains the item ID
        assertTrue(response.getBody().asString().contains("\"id\":" + id));
    }


    @And("I should see {string} in the response bug list")
    public void iShouldSeeInTheResponseBugList(String bugName) {
        // Assert that the response body contains the expected item name
        response.prettyPrint();
        assertTrue(response.getBody().asString().contains(bugName));
    }
}
