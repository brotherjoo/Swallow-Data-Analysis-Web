package org.swallow.swallow_data_analysis.service;

import java.util.List;
import java.util.Map;

public interface SelectionService {

  void selectAndSave(final Map<String, List<String>> map);

  Map<String, List<Double>> selectionNumberMap(final Map<String, List<String>> map);
}
