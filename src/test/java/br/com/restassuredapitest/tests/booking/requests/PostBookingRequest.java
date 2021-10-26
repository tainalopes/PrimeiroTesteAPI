package br.com.restassuredapitest.tests.booking.requests;

import br.com.restassuredapitest.tests.booking.payloads.BookingPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONObject;

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

    @Step("Criar uma nova reserva com mais parâmetros")
    public Response createANewBookingWithMoreParameters(){
        return given()
                .header("Content-Type", "application/json")
                .when()
                .log().all()
                .body(bookingPayloads.payloadWithMoreParameters().toString())
                .post("booking");
    }

    @Step("Criar uma reserva com payload incorreto")
    public Response createANewBookingWithInvalidParameters() {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(BookingPayloads.payloadCreateInvalidParameters().toString())
                .post("booking");
    }

    @Step("Criar uma reserva com Header Accept inválido")
    public Response createABookingWithInvalidAccept(JSONObject payload){
        return given()
                .header("Accept", "x")
                .header("Content-Type", "application/json")
                .when()
                .body(payload.toString())
                .post("booking");
    }
}
