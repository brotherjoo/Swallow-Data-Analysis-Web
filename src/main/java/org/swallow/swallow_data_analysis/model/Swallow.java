package org.swallow.swallow_data_analysis.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@ToString
@Entity
@Data
@RequiredArgsConstructor
public class Swallow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private int number;
    private String date;
    private double longitude;
    private double latitude;

    @Column(name = "longitude_difference")
    private double longitudeDifference;

    @Column(name = "latitude_difference")
    private double latitudeDifference;

    private double distance;

    @ManyToOne
    @JoinColumn(name = "table_number")
    private SwallowTable swallow;
}
