package br.com.restassuredapitest.tests.booking.requests;

import br.com.restassuredapitest.tests.booking.payloads.BookingPayloads;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PutBookingRequest {
    BookingPayloads bookingPayloads = new BookingPayloads();

    public Response updateBookingToken(int id, String token){
        System.out.println(token);
            return given()
                    .header("Content-Type", "application/json" )
                    .header("Accept", "application/json")
                    .header("Cookie", token)
                    .when()
                    .body(bookingPayloads.payloadValidBooking().toString())
                    .put("booking/"+ id);

    }
}
