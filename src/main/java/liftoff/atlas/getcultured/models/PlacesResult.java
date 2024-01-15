package liftoff.atlas.getcultured.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PlacesResult {

    @JsonProperty("business_status")
    private String businessStatus;

    @JsonProperty("formatted_address")
    private String formattedAddress;

    @JsonProperty("geometry")
    private Geometry geometry;

    @JsonProperty("icon")
    private String icon;

    @JsonProperty("icon_background_color")
    private String iconBackgroundColor;

    @JsonProperty("icon_mask_base_uri")
    private String iconMaskBaseUri;

    @JsonProperty("name")
    private String name;

    @JsonProperty("opening_hours")
    private OpeningHours openingHours;

    @JsonProperty("photos")
    private List<Photo> photos;

    @JsonProperty("place_id")
    private String placeId;

    @JsonProperty("plus_code")
    private PlusCode plusCode;

    @JsonProperty("price_level")
    private Integer priceLevel;

    @JsonProperty("rating")
    private Double rating;

    @JsonProperty("reference")
    private String reference;

    @JsonProperty("types")
    private List<String> types;

    @JsonProperty("user_ratings_total")
    private Integer userRatingsTotal;

    public String getBusinessStatus() {
        return businessStatus;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public String getIcon() {
        return icon;
    }

    public String getIconBackgroundColor() {
        return iconBackgroundColor;
    }

    public String getIconMaskBaseUri() {
        return iconMaskBaseUri;
    }

    public String getName() {
        return name;
    }

    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public String getPlaceId() {
        return placeId;
    }

    public PlusCode getPlusCode() {
        return plusCode;
    }

    public Integer getPriceLevel() {
        return priceLevel;
    }

    public Double getRating() {
        return rating;
    }

    public String getReference() {
        return reference;
    }

    public List<String> getTypes() {
        return types;
    }

    public Integer getUserRatingsTotal() {
        return userRatingsTotal;
    }
}
