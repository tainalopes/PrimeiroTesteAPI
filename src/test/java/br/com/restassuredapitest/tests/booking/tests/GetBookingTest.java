package br.com.restassuredapitest.tests.booking.tests;

import br.com.restassuredapitest.base.BaseTest;
import br.com.restassuredapitest.suites.AcceptanceTests;
import br.com.restassuredapitest.suites.AllTests;
import br.com.restassuredapitest.suites.ContractTests;
import br.com.restassuredapitest.suites.EndToEndTests;
import br.com.restassuredapitest.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitest.tests.booking.requests.PostBookingRequest;
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
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;

@Feature("Feature - retorno de reservas")
public class GetBookingTest extends BaseTest {

    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostBookingRequest postBookingRequest = new PostBookingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, ContractTests.class})
    @DisplayName("Garantir o schema do retorno da listagem de reserva")
    public void testAssureTheBookingListingSchema(){

        postBookingRequest.createANewBooking();
        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking", "bookings"))))
                .body("booking", notNullValue());
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, ContractTests.class})
    @DisplayName("Garantir o schema do retorno de uma reserva específica")
    public void testAssureTheReturnOfASpecificBookingSchema(){

        postBookingRequest.createANewBooking();
        getBookingRequest.bookingReturnFirstId()
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking", "booking"))))
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar ids das reservas")
    public void testListTheIdsOfBookings(){

        postBookingRequest.createANewBooking();
        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar uma reserva específica")
    public void testListASpecificBooking() {

        postBookingRequest.createANewBooking();
        getBookingRequest.bookingReturnFirstId()
                .then().log().all()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar ids de reservas utilizando o filtro 'firstname'")
    public void testListIdsOfBookingsByFirstname() {

        postBookingRequest.createANewBooking();
        Response booking = getBookingRequest.bookingReturnFirstId();
        String firstname = booking.then().extract().path("firstname");

        getBookingRequest.bookingReturnIdsByFilter("firstname", firstname)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar ids de reservas utilizando o filtro 'lastname'")
    public void testListIdsOfBookingsByLastname() {

        postBookingRequest.createANewBooking();
        Response booking = getBookingRequest.bookingReturnFirstId();
        String lastname = booking.then().extract().path("lastname");

        getBookingRequest.bookingReturnIdsByFilter("lastname", lastname)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar ids de reservas utilizando o filtro 'checkin'")
    public void testListIdsOfBookingsByCheckin() {

        postBookingRequest.createANewBooking();
        Response booking = getBookingRequest.bookingReturnFirstId();
        String checkin = booking.then().extract().path("bookingdates.checkin");

        getBookingRequest.bookingReturnIdsByFilter("checkin", checkin)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar ids de reservas utilizando o filtro 'checkout'")
    public void testListIdsOfBookingsByCheckout() {

        postBookingRequest.createANewBooking();
        Response booking = getBookingRequest.bookingReturnFirstId();
        String checkout = booking.then().extract().path("bookingdates.checkout");

        getBookingRequest.bookingReturnIdsByFilter("bookingdates.checkout", checkout)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar ids de reservas utilizando o filtro 'checkout' duas vezes")
    public void testListIdsOfBookingsByTwoCheckout() {

        getBookingRequest.bookingReturnIdsByFilterWithDoubleCheckouts()
                .then()
                .statusCode(500);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar ids de reservas utilizando o filtro 'name', 'checkin' e 'checkout'")
    public void testListIdsOfBookingsByNameAndCheckinAndCheckout() {

        System.out.println(postBookingRequest.createANewBooking());
        getBookingRequest.bookingReturnIdsByFilterWithFirstnameAndLastnameAndChekinAndCheckout()
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, EndToEndTests.class})
    @DisplayName("Visualizar erro de servidor 500 quando enviar filtro mal formatado")
    public void testReturnErrorWithABadFilter() {

        postBookingRequest.createANewBooking();
        getBookingRequest.bookingReturnIdsByFilter("checkin","26-10-2021")
                .then()
                .statusCode(500);
    }
}