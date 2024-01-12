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

public class GeocodingResponseParser {

    public static List<Map<String, Object>> parseResults(String jsonResponse) {
        List<Map<String, Object>> resultList = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            GeocodingResponse geocodingResponse = objectMapper.readValue(jsonResponse, GeocodingResponse.class);

            String status = geocodingResponse.getStatus();
            if ("OK".equals(status)) {
                List<GeocodingResult> results = geocodingResponse.getResults();
                for (GeocodingResult result : results) {
                    Map<String, Object> resultInfo = new HashMap<>();
                    resultInfo.put("Formatted Address", result.getFormattedAddress());

                    List<AddressComponent> addressComponents = result.getAddressComponents();
                    for (AddressComponent addressComponent : addressComponents) {
                        resultInfo.put("Long Name", addressComponent.getLongName());
                        resultInfo.put("Short Name", addressComponent.getShortName());
                        resultInfo.put("Types", addressComponent.getTypes());
                    }

                    Geometry geometry = result.getGeometry();
                    if (geometry != null) {
                        Location location = geometry.getLocation();
                        if (location != null) {
                            resultInfo.put("Latitude", location.getLat());
                            resultInfo.put("Longitude", location.getLng());
                        }
                    }

                    resultInfo.put("Place ID", result.getPlaceId());
                    resultInfo.put("Types", result.getTypes());

                    resultList.add(resultInfo);
                }
            } else {
                System.out.println("Geocoding request failed with status: " + status);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultList;
    }
    }