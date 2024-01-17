package liftoff.atlas.getcultured.controllers;

import liftoff.atlas.getcultured.util.GeocodingResponseParser;
import liftoff.atlas.getcultured.util.ReadJSON;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("google")
public class GoogleController {



//    getCityGeocode returns several instances of latitude & longitude necessary to conduct
    @PostMapping("/{userId}/city")
    private static void getCityGeocode(String searchTerm, Model model) throws FileNotFoundException, ParseException {

        final String apiKey = "&key="
                + System.getenv("GOOGLE_API_KEY");
        final String uriStart = "https://maps.googleapis.com/maps/api/geocode/json?address=";
        final String uriEnd = "&region=us";
        final String uri = uriStart + searchTerm + uriEnd + apiKey;


        List<Map<String, Object>> resultList = GeocodingResponseParser.parseResults(ReadJSON.readLocalJSONFile(uri));

        String response = "";

        for (Map<String, Object> result : resultList) {

            // Accessing latitude and longitude
            Double latitude = (Double) result.get("Latitude");
            Double longitude = (Double) result.get("Longitude");

            if (latitude != null && longitude != null) {
                response = latitude + String.valueOf(longitude);

            }
        }
        model.addAttribute("location", response);

    }

    @PostMapping("")
    @Value(value = "39.0997265,-94.5785667")
    @ResponseBody
    private static void getStopDetails (@RequestParam String location, String category, String searchTerm ) {

        final String apiKey = "&key="
                + System.getenv("GOOGLE_API_KEY");
        final String uriStart = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=";
        final String radius = "&=5000";
        final String uri = uriStart + searchTerm + "&=" + location + "&=" + category + radius + apiKey;

    }

}
