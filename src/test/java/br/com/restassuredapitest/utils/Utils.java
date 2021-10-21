package br.com.restassuredapitest.utils;

public class Utils {
    public static String getSchemaBasePath(String pack, String nameSchema){

        return System.getProperty("user.dir")
                + "/src/test/java/br/com/restassuredapitest/tests/"
                + pack
                + "/schema/"
                + nameSchema
                + ".json";

    }
}
