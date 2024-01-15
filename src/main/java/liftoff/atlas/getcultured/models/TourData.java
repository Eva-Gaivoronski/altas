package liftoff.atlas.getcultured.models;

import java.util.ArrayList;

public class TourData {

    /*
    Returns the results of searching the Tours data by field and search term

    For example, searching for city "Philadelphia" will include results with tours created in Philadelphia.

    * @param column Tour field that should be searched.
    * @param value Value of the field to search for.
    * @param allTours The list of tours to search.
    * @return List of all Tours matching the criteria.

    */

    public static ArrayList<Tour> findByColumnAndValue(String column, String value, Iterable<Tour> allTours) {

        ArrayList<Tour> results = new ArrayList<>();

        if (value.toLowerCase().equals("all")) {
            return (ArrayList<Tour>) allTours;
        }

        if (column.equals("all")) {
            results = findByValue(value, allTours);
            return results;
        }
        for (Tour tour : allTours) {
            String aValue = getFieldValue(tour, column);

            if (aValue != null && aValue.toLowerCase().contains((value.toLowerCase()))) {
                results.add(tour);
            }
        }
        return results;
    }

    public static String getFieldValue(Tour tour, String fieldName) {
        String theValue;
        if (fieldName.equals("name")) {
            theValue = tour.getTourName();
        } else if (fieldName.equals("city")) {
            theValue = tour.getCity().toString();
        } else {
            theValue = tour.getTourCategory().toString();
        }
        return theValue;
    }

    /*
    Search all Tour fields for the given term

    * @param value The search term to look for.
    * @param allTours The list of tours to search.
    * @return List of all tours with at least one field containing the value.

     */

    public static ArrayList<Tour> findByValue(String value, Iterable<Tour> allTours) {

        ArrayList<Tour> results = new ArrayList<>();

        for (Tour tour : allTours) {

            if (tour.getTourName().toLowerCase().contains(value.toLowerCase())) {
                results.add(tour);
            } else if (tour.getCity().toString().toLowerCase().contains(value.toLowerCase())) {
                results.add(tour);
            } else if (tour.getTourCategory().toString().toLowerCase().contains(value.toLowerCase())) {
                results.add(tour);
            }

        }
        return results;
    }


}
