package br.com.restassuredapitest.base;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class BaseTest {

    @BeforeClass
    public static void setup(){
        RestAssured.baseURI = "https://treinamento-api.herokuapp.com/";
        //toda vez que um teste for executado, ele vai iniciar com essa url aqui.
        //necessário estender a classe "BaseTest" em todas as classes de testes.
    }
}
