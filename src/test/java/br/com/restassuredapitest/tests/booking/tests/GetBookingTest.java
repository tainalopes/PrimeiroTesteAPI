package br.com.restassuredapitest.tests.booking.tests;

import br.com.restassuredapitest.base.BaseTest;
import br.com.restassuredapitest.suites.AcceptanceTests;
import br.com.restassuredapitest.suites.AllTests;
import br.com.restassuredapitest.suites.ContractTests;
import br.com.restassuredapitest.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitest.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import java.io.File;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.greaterThan;

@Feature("Feature - retorno de reservas")
public class GetBookingTest extends BaseTest {
    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, ContractTests.class})
    @DisplayName("Garantir o schema do retorno da listagem de reserva")
    public void testValidateBookingListingSchema(){
        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking", "bookings"))));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, ContractTests.class})
    @DisplayName("Garantir o schema do retorno de uma reserva específica")
    public void testValidateReturnOfASpecificBookingSchema(){
        getBookingRequest.bookingReturnFirstId()
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking", "booking"))));

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar ids das reservas")
    public void testListTheIdsOfBookings(){

        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar uma reserva específica (array na posição 42)")
    public void testListTheReturnOfASpecificBooking() {

        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[42].bookingid");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar ids de reservas utilizando o filtro 'firstname'")
    public void testListTheIdsOfBookingsByFirstname() {

        Response booking = getBookingRequest.bookingReturnFirstId();

        String firstname = booking.then().extract().path("firstname");

        getBookingRequest.bookingReturnIdsByFilter("firstname", firstname,
                        "", "",
                        "", "",
                        "", "")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar ids de reservas utilizando o filtro 'lastname'")
    public void testListTheIdsOfBookingsByLastname() {

        Response booking = getBookingRequest.bookingReturnFirstId();
        String lastname = booking.then().extract().path("lastname");

        getBookingRequest.bookingReturnIdsByFilter("lastname", lastname,
                        "", "",
                        "", "",
                        "", "")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar ids de reservas utilizando o filtro 'checkin'")
    public void testListTheIdsOfBookingsByCheckin() {

        Response booking = getBookingRequest.bookingReturnFirstId();

        String checkin = booking.then().extract().path("bookingdates.checkin");

        getBookingRequest.bookingReturnIdsByFilter("bookingdates.checkin", checkin,
                        "", "",
                        "", "",
                        "", "")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar ids de reservas utilizando o filtro 'checkout'")
    public void testListTheIdsOfBookingsByCheckout() {

        Response booking = getBookingRequest.bookingReturnFirstId();

        String checkout = booking.then().extract().path("bookingdates.checkout");

        getBookingRequest.bookingReturnIdsByFilter("bookingdates.checkout", checkout,
                        "", "",
                        "", "",
                        "", "")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar ids de reservas utilizando o filtro 'checkout' duas vezes")
    public void testListTheIdsOfBookingsByCheckinAndCheckout() {

        Response booking = getBookingRequest.bookingReturnFirstId();

        String checkout = booking.then().extract().path("bookingdates.checkout");

        getBookingRequest.bookingReturnIdsByFilter("bookingdates.checkout", checkout,
                        "bookingdates.checkout", checkout,
                        "", "",
                        "", "")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
        System.out.println(checkout);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar ids de reservas utilizando o filtro 'name', 'checkin' e 'checkout'")
    public void testListTheIdsOfBookingsByNameAndCheckinAndCheckout() {

        Response booking = getBookingRequest.bookingReturnFirstId();

        String firstname = booking.then().extract().path("firstname");
        String lastname = booking.then().extract().path("lastname");
        String checkin = booking.then().extract().path("bookingdates.checkin");
        String checkout = booking.then().extract().path("bookingdates.checkout");

        getBookingRequest.bookingReturnIdsByFilter("firstname", firstname,
                        "lastname", lastname,
                        "bookingdates.checkin",
                        checkin, "bookingdates.checkout", checkout)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }
}