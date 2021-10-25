package br.com.restassuredapitest.tests.booking.requests;

import br.com.restassuredapitest.tests.booking.payloads.BookingPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostBookingRequest {
    BookingPayloads bookingPayloads = new BookingPayloads();

    @Step("Criar uma nova reserva")

    public Response createANewBooking(){
        return given()
                .header("Content-Type", "application/json")
                .when()
                .log().all()
                .body(bookingPayloads.payloadValidBooking().toString())
                .post("booking");
    }
//------------------------------------------------------------------
    @Step("Cria uma reserva com payload incorreto")
    public Response createInvalidBooking() {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(BookingPayloads.payloadCreateInvalidBooking().toString())
                .post("booking/");
    }

    //------------------------------------------------------------------
}
