package br.com.restassuredapitest.tests.booking.tests;

import br.com.restassuredapitest.base.BaseTest;
import br.com.restassuredapitest.suites.AcceptanceTests;
import br.com.restassuredapitest.suites.AllTests;
import br.com.restassuredapitest.suites.EndToEndTests;
import br.com.restassuredapitest.suites.SecurityTests;
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
    BookingPayloads bookingPayloads = new BookingPayloads();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Criar uma nova reserva")
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
    @DisplayName("Validar a criação de mais de uma reserva em sequência. Nesse teste são criadas 2 reservas.")
    public void testCreateTwoBookingsInARow() {

        int i = 0;
        while (i < 2) {
            testCreateANewBooking();
            i++;
        }
    }

    @Test
    @Ignore("O teste não está executando pois tem o seguinte defeito: a reserva é criada " +
            "sem os parâmetros extras e retorna o status code 200." +
            "O correto seria não ser criada e retornar erro 400 - bad request.")
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SecurityTests.class})
    @DisplayName("Criar uma reserva enviando mais parâmetros no payload da reserva")
    public void testCreateBookingWithMoreParameters(){

        postBookingRequest.createANewBookingWithMoreParameters()
                .then()
                .statusCode(400)
                .time(lessThan(2L), TimeUnit.SECONDS);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, EndToEndTests.class})
    @DisplayName("Validar retorno 418 quando o header Accept for inválido")
    public void testValidateError418WhenInvalidAccept() {

        postBookingRequest.createABookingWithInvalidAccept(
                bookingPayloads.payloadValidBooking())
                .then()
                .statusCode(418)
                .time(lessThan(2L), TimeUnit.SECONDS);
    }
}
