package julianocelestino.complainsservices.integratedtests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import julianocelestino.complainsservices.Complain;
import org.junit.Before;
import org.junit.Test;
import com.google.gson.GsonBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

/**
 * Created by juliano on 27/12/17.
 */

public class ComplainsServicesTest {

    private static final String URL = "http://172.23.0.2:8080/";

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private GsonBuilder gsonBuilder;

    @Before
    public void setUp() throws Exception {
        gsonBuilder = new GsonBuilder();
    }

    @Test
    public void should_ingest_a_complain() throws Exception {

        final String url = URL + "complain";
        final Complain complainToIngest = new Complain("Cerveja Quente","cerveja estava quente","Bar do Bira");
        complainToIngest.setLocale("FIXM ME"); // FIXME complainToIngest.setLocale

        logger.log(Level.INFO, url);
        Response response = given().contentType("application/json").and().body(gsonBuilder.create().toJson(complainToIngest)).post(url);

        logger.log(Level.INFO, "response.getStatusCode() --> " + response.getStatusCode());

        logger.log(Level.INFO, "response --> " + response.getHeaders().getValue("location"));


    }
}
