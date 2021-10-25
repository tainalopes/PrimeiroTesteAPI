package br.com.restassuredapitest.tests.auth.requests.payloads;

import org.json.JSONObject;

public class AuthPayloads {
    public JSONObject jsonAuthLogin(){
        JSONObject payloadLogin = new JSONObject(); //aqui eu crio um objeto do body com os valores
        payloadLogin.put("username", "admin"); //estou dizendo que o valor de username é admin
        payloadLogin.put("password", "password123"); //e o valor de password é password123
        return payloadLogin;
    }

}
