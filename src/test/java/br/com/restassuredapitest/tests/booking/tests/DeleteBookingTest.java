package br.com.restassuredapitest.tests.booking.tests;

import br.com.restassuredapitest.base.BaseTest;
import br.com.restassuredapitest.suites.AcceptanceTests;
import br.com.restassuredapitest.suites.AllTests;
import br.com.restassuredapitest.suites.EndToEndTests;
import br.com.restassuredapitest.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitest.tests.booking.requests.DeleteBookingRequest;
import br.com.restassuredapitest.tests.booking.requests.GetBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Feature("Feature - exclusão de reservas")
public class DeleteBookingTest extends BaseTest {
    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();
    DeleteBookingRequest deleteBookingRequest = new DeleteBookingRequest();

    @Test
    @DisplayName("Deletar uma reserva")
    @Category({AllTests.class, AcceptanceTests.class})
    public void testDeletaUmaReservaUtilizandoToken(){
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        deleteBookingRequest.deleteBooking(primeiroId, postAuthRequest.getToken())
                .then()
                .statusCode(201)
                .log().all();
    }


    //COMO EU VOU TENTAR EXCLUIR UMA RESERVA QUE NÃO EXISTE SE EU NÃO CONSIGO SELECIONAR UMA PARA EXCLUIR?

//    @Test
//    @DisplayName("Deletar uma reserva que não existe")
//    @Category({AllTests.class, EndToEndTests.class})
//    public void testDeleteABookingThatDoesntExist(){
//        int invalidId = getBookingRequest.bookingReturnIds()
//                .then()
//                .log().all()
//                .statusCode(200)
//                .extract()
//                .path("[999].bookingid");
//
//        deleteBookingRequest.deleteBooking(invalidId, postAuthRequest.getToken())
//                .then()
//                .statusCode(405)
//                .log().all();
//
//    }

    @Test
    @DisplayName("Deletar uma reserva sem autorização")
    @Category({AllTests.class, EndToEndTests.class})
    public void testDeletaABookingWithNoToken(){
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        deleteBookingRequest.deleteBooking(primeiroId, "")
                .then()
                .statusCode(403)
                .log().all();
    }
}
