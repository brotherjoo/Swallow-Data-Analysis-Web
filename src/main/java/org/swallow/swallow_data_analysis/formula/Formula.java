package org.swallow.swallow_data_analysis.formula;

public interface Formula {
    double formula(double lon, double lat, double prelon, double prelat);
}
