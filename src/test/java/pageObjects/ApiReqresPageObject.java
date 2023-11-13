package pageObjects;

import common.Common;
import config.PropertiesFile;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

public class ApiReqresPageObject extends Common {
    private static String apiBasePath = PropertiesFile.readProperties("apiBasePath");

    public static void getListUsers(int numberOfPage) {
         RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(apiBasePath)
                .basePath("/api/users")
                .get("?page=" + numberOfPage)
                .then()
                .extract().response();
    }


    public static Response getDataById(int id) {
        return RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(apiBasePath)
                .basePath("/api/users")
                .get(String.valueOf(id))
                .then()
                .extract().response();
    }

    public static void verifyNameById(Response response, String expectedName) {
        JsonPath jsonPath = new JsonPath(response.prettyPrint());
        String name = jsonPath.get("data.first_name");
        Assert.assertEquals("Name is incorrect ", expectedName, name);
    }

    public static void userWithIdshouldNotExist(Response response) {
        Assert.assertEquals("Status code invalid", 404, response.statusCode());
    }

    public static void verifyStatusCode(int expectedStatus, int actualStatus) {
        Assert.assertEquals("Status code invalid", expectedStatus, actualStatus);
    }

    public static void postCreateNewUser(String body) {
        Response response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(apiBasePath)
                .basePath("/api/users")
                .body(body)
                .post()
                .then()
                .extract().response();
        verifyStatusCode(201, response.statusCode());
    }

    public static String createUser(String name, String job) throws JsonProcessingException {
        return createUsersBody(name, job);
    }

    public static void putCreateNewUser(String id, String body) {
        Response response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(apiBasePath)
                .basePath("/api/users/" + id)
                .body(body)
                .put()
                .then()
                .extract().response();
        verifyStatusCode(200, response.statusCode());
    }
}
