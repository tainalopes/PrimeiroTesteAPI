package br.com.restassuredapitest.tests.auth.tests;

import br.com.restassuredapitest.base.BaseTest;
import br.com.restassuredapitest.suites.AllTests;
import br.com.restassuredapitest.tests.auth.requests.PostAuthRequest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.CoreMatchers.notNullValue;

public class PostAuthTest extends BaseTest {

    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Test
    @Category(AllTests.class)
    public void validaRetornoDeTokenParaUsuario(){
        postAuthRequest.tokenReturn()
                .then()
                .statusCode(200)
                .body("token", notNullValue());
    }
}
