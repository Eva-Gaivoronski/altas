package liftoff.atlas.getcultured;

import liftoff.atlas.getcultured.models.City;
import java.util.*;

import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GoogleMethodTests extends AbstractTest{

    @Test
    @Value("")
    public void testGetCityGeocodeParser () throws IOException, InterruptedException, ParseException {
        // Below is a working HTTP GET-Method call to Google Maps Preset:
        System.out.println("HTTP-GET JSON:");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://maps.googleapis.com/maps/api/geocode/json?address=KansasCity&region=us&key=ADD_KEY_HERE"))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        Object cityJSON = new JSONParser(JSONParser.MODE_JSON_SIMPLE)
                .parse(response.body());
        System.out.println(cityJSON);
        System.out.println("-----");
        // Below is the reading the local JSON file to test against.
        try {
            Object testCityJSON = new JSONParser(JSONParser.MODE_JSON_SIMPLE)
                    .parse(new FileReader("src/test/java/liftoff/atlas/getcultured/PostMapTest.JSON"));
            System.out.println(testCityJSON);
            assertEquals(cityJSON, testCityJSON);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
