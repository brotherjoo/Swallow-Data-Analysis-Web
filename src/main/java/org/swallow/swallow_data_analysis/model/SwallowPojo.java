package org.swallow.swallow_data_analysis.model;

import lombok.Data;

@Data
public class SwallowPojo {

    private Long id;
    private int number;
    private String date;
    private double longitude;
    private double latitude;
    private double longitudeDifference;
    private double latitudeDifference;
    private double distance;

    public SwallowPojo(Swallow swallow) {
        id = swallow.getId();
        number = swallow.getNumber();
        date = swallow.getDate();
        longitude = swallow.getLongitude();
        latitude = swallow.getLatitude();
        longitudeDifference = swallow.getLongitudeDifference();
        latitudeDifference = swallow.getLatitudeDifference();
        distance = swallow.getDistance();
    }
}
