package org.swallow.swallow_data_analysis.component.preprocessing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import org.springframework.stereotype.Component;

@Component
public class SwallowPreprocessing implements Preprocessing {

  @Override
  public List<Double> preprocessing(final List<Double> list) {
    List<Double> newList = new ArrayList<>(list);
    ListIterator<Double> iterator = newList.listIterator();
    double cutLine = getPercentile(list, 0.90);

    while (iterator.hasNext()) {
      double number = iterator.next();

      if (number < cutLine) {
        newList.add(number);
      }
    }

    return newList;
  }

  protected double getPercentile(final List<Double> list, double p) {
    List<Double> newList = new ArrayList<>(list);
    Collections.sort(newList);
    double number = (int) (newList.size() * p);

    return newList.get((int) Math.ceil(number));
  }
}
