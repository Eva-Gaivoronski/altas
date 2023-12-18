package liftoff.atlas.getcultured.models;

public enum Categories {

    FEATURED("Featured"),
    POPULAR("Popular"),
    EDITORS_CHOICE("Editor's Choice"),
    FAMILY_FRIENDLY("Family Friendly"),
    LOW_KEY("Low Key");

    private final String displayName;

    Categories(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
