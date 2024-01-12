package liftoff.atlas.getcultured.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodingResponse {
    @JsonProperty("results")
    private List<GeocodingResult> results;

    @JsonProperty("status")
    private String status;

    public List<GeocodingResult> getResults() {
        return results;
    }

    public String getStatus() {
        return status;
    }
}

