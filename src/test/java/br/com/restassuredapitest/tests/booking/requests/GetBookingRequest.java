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
        return given()
                .when()
                .get("booking?checkout=2018-11-02&checkout=2018-11-02");
    }

    @Step("Retorna lista de ids com filtro de nome, sobrenome, checkin e ckechout")
    public Response bookingReturnIdsByFilterWithFirstnameAndLastnameAndChekinAndCheckout(){
        return given()
                .when()
                .get("booking?firstname=Nemo&lastname=Nobody&checkin=2018-07-03&checkout=2018-11-02");
    }
}