package br.com.restassuredapitest.tests.booking.requests;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {

    public Response bookingReturnIds(){
        return given()
                .when()
                .get("booking"); //a url inicial está em "BaseTest", aqui vai csomente o resto do caminho
    }
}
