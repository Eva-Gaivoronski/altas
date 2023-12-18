package liftoff.atlas.getcultured.models;



public class Tours {

    private static int nextId= 1;

    private int id;

    private String tourName;

    private String summaryDescription;

    private Categories category;

    // No-arg constructor that lets the form know about the existence of the fields when the page loads
    public Tours() {
        this.id = nextId;
        nextId++;
    }

    public Tours(String tourName, String summaryDescription, Categories category) {
        this.tourName = tourName;
        this.summaryDescription = summaryDescription;
        this.category = category;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getSummaryDescription() {
        return summaryDescription;
    }

    public void setSummaryDescription(String summaryDescription) {
        this.summaryDescription = summaryDescription;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

}
