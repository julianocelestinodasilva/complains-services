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

    private static final String URL = "http://localhost:8080/complains";

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private GsonBuilder gsonBuilder;
    private List<Complain> complains;

    @Before
    public void setUp() throws Exception {
        gsonBuilder = new GsonBuilder();
        complains = given().contentType("application/json").get(URL).thenReturn().getBody().as(List.class);
    }

    @Test
    public void should_ingest_a_complain() throws Exception {
        final Complain complainToIngest = new Complain("Cerveja Quente","cerveja estava quente","Bar do Bira");
        complainToIngest.setLocale("FIXM ME"); // FIXME complainToIngest.setLocale
        logger.log(Level.INFO, URL);
        Response response = given().contentType("application/json").and().body(gsonBuilder.create().toJson(complainToIngest)).post(URL);
        assertEquals(201,response.getStatusCode());
        final int size = complains.size();
        final int complainId = size + 1;
        final String complainURI = URL + "/" + complainId;
        assertEquals(complainURI,response.getHeader("location"));
        final Complain complainIngested = given().contentType("application/json").get(complainURI).thenReturn().getBody().as(Complain.class);
        assertEquals(complainToIngest,complainIngested);
    }
}
