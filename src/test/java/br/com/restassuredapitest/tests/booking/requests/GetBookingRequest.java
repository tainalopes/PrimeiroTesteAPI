package br.com.restassuredapitest.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class GetBookingRequest {

    @Step("Retorna todos os ids da listagem de reservas")
    public Response bookingReturnIds() {
        return given()
                .when()
                .get("booking");
    }

    @Step("Retorna a reserva cadastrada na primeira posição do array")
    public Response bookingReturnFirstId() {
        int primeiroId = returnFirtsId();
        return given()
                .when()
                .get("booking/" + primeiroId);
    }

    @Step("Busca a primeira posição do array de reserva")
    public int returnFirtsId() {
        return bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");
    }

    @Step("Retorna lista de ids com 4 filtros")
    public Response bookingReturnIdsByFilter(String filter1, String filterValue1,
                                             String filter2, String filterValue2,
                                             String filter3, String filterValue3,
                                             String filter4, String filterValue4)
    {
        return given().log().all()
                .queryParams(filter1, filterValue1, filter2, filterValue2,
                        filter3, filterValue3, filter4, filterValue4)
                .when()
                .get("booking");
    }

    @Step("Retorna lista de ids com 1 filtro")
    public Response bookingReturnIdsByFilter(String filter1, String filterValue1)
    {
        return given().log().all()
                .queryParams(filter1, filterValue1)
                .when()
                .get("booking");
    }

    @Step("Retorna lista de ids com filtro de checkout 2x")
    public Response bookingReturnIdsByFilterWithDoubleCheckouts(){
        return given().log().all()
                .when()
                .get("booking?checkout=2014-05-21&checkout=2014-05-21");
    }
}