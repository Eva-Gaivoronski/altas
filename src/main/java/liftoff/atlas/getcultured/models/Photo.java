package liftoff.atlas.getcultured.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Photo {

    @JsonProperty("height")
    private Integer height;
    @JsonProperty("html_attributions")
    private List<String> htmlAttributions;
    @JsonProperty("photo_reference")
    private String photoReference;
    @JsonProperty("width")
    private Integer width;

    public Integer getHeight() {
        return height;
    }

    public List<String> getHtmlAttributions() {
        return htmlAttributions;
    }

    public String getPhotoReference() {
        return photoReference;
    }

    public Integer getWidth() {
        return width;
    }
}
