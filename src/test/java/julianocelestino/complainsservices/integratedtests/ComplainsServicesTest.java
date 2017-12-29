package julianocelestino.complainsservices.integratedtests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import julianocelestino.complainsservices.Complain;
import org.junit.Before;
import org.junit.Test;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

/**
 * Created by juliano on 27/12/17.
 */

public class ComplainsServicesTest {

    private static final String URL = "http://localhost:8080/complains"; // TODO http://172.23.0.2:8080/complains


    @Test
    public void should_return_complains_from_specific_company_in_specific_city () throws Exception {
    }

    @Test
    public void should_ingest_a_complain() throws Exception {
        List<Complain> complains = given().contentType("application/json").get(URL).thenReturn().getBody().as(List.class);
        final Complain complainToIngest = new Complain("Hamburguer queimado","Hamburguer estava queimado","Rock Burguer");
        Response response = given().contentType("application/json").and().body(new GsonBuilder().create().toJson(complainToIngest)).post(URL);
        assertEquals(201,response.getStatusCode());
        final int size = complains.size();
        final int complainId = size + 1;
        final String complainURI = URL + "/" + complainId;
        assertEquals(complainURI,response.getHeader("location"));
        final Complain complainIngested = given().contentType("application/json").get(complainURI).thenReturn().getBody().as(Complain.class);
        complainToIngest.setLocale("AddressNotFound"); // TODO Because my ip is 127.0.0.1, and it can't find in GeoLite2-City.mmdb
        assertEquals(complainToIngest,complainIngested);
    }

    @Test
    public void should_return_bad_request_for_invalid_complain() throws Exception {
        final Complain complainToIngest = new Complain("Hamburguer queimado","Hamburguer estava queimado","");
        Response response = given().contentType("application/json").and().body(new GsonBuilder().create().toJson(complainToIngest)).post(URL);
        assertEquals(400,response.getStatusCode());

    }
}
