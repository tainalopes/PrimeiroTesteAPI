package br.com.restassuredapitest.tests.auth.requests;

import br.com.restassuredapitest.tests.auth.requests.payloads.AuthPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class PostAuthRequest {
    AuthPayloads authPayloads = new AuthPayloads();
    //NÃO ESQUECER DE CHAMAR AS CLASSES PARA TER ACESSO AOS MÉTODOS

    @Step("Retorna o token")
    public Response tokenReturn(){

        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(authPayloads.jsonAuthLogin().toString()) //chama o jsonobject lá do payload
                .post("auth");
    }

    @Step("Busca o token")
    public String getToken(){
        return "token="+this.tokenReturn()//a chave token recebe o tokenReturn, que é o método acima que pega o token
                .then()
                .statusCode(200)
                .extract()
                .path("token");
    }

}
