package br.com.restassuredapitest.tests.booking.tests;

import br.com.restassuredapitest.base.BaseTest;
import br.com.restassuredapitest.suites.AcceptanceTests;
import br.com.restassuredapitest.suites.AllTests;
import br.com.restassuredapitest.suites.EndToEndTests;
import br.com.restassuredapitest.suites.SecurityTests;
import br.com.restassuredapitest.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitest.tests.booking.requests.DeleteBookingRequest;
import br.com.restassuredapitest.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitest.tests.booking.requests.PostBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.Matchers.lessThan;

@Feature("Feature - exclusão de reservas")
public class DeleteBookingTest extends BaseTest {

    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();
    DeleteBookingRequest deleteBookingRequest = new DeleteBookingRequest();
    PostBookingRequest postBookingRequest = new PostBookingRequest();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Deletar uma reserva utilizando token")
    public void testDeleteABookingWithToken(){

        postBookingRequest.createANewBooking();
        int firstId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        deleteBookingRequest.deleteBooking(firstId, postAuthRequest.getToken())
                .then()
                .statusCode(201)
                .time(lessThan(2L), TimeUnit.SECONDS);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, EndToEndTests.class})
    @DisplayName("Deletar uma reserva que não existe")
    public void testDeleteABookingThatDoesntExist(){

        deleteBookingRequest.deleteBooking(999999999, postAuthRequest.getToken())
                .then()
                .statusCode(405)
                .time(lessThan(2L), TimeUnit.SECONDS);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, SecurityTests.class})
    @DisplayName("Deletar uma reserva sem autorização(token)")
    public void testDeleteABookingWithoutAToken(){

        postBookingRequest.createANewBooking();
        int firstId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        deleteBookingRequest.deleteBooking(firstId, "")
                .then()
                .statusCode(403)
                .time(lessThan(2L), TimeUnit.SECONDS);
    }
}
