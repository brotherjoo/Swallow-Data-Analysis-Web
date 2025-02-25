package org.swallow.swallow_data_analysis.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.swallow.swallow_data_analysis.repository.SwallowRepository;
import org.swallow.swallow_data_analysis.repository.SwallowTableRepository;
import org.swallow.swallow_data_analysis.service.SelectionService;

@Service
public class SwallowSelectionService implements SelectionService {

  private final SwallowRepository swallowRepository;
  private final SwallowTableRepository swallowTableRepository;

  public SwallowSelectionService(SwallowRepository swallowRepository,
      SwallowTableRepository swallowTableRepository) {
    this.swallowRepository = swallowRepository;
    this.swallowTableRepository = swallowTableRepository;
  }

  @Override
  public void selectAndSave(final Map<String, List<String>> map) {
    for (Map.Entry<String, List<String>> entry : map.entrySet()) {

    }
  }

  @Override
  public Map<String, List<Double>> selectionNumberMap(final Map<String, List<String>> map) {
    Map<String, List<Double>> newMap = Map.of();

    for (Map.Entry<String, List<String>> entry : map.entrySet()) {
      if (entry.getKey().equals("longitude")) {
        List<Double> list = new ArrayList<>();

        entry.getValue().stream()
            .forEach(x -> list.add(Double.valueOf(x)));

        newMap.put(entry.getKey(), list);
      } else if (entry.getKey().equals("latitude")) {
        List<Double> list = new ArrayList<>();

        entry.getValue().stream()
            .forEach(x -> list.add(Double.valueOf(x)));

        newMap.put(entry.getKey(), list);
      }
    }

    return newMap;
  }
}
