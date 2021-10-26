package br.com.restassuredapitest.tests.booking.tests;

import br.com.restassuredapitest.base.BaseTest;
import br.com.restassuredapitest.suites.AcceptanceTests;
import br.com.restassuredapitest.suites.AllTests;
import br.com.restassuredapitest.suites.EndToEndTests;
import br.com.restassuredapitest.tests.booking.payloads.BookingPayloads;
import br.com.restassuredapitest.tests.booking.requests.PostBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

@Feature("Feature - criação de reservas")
public class PostBookingTest extends BaseTest {
    PostBookingRequest postBookingRequest = new PostBookingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Cria uma nova reserva")
    public void testCreateANewBooking(){
        postBookingRequest.createANewBooking()
                .then()
                .statusCode(200)
                .body("booking", notNullValue());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, EndToEndTests.class})
    @DisplayName("Validar retorno 500 quando o payload da reserva estiver inválido")
    public void testValidateErrorwhenInvalidPayLoad() {

        postBookingRequest.createANewBookingWithInvalidParameters()
                .then()
                .statusCode(500)
                .time(lessThan(2L), TimeUnit.SECONDS);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, EndToEndTests.class})
    @DisplayName("Validar a criação de mais de uma reserva em sequência")
    public void testCreateTwoBookingsInARow() {
        int i = 0;
        while (i < 2) {
            testCreateANewBooking();
            given()
                    .when()
                    .then()
                    .statusCode(200)
                    .log().all()
                    .body("size()", greaterThan(0));
            i++;
        }
    }

    @Test
    @Ignore("O teste não está executando pois tem o seguinte defeito: a reserva é criada, porém sem os parâmetros extras." +
            "O correto seria não ser criada e retornar erro 400 - bad request.")
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, EndToEndTests.class})
    @DisplayName("Criar uma reserva enviando mais parâmetros no payload da reserva")
    public void testAddBookingWithMoreParameters(){

        postBookingRequest.createANewBookingWithMoreParameters()
                .then()
                .statusCode(400)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, EndToEndTests.class})
    @DisplayName("Criar uma reserva com Header Accept Inválido")
    public void testCreateABookingWithInvalidAccept() {

        postBookingRequest.createABookingWithInvalidAccept(
                BookingPayloads.payloadValidBooking())
                .then()
                .statusCode(418)
                .time(lessThan(2L), TimeUnit.SECONDS);
    }
}
