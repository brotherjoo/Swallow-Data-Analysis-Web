package org.swallow.swallow_data_analysis.service.implement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.swallow.swallow_data_analysis.service.FileConvertService;

@Service
@Slf4j
public class FileConvertFromTextFileService implements FileConvertService {

  @Override
  public Map<String, List<String>> fileConvertToMap(String fileRoot) throws FileNotFoundException {
    Map<String, List<String>> map = new java.util.HashMap<>(Map.of());
    Scanner scanner = new Scanner(new FileInputStream(fileRoot));
    scanner.useDelimiter("\n");
    String[] headers = null;

    if (scanner.hasNext()) {
      headers = scanner.next().split("\t");

      for (String header : headers) {
        map.put(header.trim().toLowerCase(), new ArrayList<>());

        log.info(header);
      }
    }

    while (scanner.hasNext()) {
      String[] texts = scanner.next().split("\t");
      int i = 0;

      for (String text : texts) {
        map.get(headers[i].trim().toLowerCase()).add(text);
        i++;
      }

    }

    log.info(map.toString());

    return map;
  }
}
