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
@Table(name = "swallow")
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

  private Swallow(int number, String date, double longitude, double latitude,
      double longitudeDifference, double latitudeDifference, double distance) {
    this.number = number;
    this.date = date;
    this.longitude = longitude;
    this.latitude = latitude;
    this.longitudeDifference = longitudeDifference;
    this.latitudeDifference = latitudeDifference;
    this.distance = distance;
  }

  public static SwallowBuilder builder() {
    return new SwallowBuilder();
  }

  public static class SwallowBuilder {

    private int number;
    private String date;
    private double longitude;
    private double latitude;
    private double longitudeDifference;
    private double latitudeDifference;
    private double distance;

    public SwallowBuilder number(int number) {
      this.number = number;
      return this;
    }

    public SwallowBuilder date(String date) {
      this.date = date;
      return this;
    }

    public SwallowBuilder longitude(double longitude) {
      this.longitude = longitude;
      return this;
    }

    public SwallowBuilder latitude(double latitude) {
      this.latitude = latitude;
      return this;
    }

    public SwallowBuilder longitudeDifference(double longitudeDifference) {
      this.longitudeDifference = longitudeDifference;
      return this;
    }

    public SwallowBuilder latitudeDifference(double latitudeDifference) {
      this.latitudeDifference = latitudeDifference;
      return this;
    }

    public SwallowBuilder distance(double distance) {
      this.distance = distance;
      return this;
    }

    public Swallow build() {
      return new Swallow(number, date, longitude, latitude, longitudeDifference, latitudeDifference,
          distance);
    }
  }
}
