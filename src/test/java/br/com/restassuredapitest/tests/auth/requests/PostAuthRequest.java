package br.com.restassuredapitest.tests.auth.requests;

import br.com.restassuredapitest.tests.auth.requests.payloads.AuthPayloads;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class PostAuthRequest {
    AuthPayloads authPayloads = new AuthPayloads();

    public Response tokenReturn(){

        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(authPayloads.jsonAuthLogin().toString())
                .post("auth"); //a url inicial está em "BaseTest", aqui vai csomente o resto do caminho
    }

    public String getToken(){
        return "token"+this.tokenReturn()
                .then()
                .statusCode(200)
                .extract()
                .path("token");
    }
}
