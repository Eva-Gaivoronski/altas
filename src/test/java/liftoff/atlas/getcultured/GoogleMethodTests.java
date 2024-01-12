package liftoff.atlas.getcultured;

import liftoff.atlas.getcultured.util.GeocodingResponseParser;
import liftoff.atlas.getcultured.util.ReadJSON;
import net.minidev.json.parser.ParseException;
import org.junit.jupiter.api.Test;

import static java.lang.System.lineSeparator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class GoogleMethodTests extends AbstractTest {

    @Test
    public void cityGeocoderIsSame() throws IOException, ParseException, InterruptedException, ParseException {
        List<Map<String, Object>> resultList = GeocodingResponseParser.parseResults(ReadJSON.readLocalJSONFile("src/test/java/liftoff/atlas/getcultured/PostMapTest.JSON"));

        String mockAddress = "Kansas City, MO, USA";
        Double mockLatitude = 39.0997265;
        Double mockLongitude = -94.5785667;

        for (Map<String, Object> result : resultList) {
//            System.out.println("Formatted Address: " + result.get("Formatted Address"));

            // Accessing latitude and longitude
            Double latitude = (Double) result.get("Latitude");
            Double longitude = (Double) result.get("Longitude");

            // Accessing address
            String formatted_address = (String) result.get("Formatted Address");

            System.out.println("Test 1:");
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

    // Below is a working HTTP GET-Method call to Google Maps Preset:
//        System.out.println("HTTP-GET JSON:");
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://maps.googleapis.com/maps/api/geocode/json?address=KansasCity&region=us&key=ADD_KEY_HERE"))
//                .build();
//
//        HttpResponse<String> response =
//                client.send(request, HttpResponse.BodyHandlers.ofString());
//
//        Object cityJSON = new JSONParser(JSONParser.MODE_JSON_SIMPLE)
//                .parse(response.body());
//        System.out.println(cityJSON);
//        System.out.println("-----");
    // Below is the reading the local JSON file to test against.
//        try {
//            Object testCityJSON = new JSONParser(JSONParser.MODE_JSON_SIMPLE)
//                    .parse(new FileReader("src/test/java/liftoff/atlas/getcultured/PostMapTest.JSON"));
//            System.out.println(testCityJSON);
//            System.out.println("-----");
//            JSONObject testJSONObj = (JSONObject) testCityJSON;
//            JSONArray jsonObj1 = (JSONArray) testJSONObj.get("rows");
//            assertEquals(cityJSON, testCityJSON);
//            Data data = new Gson().fromJson(testCityJSON.toString(), Data.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    // Working original Parser code
//    ObjectMapper objectMapper = new ObjectMapper();
//        JSONObject json1 = readLocalJSONObject("src/test/java/liftoff/atlas/getcultured/PostMapTest.JSON");
//        try {
//            GeocodingResponse geocodingResponse = objectMapper.readValue(json1.toJSONString(), GeocodingResponse.class);
//
//            List<GeocodingResult> results = geocodingResponse.getResults();
//            for (GeocodingResult result : results) {
//                System.out.println("Formatted Address: " + result.getFormattedAddress());
//
//                List<AddressComponent> addressComponents = result.getAddressComponents();
//                for (AddressComponent addressComponent : addressComponents) {
//                    System.out.println("Long Name: " + addressComponent.getLongName());
//                    System.out.println("Short Name: " + addressComponent.getShortName());
//                    System.out.println("Types: " + addressComponent.getTypes());
//                }
//
//                Geometry geometry = result.getGeometry();
//                if (geometry != null) {
//                    Location location = geometry.getLocation();
//                    if (location != null) {
//                        double lat = location.getLat();
//                        double lng = location.getLng();
//                        System.out.println("Latitude: " + lat);
//                        System.out.println("Longitude: " + lng);
//                    }
//                }
//
//                System.out.println("Place ID: " + result.getPlaceId());
//                System.out.println("Types: " + result.getTypes());
//            }
//
//            System.out.println("Status: " + geocodingResponse.getStatus());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

}