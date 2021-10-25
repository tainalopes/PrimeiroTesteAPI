package br.com.restassuredapitest.tests.booking.requests;

import br.com.restassuredapitest.tests.booking.payloads.BookingPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PutBookingRequest {
    BookingPayloads bookingPayloads = new BookingPayloads();

    @Step("Atualiza uma reserva específica com o parâmetro token")
    public Response updateBookingToken(int id, String token){
            return given()
                    .header("Content-Type", "application/json" )
                    .header("Accept", "application/json")
                    .header("Cookie", token)
                    .when()
                    .body(bookingPayloads.payloadValidBooking().toString())
                    .put("booking/"+ id);
    }

    @Step("Atualiza uma reserva específica com o parâmetro Basic Path")
    public Response updateBookingBasicAuth(int id){
        return given()
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .header("Content-Type", "application/json" )
                .header("Accept", "application/json")
                .when()
                .body(bookingPayloads.payloadValidBooking().toString())
                .put("booking/"+ id);
    }
}
