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
    public void testValidaSchemaDaListagemDeReservas(){
        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .log().all()
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking", "bookings"))));
    }

    //COMEÇANDO O DESAFIO(BOA SORTE) <3

    //INÍCIO DO DESAFIO PARA TESTAR LISTAGENS
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar ids das reservas")
    public void testListaIdsDasReservas(){

        getBookingRequest.bookingReturnIds() //essa a é resposta da request feita em getBookingRequest
                .then()
                .statusCode(200) //esse é o status que deve retornar conforme a documentação
                .log().all()
                .body("size()", greaterThan(0));
        //a listagem deve ser maior que zero
        //validando que está retornando valores na listagem, pelo menos um
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar uma reserva específica")
    public void testValidaRetornoDeReservaEspecifica() {

        getBookingRequest.bookingReturnFirstId()
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar ids de reservas utilizando o filtro 'firstname'")
    public void testValidaListagemDeIdsDasReservasPorFirstname() {

        Response booking = getBookingRequest.bookingReturnFirstId();

        String firstname = booking.then().extract().path("firstname");

        getBookingRequest.bookingReturnIdsByFilter("firstname", firstname,
                        "", "",
                        "", "",
                        "", "")
                .then()
                .statusCode(200)
                .log().all()
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar ids de reservas utilizando o filtro 'lastname'")
    public void testValidaListagemDeIdsDasReservasPorLastname() {

        Response booking = getBookingRequest.bookingReturnFirstId();

        String lastname = booking.then().extract().path("lastname");

        getBookingRequest.bookingReturnIdsByFilter("lastname", lastname,
                        "", "",
                        "", "",
                        "", "")
                .then()
                .statusCode(200)
                .log().all()
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar ids de reservas utilizando o filtro 'checkin'")
    public void testValidaListagemDeIdsDasReservasPorCheckin() {

        Response booking = getBookingRequest.bookingReturnFirstId();

        String checkin = booking.then().extract().path("bookingdates.checkin");

        getBookingRequest.bookingReturnIdsByFilter("bookingdates.checkin", checkin,
                        "", "",
                        "", "",
                        "", "")
                .then()
                .statusCode(200)
                .log().all()
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar ids de reservas utilizando o filtro 'checkout'")
    public void testValidaListagemDeIdsDasReservasPorCheckout() {

        Response booking = getBookingRequest.bookingReturnFirstId();

        String checkout = booking.then().extract().path("bookingdates.checkout");

        getBookingRequest.bookingReturnIdsByFilter("bookingdates.checkout", checkout,
                        "", "",
                        "", "",
                        "", "")
                .then()
                .statusCode(200)
                .log().all()
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar ids de reservas utilizando o filtro 'checkin' e 'checkout'")
    public void testValidaListagemDeIdsDasReservasPorCheckinECheckout() {

        Response booking = getBookingRequest.bookingReturnFirstId();

        String checkin = booking.then().extract().path("bookingdates.checkin");
        String checkout = booking.then().extract().path("bookingdates.checkout");

        getBookingRequest.bookingReturnIdsByFilter("bookingdates.checkin", checkin,
                        "bookingdates.checkout", checkout,
                        "", "",
                        "", "")
                .then()
                .statusCode(200)
                .log().all()
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar ids de reservas utilizando o filtro 'name', 'checkin' e 'checkout'")
    public void testValidaListagemDeIdsDasReservasPorNameECheckinECheckout() {

        Response booking = getBookingRequest.bookingReturnFirstId();

        String firstname = booking.then().extract().path("firstname" );
        String lastname = booking.then().extract().path("lastname");
        String checkin = booking.then().extract().path("bookingdates.checkin");
        String checkout = booking.then().extract().path("bookingdates.checkout");

        getBookingRequest.bookingReturnIdsByFilter("firstname", firstname,
                        "lastname", lastname,
                        "bookingdates.checkin",
                        checkin, "bookingdates.checkout", checkout)
                .then()
                .statusCode(200)
                .log().all()
                .body("size()", greaterThan(0));
    }
}