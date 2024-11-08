package org.swallow.swallow_data_analysis.model.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Entity
@Table(name="swallow")
@Data
@RequiredArgsConstructor
public class Swallow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "index_number")
    private int number;
    private String date;
    private double longitude;
    private double latitude;

    private double longitudeDifference;

    private double latitudeDifference;

    private double distance;

    @ManyToOne
    @JoinColumn(name = "table_number")
    private SwallowTable swallow;
}
