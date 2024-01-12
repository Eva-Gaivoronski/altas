package liftoff.atlas.getcultured.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Geometry {
    @JsonProperty("bounds")
    private Bounds bounds;

    @JsonProperty("location")
    private Location location;

    @JsonProperty("location_type")
    private String locationType;

    @JsonProperty("viewport")
    private Viewport viewport;

    public Bounds getBounds() {
        return bounds;
    }

    public Location getLocation() {
        return location;
    }

    public String getLocationType() {
        return locationType;
    }

    public Viewport getViewport() {
        return viewport;
    }
}

