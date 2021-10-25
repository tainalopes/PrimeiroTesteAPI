package br.com.restassuredapitest.tests.auth.tests;

import br.com.restassuredapitest.base.BaseTest;
import br.com.restassuredapitest.suites.AllTests;
import br.com.restassuredapitest.suites.SmokeTests;
import br.com.restassuredapitest.tests.auth.requests.PostAuthRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.CoreMatchers.notNullValue;

@Feature("Feature - autenticação de usuário")
public class PostAuthTest extends BaseTest {

    PostAuthRequest postAuthRequest = new PostAuthRequest(); //instanciando a classe para poder chamar no teste.

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Retorna o token para o usuário")
    public void testValidateTokenReturnForUser(){
        postAuthRequest.tokenReturn() //chamando o método de retorno do token
                .then()
                .statusCode(200)
                .body("token", notNullValue()); //fazendo a validação que esse token não pode ser nulo
    }
}
