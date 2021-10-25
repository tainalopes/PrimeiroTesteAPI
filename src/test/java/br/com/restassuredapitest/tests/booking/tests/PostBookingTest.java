package br.com.restassuredapitest.tests.booking.tests;

import br.com.restassuredapitest.base.BaseTest;
import br.com.restassuredapitest.suites.AcceptanceTests;
import br.com.restassuredapitest.suites.AllTests;
import br.com.restassuredapitest.suites.EndToEndTests;
import br.com.restassuredapitest.tests.booking.requests.PostBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;

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
                .log().all()
                .body("booking", notNullValue());

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, EndToEndTests.class})
    @DisplayName("Tentar criar uma reserva com payload inválido")
    public void testCreateANewBookingWithInvalidPayLoad() {

        postBookingRequest.createANewBookingWithInvalidParameters()
                .then()
                .statusCode(500)
                .log().all();
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
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, EndToEndTests.class})
    @DisplayName("Criar uma reserva enviando mais parâmetros no payload da reserva")
    public void testAddBookingWithMoreParameters(){

        postBookingRequest.createANewBookingWithMoreParameters()
                .then()
                .log().all()
                .statusCode(200); //ERRADO, ATENÇÃO!!!!!!!!!!!!!!
    }
}
