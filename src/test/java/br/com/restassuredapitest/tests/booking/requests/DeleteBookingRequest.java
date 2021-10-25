package br.com.restassuredapitest.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteBookingRequest{

    @Step("Retorna um id com token")
    public Response deleteBooking(int id, String token){
        return given()
                .header("Content-Type", "application/json" )
                .header("Cookie", token)
                .when()
                .delete("booking/"+ id);
    }


}
