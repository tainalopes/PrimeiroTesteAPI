package br.com.restassuredapitest.tests.booking.payloads;

import org.json.JSONObject;

public class BookingPayloads{
    public JSONObject payloadValidBooking(){
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin","2018-07-04");
        bookingDates.put("checkout", "2018-11-02");

        payload.put("firstname", "Nemo");
        payload.put("lastname", "Nobody");
        payload.put("totalprice", 999);
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "beer");

        return payload;
    }

    public JSONObject payloadWithMoreParameters(){
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin","2018-07-04");
        bookingDates.put("checkout", "2018-11-02");

        payload.put("firstname", "Nemo");
        payload.put("lastname", "Nobody");
        payload.put("totalprice", 999);
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "beer");

        payload.put("extra1", "blablabla");
        payload.put("extra2", 888);

        return payload;
    }

    public static JSONObject payloadCreateInvalidParameters() {
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin","2018-01-01");
        bookingDates.put("checkout", "2019-01-01");

        payload.put("firstname", 789);
        payload.put("lastname", 987);

        payload.put("totalprice", "String hehehe");
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", 12.5);

        return payload;
    }
}
