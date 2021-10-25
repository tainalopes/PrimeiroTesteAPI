package br.com.restassuredapitest.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.Validatable;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {

    @Step("Retorna os ids da listagem de reservas")
    public Response bookingReturnIds() {
        return given()
                .when()
                .get("booking"); //a url inicial está em "BaseTest", aqui vai somente o resto do caminho
    }

    @Step("Retorna a primeira reserva cadastrada")
    public Response bookingReturnFirstId() {
        int primeiroId = returnFirtsId();

        return given()
                .when()
                .get("booking/" + primeiroId);
    }

    public int returnFirtsId() {
        return bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");
    }

    @Step("Retorna um id que não existe")
    public Response bookingDontExist(){
        int idDontExist = returnIdDontExist();
        return given()
                .when()
                .get("booking/" + idDontExist);
    }
    public int returnIdDontExist(){
        return bookingDontExist()
                .then()
                .statusCode(405)
                .extract()
                .path("[666].bookingid");
    }

    @Step("Retorna lista de ids com filtros")
    public Response bookingReturnIdsByFilter(String filter1, String filterValue1,
                                             String filter2, String filterValue2,
                                             String filter3, String filterValue3,
                                             String filter4, String filterValue4)
    {
        return given()
                .queryParams(filter1, filterValue1,
                        filter2, filterValue2,
                        filter3, filterValue3,
                        filter4, filterValue4)
                .when()
                .get("booking");
    }
}