package org.swallow.swallow_data_analysis.model;

import lombok.Data;
import org.swallow.swallow_data_analysis.model.Entity.Swallow;

@Data
public class ResponseSwallow {

    private Long id;
    private int number;
    private String date;
    private double longitude;
    private double latitude;
    private double longitudeDifference;
    private double latitudeDifference;
    private double distance;

    public ResponseSwallow(Swallow swallow) {
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
