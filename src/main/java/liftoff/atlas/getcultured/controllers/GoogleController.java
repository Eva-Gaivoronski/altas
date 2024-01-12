package liftoff.atlas.getcultured.controllers;

import liftoff.atlas.getcultured.util.GeocodingResponseParser;
import liftoff.atlas.getcultured.util.ReadJSON;
import net.minidev.json.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;


@Controller
public class GoogleController {



//    getCityGeocode returns several instances of latitude & longitude necessary to conduct
    @PostMapping("/city")
    private static String[] getCityGeocode(String searchTerm) throws FileNotFoundException, ParseException {

        final String apiKey = "&key=\n"
                + "ENV_APIKEY_HERE";
        final String uriStart = "https://maps.googleapis.com/maps/api/geocode/json?address=";
        final String uriEnd = "&region=us";
        final String uri = uriStart + searchTerm + uriEnd + apiKey;


        List<Map<String, Object>> resultList = GeocodingResponseParser.parseResults(ReadJSON.readLocalJSONFile(uri).toString());

        String[] response = new String[0];

        for (Map<String, Object> result : resultList) {

            // Accessing latitude and longitude
            Double latitude = (Double) result.get("Latitude");
            Double longitude = (Double) result.get("Longitude");

            if (latitude != null && longitude != null) {
                response = new String[]{String.valueOf(latitude), String.valueOf(longitude)};

            }
        }
        return response;
    }

}
