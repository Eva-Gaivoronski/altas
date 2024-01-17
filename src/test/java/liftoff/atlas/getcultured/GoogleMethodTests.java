package liftoff.atlas.getcultured;

import com.fasterxml.jackson.databind.ObjectMapper;
import liftoff.atlas.getcultured.models.GeocodingResponse;
import liftoff.atlas.getcultured.util.GeocodingResponseParser;
import liftoff.atlas.getcultured.util.PlacesResponseParser;
import liftoff.atlas.getcultured.util.ReadJSON;
import net.minidev.json.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class GoogleMethodTests extends AbstractTest {

    @Test
    public void givenGeocodingRequestWithNoAcceptHeader_whenRequestIsExecuted_thenDefaultResponseContentTypeIsJson() {
        System.out.println("-----\nTest - givenGeocodingRequestWithNoAcceptHeader_whenRequestIsExecuted_thenDefaultResponseContentTypeIsJson");

        final String apiKey = "&key="
                + System.getenv("GOOGLE_API_KEY");
        final String urlStart = "https://maps.googleapis.com/maps/api/geocode/json?address=";
        final String urlEnd = "&region=us";
        final String city = "KansasCity";
        final String url = urlStart + city + urlEnd + apiKey;

        // Given
        String jsonMimeType = "application/json";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            // When
            HttpResponse<String> res = client.send(request, HttpResponse.BodyHandlers.ofString());

            Map<String, List<String>> resMap = res.headers().map();
            List<String> resContentType = resMap.get("content-type");
            for (String o : resContentType) {
                // Then
                String[] mimeType = o.split(";");
                assertEquals(jsonMimeType, mimeType[0]);
                System.out.println("Expected: " + jsonMimeType + "\nActual: " + mimeType[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenPlacesRequestWithNoAcceptHeader_whenRequestIsExecuted_thenDefaultResponseContentTypeIsJson(){
        System.out.println("-----\nTest - givenPlacesRequestWithNoAcceptHeader_whenRequestIsExecuted_thenDefaultResponseContentTypeIsJson");

        final String apiKey = "&key="
                + System.getenv("GOOGLE_API_KEY");
        final String uriStart = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=";
        final String radius = "&=5000";
        final String searchTerm = "tattooedmom";
        final String locale = "39.9533116,-75.1703448";
        final String category = "restaurant";
        final String url = uriStart + searchTerm + "&=" + locale + "&=" + category + radius + apiKey;

        // Given
        String jsonMimeType = "application/json";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            // When
            HttpResponse<String> res = client.send(request, HttpResponse.BodyHandlers.ofString());

            Map<String, List<String>> resMap = res.headers().map();
            List<String> resContentType = resMap.get("content-type");
            for (String o : resContentType) {
                // Then
                String[] mimeType = o.split(";");
                assertEquals(jsonMimeType, mimeType[0]);
                System.out.println("Expected: " + jsonMimeType + "\nActual: " + mimeType[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fetchGeocodingResponseDoesNotExists () throws IOException, ParseException {
        final String apiKey = "&key="
                + System.getenv("GOOGLE_API_KEY");
        final String urlStart = "https://maps.googleapis.com/maps/api/geocode/json?address=";
        final String city = "RomdeauCity";
        final String url = urlStart + city + apiKey;

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Given
            GeocodingResponse geocodingResponse = objectMapper.readValue(ReadJSON.fetchJSON(url), GeocodingResponse.class);

            // When
            String status = geocodingResponse.getStatus();

            //Then
            assertEquals("ZERO_RESULTS", status);
            System.out.println("-----\nTest - fetchGeocodingResponseDoesNotExists");
            System.out.println("Expected: ZERO_RESULTS" + "\nActual: " + status);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void fetchPlacesTest() throws IOException, ParseException, InterruptedException {
        System.out.println("-----\nTest - fetchPlacesTest");

        final String apiKey = "&key="
                + System.getenv("GOOGLE_API_KEY");
        final String uriStart = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=";
        final String radius = "&=5000";
        final String searchTerm = "tattooedmom";
        final String locale = "39.9533116,-75.1703448";
        final String category = "restaurant";
        final String uri = uriStart + searchTerm + "&=" + locale + "&=" + category + radius + apiKey;

        String mockName = "Tattooed Mom";
        String mockAddress = "530 South St, Philadelphia, PA 19147, United States";
        String mockType = "restaurant";

        List<Map<String, Object>> resultList = PlacesResponseParser.parseResults(ReadJSON.fetchJSON(uri));

        Map <Integer, Object> formattedList = new HashMap<>();
        int k = 0;

        for (Map<String, Object> result : resultList) {
            String name = (String) result.get("Name");
            String address = (String) result.get("Address");
            Double rating = (Double) result.get("Rating");
            Integer priceLevel = (Integer) result.get("Price Level");
            Double latitude = (Double) result.get("Latitude");
            Double longitude = (Double) result.get("Longitude");
            List types = (List) result.get("Types");

            String[] location = new String[0];

            if (latitude != null && longitude != null) {
                location = new String[]{String.valueOf(latitude), String.valueOf(longitude)};

            }

            Map<String, Object> formattedResult = new HashMap<>();
            formattedResult.put("Name", name);
            formattedResult.put("Address", address);
            formattedResult.put("Types", types);
            formattedResult.put("Rating", rating);
            formattedResult.put("Price Level", priceLevel);
            // For original data type:
            formattedResult.put("Location", location);
            // For readability:
//            formattedResult.put("Location", Arrays.toString(location));

            formattedList.put(k, formattedResult);
            k++;
        }

        Map firstResult = (Map) formattedList.get(0);
        System.out.println("Expected: " + mockName);
        System.out.println("Actual: " + firstResult.get("Name"));
        assertEquals(mockName, firstResult.get("Name"));

        System.out.println("Expected: " + mockAddress);
        System.out.println("Actual: " + firstResult.get("Address"));
        assertEquals(mockAddress, firstResult.get("Address"));

        System.out.println("Contains: " + mockType + "\nExpected: true");
        List types = (List) firstResult.get("Types");
        System.out.println("Actual: " + types.contains(mockType));
        assertTrue(types.contains(mockType));
    }

    @Test
    public void cityPlacesIsSame() throws IOException, ParseException {
        List<Map<String, Object>> resultList = PlacesResponseParser.parseResults(ReadJSON.readLocalJSONFile("src/test/java/liftoff/atlas/getcultured/PlacesTest.JSON"));

        System.out.println("-----");
        System.out.println("Test - cityPlacesIsSame");
        Map <Integer, Object> formattedList = new HashMap<>();
        int k = 0;

        String mockName = "Harper's Garden";
        String mockAddress = "31 S 18th St, Philadelphia, PA 19103, United States";
        String mockType = "restaurant";

        for (Map<String, Object> result : resultList) {
            String name = (String) result.get("Name");
            String address = (String) result.get("Address");
            Double rating = (Double) result.get("Rating");
            Integer priceLevel = (Integer) result.get("Price Level");
            Double latitude = (Double) result.get("Latitude");
            Double longitude = (Double) result.get("Longitude");
            List types = (List) result.get("Types");

            String[] location = new String[0];

            if (latitude != null && longitude != null) {
                location = new String[]{String.valueOf(latitude), String.valueOf(longitude)};

            }
            // Flattening properties of result objects to be more accessible
            Map<String, Object> formattedResult = new HashMap<>();
            formattedResult.put("Name", name);
            formattedResult.put("Address", address);
            formattedResult.put("Types", types);
            formattedResult.put("Rating", rating);
            formattedResult.put("Price Level", priceLevel);
            // For original data type:
            formattedResult.put("Location", location);
            // For readability:
//            formattedResult.put("Location", Arrays.toString(location));

            formattedList.put(k, formattedResult);
            k++;
        }

        Map firstResult = (Map) formattedList.get(0);
        System.out.println("Expected: " + mockName);
        System.out.println("Actual: " + firstResult.get("Name"));
        assertEquals(mockName, firstResult.get("Name"));

        System.out.println("Expected: " + mockAddress);
        System.out.println("Actual: " + firstResult.get("Address"));
        assertEquals(mockAddress, firstResult.get("Address"));

        System.out.println("Contains: " + mockType + "\nExpected: true");
        List types = (List) firstResult.get("Types");
        System.out.println("Actual: " + types.contains(mockType));
        assertTrue(types.contains(mockType));
    }

    @Test
    public void fetchGeocodeTest() throws IOException, ParseException, InterruptedException {
        System.out.println("-----\nTest - fetchGeocodeTest");
        final String apiKey = "&key="
                + System.getenv("GOOGLE_API_KEY");
        final String urlStart = "https://maps.googleapis.com/maps/api/geocode/json?address=";
        final String urlEnd = "&region=us";
        final String city = "KansasCity";
        final String url = urlStart + city + urlEnd + apiKey;

        List<Map<String, Object>> resultList = GeocodingResponseParser.parseResults(ReadJSON.fetchJSON(url));

        String mockAddress = "Kansas City, MO, USA";
        Double mockLatitude = 39.0997265;
        Double mockLongitude = -94.5785667;

        for (Map<String, Object> result : resultList) {

            Double latitude = (Double) result.get("Latitude");
            Double longitude = (Double) result.get("Longitude");

            String formatted_address = (String) result.get("Formatted Address");

            try {
                assertEquals(mockAddress, formatted_address, "Address is the same as mock");
                assertEquals(mockLatitude, latitude, "Latitude is the same as mock");
                assertEquals(mockLongitude, longitude, "Longitude is the same as mock");
            } catch (Exception e) {
                fail("City geocode information does not match");
            }
            System.out.println("Expected: " + mockAddress);
            System.out.println("Actual: " + formatted_address);
            System.out.println("Expected: " + mockLatitude);
            System.out.println("Actual: " + latitude);
            System.out.println("Expected: " + mockLongitude);
            System.out.println("Actual: " + longitude);

        }
    }

    @Test
    public void cityGeocoderIsSame() throws IOException, ParseException {
        System.out.println("-----\nTest - cityGeocoderIsSame");
        List<Map<String, Object>> resultList = GeocodingResponseParser.parseResults(ReadJSON.readLocalJSONFile("src/test/java/liftoff/atlas/getcultured/PostMapTest.JSON"));

        String mockAddress = "Kansas City, MO, USA";
        Double mockLatitude = 39.0997265;
        Double mockLongitude = -94.5785667;


        // Accessing each result
        for (Map<String, Object> result : resultList) {

            // Accessing latitude and longitude
            Double latitude = (Double) result.get("Latitude");
            Double longitude = (Double) result.get("Longitude");

            // Accessing address
            String formatted_address = (String) result.get("Formatted Address");

            try {
                assertEquals(mockAddress, formatted_address, "Address is the same as mock");
                assertEquals(mockLatitude, latitude, "Latitude is the same as mock");
                assertEquals(mockLongitude, longitude, "Longitude is the same as mock");
            } catch (Exception e) {
                fail("City geocode information does not match");
            }
            System.out.println("Expected: " + mockAddress);
            System.out.println("Actual: " + formatted_address);
            System.out.println("Expected: " + mockLatitude);
            System.out.println("Actual: " + latitude);
            System.out.println("Expected: " + mockLongitude);
            System.out.println("Actual: " + longitude);
        }
    }

    /* HTTP GET-Method call to Google Maps Geocoding Format:
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
     Below is the reading the local JSON file to test against.
        try {
            Object testCityJSON = new JSONParser(JSONParser.MODE_JSON_SIMPLE)
                    .parse(new FileReader("src/test/java/liftoff/atlas/getcultured/PostMapTest.JSON"));
            System.out.println(testCityJSON);
            System.out.println("-----");
            JSONObject testJSONObj = (JSONObject) testCityJSON;
            JSONArray jsonObj1 = (JSONArray) testJSONObj.get("rows");
            assertEquals(cityJSON, testCityJSON);
            Data data = new Gson().fromJson(testCityJSON.toString(), Data.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

     Working original Parser code
    ObjectMapper objectMapper = new ObjectMapper();
        JSONObject json1 = readLocalJSONObject("src/test/java/liftoff/atlas/getcultured/PostMapTest.JSON");
        try {
            GeocodingResponse geocodingResponse = objectMapper.readValue(json1.toJSONString(), GeocodingResponse.class);

            List<GeocodingResult> results = geocodingResponse.getResults();
            for (GeocodingResult result : results) {
                System.out.println("Formatted Address: " + result.getFormattedAddress());

                List<AddressComponent> addressComponents = result.getAddressComponents();
                Geometry geometry = result.getGeometry();
                if (geometry != null) {
                    Location location = geometry.getLocation();
                    if (location != null) {
                        double lat = location.getLat();
                        double lng = location.getLng();
                    }
                } */

}