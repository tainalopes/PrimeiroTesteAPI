package br.com.restassuredapitest.tests;

import br.com.restassuredapitest.suites.AllTests;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

public class ClasseDeTeste {

    @Test
    @Category({AllTests.class})
    public void validaApiOnline(){
        Response responsePing =
                given()
                .header("Content-Type", "application/json")
                .when()
                .get("https://treinamento-api.herokuapp.com/ping");

        responsePing
                .then()
                .statusCode(201);
    }
}