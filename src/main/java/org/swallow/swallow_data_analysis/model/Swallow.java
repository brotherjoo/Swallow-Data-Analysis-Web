package org.swallow.swallow_data_analysis.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor
public class Swallow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private final int number;
    private final Date date;
    private final double longitude;
    private final double latitude;

    @Column(name = "longitude_difference")
    private final double longitudeDifference;

    @Column(name = "latitude_difference")
    private final double latitudeDifference;

    private final double distance;

    @ManyToOne
    @JoinColumn(name = "table_number")
    private SwallowTable swallow;

    public static Swallow of(
            int number,
            double longitude,
            double latitude,
            double longitudeDifference,
            double latitudeDifference,
            double distance) {
        return new Swallow(number, new Date(), longitude, latitude, longitudeDifference, latitudeDifference, distance);
    }
}
