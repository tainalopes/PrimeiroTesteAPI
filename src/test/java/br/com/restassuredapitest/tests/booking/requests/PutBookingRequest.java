package br.com.restassuredapitest.tests.booking.requests;

import br.com.restassuredapitest.tests.booking.payloads.BookingPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class PutBookingRequest {

    BookingPayloads bookingPayloads = new BookingPayloads();

    @Step("Busca uma reserva com token")
    public Response updateBookingToken(int id, String token){
            return given()
                    .header("Content-Type", "application/json" )
                    .header("Accept", "application/json")
                    .header("Cookie", token)
                    .when()
                    .body(bookingPayloads.payloadValidBooking().toString())
                    .put("booking/"+ id);
    }

    @Step("Busca uma reserva com o parâmetro Basic Path")
    public Response updateBookingBasicAuth(int id){
        return given()
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .header("Content-Type", "application/json" )
                .header("Accept", "application/json")
                .when()
                .body(bookingPayloads.payloadValidBooking().toString())
                .put("booking/"+ id);
    }

    @Step("Tenta atualizar uma reserva específica sem o token")
    public Response tryUpdateBookingWithoutToken(int id){
        return given()
                .header("Content-Type", "application/json" )
                .header("Accept", "application/json")
                .when()
                .body(bookingPayloads.payloadValidBooking().toString())
                .put("booking/"+ id);
    }
}
