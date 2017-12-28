package julianocelestino.complainsservices.integratedtests;

import io.restassured.response.Response;
import julianocelestino.complainsservices.Complain;
import org.junit.Test;
import com.google.gson.GsonBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;

/**
 * Created by juliano on 27/12/17.
 */

public class ComplainsServicesTest {

    private static final String URL = "http://localhost:8080/";

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private GsonBuilder gsonBuilder;

    @Test
    public void should_ingest_a_complain() throws Exception {

        final String url = URL + "complain";
        final Complain complainToIngest = new Complain("Cerveja Quente","cerveja estava quente","Bar do Bira");
        logger.log(Level.INFO, url);
        Response response = given().contentType("application/json").and().body(gsonBuilder.create().toJson(complainToIngest)).post(url);


    }
}
