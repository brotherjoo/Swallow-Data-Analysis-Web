package org.swallow.swallow_data_analysis.service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public interface FileConvertService {

  Map<String, List<String>> fileConvertToMap(String fileRoot) throws FileNotFoundException;

}
