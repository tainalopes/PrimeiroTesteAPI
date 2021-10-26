package br.com.restassuredapitest.tests.booking.tests;

import br.com.restassuredapitest.base.BaseTest;
import br.com.restassuredapitest.suites.AllTests;
import br.com.restassuredapitest.suites.ContractTests;
import br.com.restassuredapitest.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitest.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitest.tests.booking.requests.PutBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

@Feature("Feature - atualização de reservas")
public class PutBookingTest extends BaseTest{
    PutBookingRequest putBookingRequest = new PutBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, ContractTests.class})
    @DisplayName("Altera uma reserva somente utilizando o token")
    public void testUpdateABooKingWithToken(){
        //lembrando que o primeiro id está na posição [0]
        int firstId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                //método extract faz a extração do id que é solicitado abaixo
                .path("[0].bookingid"); //aqui pega o id na posição "0" como retorno

        //aqui abaixo eu chamo o método com os 2 parâmetros necessários, id e token
        putBookingRequest.updateBookingToken(firstId, postAuthRequest.getToken())
                    .then()
                    .statusCode(200)
                    .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, ContractTests.class})
    @DisplayName("Altera uma reserva somente utilizando o Basic auth")
    public void testUpdateABooKingWithBasicAuth(){
        int firstId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .path("[0].bookingid");

        putBookingRequest.updateBookingBasicAuth(firstId)
                .then()
                .statusCode(200)
                .log().all()
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, ContractTests.class})
    @DisplayName("Tenta alterar uma reserva sem token")
    public void testTryUpdateABookingWithoutToken(){
        int firstId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        putBookingRequest.tryUpdateBookingWithoutToken(firstId)
                    .then()
                    .statusCode(403)
                    .time(lessThan(2L), TimeUnit.SECONDS);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, ContractTests.class})
    @DisplayName("Tenta alterar uma reserva com um token inválido")
    public void testTryUpdateABookingWithAInvalidToken(){

        putBookingRequest.updateBookingToken(
                getBookingRequest.returnFirtsId(), "token inválido")
                .then()
                .statusCode(403)
                .time(lessThan(2L), TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Tenta alterar uma reserva que não existe com token")
    public void testTryUpdateABookingThatDoesntExistWithToken(){

        putBookingRequest.updateBookingToken(42, postAuthRequest.getToken())
                .then()
                .statusCode(405)
                .time(lessThan(2L), TimeUnit.SECONDS);
    }
}
