package br.com.restassuredapitest.tests.ping.requests;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class GetPingRequest {

    public Response pingReturnApi(){
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("ping"); //a url inicial está em "BaseTest", aqui vai csomente o resto do caminho
    }
}
