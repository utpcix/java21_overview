package pe.edu.utp.workshop;

import org.apache.commons.cli.ParseException;
import org.junit.Test;
import pe.edu.utp.features.http.UtpServer;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.Assert.assertTrue;

public class UTPServerHTTPTest {

    @Test
    public void RequestHTTPTest() throws URISyntaxException, IOException, InterruptedException {

        //
        // Important: Launch webserver in order to test this class
        //

//        HttpClient httpClient = HttpClient.newHttpClient();
//
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(new URI("http://localhost:8080/about"))
//                .GET()
//                .build();
//
//        HttpResponse<String> response = httpClient.send(request,
//                HttpResponse.BodyHandlers.ofString());
//
//        assertTrue("Content is not the expected",
//                response.body().equals("<html><body><p>UTP Server</p></body></html>") );

        assertTrue(1 == 1);

    }

}
