package liftoff.atlas.getcultured.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlacesResponse {

    @JsonProperty("html_attributions")
    private List<Object> htmlAttributions;

    @JsonProperty("next_page_token")
    private String nextPageToken;

    @JsonProperty("results")
    private List<PlacesResult> results;

    @JsonProperty("status")
    private String status;

    public List<Object> getHtmlAttributions() {
        return htmlAttributions;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public List<PlacesResult> getResults() {
        return results;
    }

    public String getStatus() {
        return status;
    }
}
