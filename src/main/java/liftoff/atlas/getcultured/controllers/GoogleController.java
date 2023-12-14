package liftoff.atlas.getcultured.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class GoogleController {

//    getCityGeocode returns several instances of latitude & longitude necessary to conduct
    @PostMapping("results")
    private static void getCityGeocode(String searchTerm)
    {
        final String apiKey = "&key=" + "ENV_APIKEY_HERE";
        final String uriStart = "https://maps.googleapis.com/maps/api/geocode/json?address=";
        final String uriEnd = "&region=us";
        final String uri = uriStart + searchTerm + uriEnd + apiKey;

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);
    }

//    getCityStops takes in the searchType (Restaurant, Bar, Venue, Museum, etc.) and the longitude and latitude (getCityGeocode()) with a hard-coded radius that produces a JSON of places.
    @PostMapping("stops")
    private static void getCityStops(@RequestParam String searchType, @RequestParam(required = true) String lnglat) {

        final String apiKey = "&key=" + "ENV_APIKEY_HERE";
        final String uriStart = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=";
        final String uriEnd = "&" + lnglat;
        final String uri = uriStart + searchType + uriEnd + "&radius=250" + apiKey;

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);
    }


}
