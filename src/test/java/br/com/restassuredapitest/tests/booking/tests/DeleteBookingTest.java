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
    @DisplayName("Deletar uma reserva utilizando token")
    @Category({AllTests.class, AcceptanceTests.class})
    public void testDeleteABookingWithToken(){
        int firstId = getBookingRequest.bookingReturnIds()
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        deleteBookingRequest.deleteBooking(firstId, postAuthRequest.getToken())
                .then()
                .statusCode(201)
                .log().all();
    }

//--------------------------------------------------------------------------------------------------------------
    //COMO EU VOU TENTAR EXCLUIR UMA RESERVA QUE NÃO EXISTE SE EU NÃO CONSIGO SELECIONAR UMA PARA EXCLUIR?
    //eu declarando a posição 999 está certo?? sem chamar o método de retornar id?

    @Test
    @DisplayName("Deletar uma reserva que não existe")
    @Category({AllTests.class, EndToEndTests.class})
    public void testDeleteABookingThatDoesntExist(){

        deleteBookingRequest.deleteBooking(999, postAuthRequest.getToken())
                .then()
                .statusCode(405)
                .log().all();
    }
//--------------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Deletar uma reserva sem autorização(token)")
    @Category({AllTests.class, EndToEndTests.class})
    public void testDeleteABookingWithoutAToken(){
        int firstId = getBookingRequest.bookingReturnIds()
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        deleteBookingRequest.deleteBooking(firstId, "")
                .then()
                .statusCode(403)
                .log().all();
    }
}
