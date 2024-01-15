package liftoff.atlas.getcultured.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liftoff.atlas.getcultured.models.*;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PlacesResponseParser {

    public static List<Map<String, Object>> parseResults(String jsonResponse) {
        List<Map<String, Object>> resultList = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            PlacesResponse placesResponse = objectMapper.readValue(jsonResponse, PlacesResponse.class);

            String status = placesResponse.getStatus();
            if ("OK".equals(status)) {
                List<PlacesResult> results = placesResponse.getResults();
                for (PlacesResult result : results) {
                    Map<String, Object> resultInfo = new HashMap<>();

                    resultInfo.put("Place ID", result.getPlaceId());
                    resultInfo.put("Name", result.getName());
                    resultInfo.put("Address", result.getFormattedAddress());
                    resultInfo.put("Rating", result.getRating());
                    resultInfo.put("Price Level", result.getPriceLevel());

                    List<String> types = result.getTypes();
                    resultInfo.put("Types", types);

                    Geometry geometry = result.getGeometry();
                    if (geometry != null) {
                        Location location = geometry.getLocation();
                        if (location != null) {
                            resultInfo.put("Latitude", location.getLat());
                            resultInfo.put("Longitude", location.getLng());
                        }
                    }

                    resultList.add(resultInfo);

                }
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }
}
