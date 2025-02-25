package org.swallow.swallow_data_analysis.service.implement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.swallow.swallow_data_analysis.component.formula.Formula;
import org.swallow.swallow_data_analysis.exception.NotWriteFileAnyThingException;
import org.swallow.swallow_data_analysis.model.Entity.Swallow;
import org.swallow.swallow_data_analysis.service.FileConvertSystem;

@Slf4j
@Service
@Deprecated
public class SwallowFileConvertService implements FileConvertSystem {

  private final Formula formula;
  private final List<String> header;

  @Autowired
  public SwallowFileConvertService(Formula formula,
      @Value("${spring.text.header.list}") String[] header) {
    this.formula = formula;
    this.header = Arrays.asList(header);
  }

  @Override
  public List<Swallow> readLine(String fileRoot) throws FileNotFoundException {
    Scanner scanner = new Scanner(new FileInputStream(fileRoot));
    scanner.useDelimiter("\n");
    int[] index = new int[header.size()];
    String[] headers = null;

    if (scanner.hasNext()) {
      headers = scanner.next().split("\t");
      int j = 0;

      for (int i = 0; i < headers.length; i++) {
        if (header.contains(headers[i].toLowerCase())) {
          index[j] = i;
          j++;
        }
      }

    } else {
      throw new NotWriteFileAnyThingException("Not Write File Anything");
    }

    return FileConvert(scanner, index, headers);
  }

  @Override
  public List<Swallow> preprocessing(List<Swallow> swallowList) {
    double sum = 0;

    for (Swallow swallow : swallowList) {
      sum += swallow.getDistance();
    }

    double percent = (sum * 0.95) / swallowList.size();
    log.info("percent: {}", percent);

    swallowList.removeIf(swallow -> swallow.getDistance() > percent);

    return swallowList;
  }

  @Override
  public void MakeCsvFile(String filename) {

  }

  private List<Swallow> FileConvert(Scanner scanner, int[] index, String[] headers) {
    double prelon = 0, prelat = 0;
    List<Swallow> swallowList = new LinkedList<>();
    int nanNumber = 0;

    while (scanner.hasNext()) {
      try {
        String[] text = scanner.next().split("\t");
        Swallow.SwallowBuilder s = Swallow.builder();
        double longitude = 0, latitude = 0;

        for (int i : index) {
          String str = text[i];

          switch (headers[i].toLowerCase()) {

            case "first":
              s = s.date(str);
              break;

            case "index":
              s = s.number(Integer.parseInt(str));
              break;

            case "longitude":
              longitude = Double.parseDouble(str);
              s = s.longitude(longitude)
                  .longitudeDifference(Math.abs(longitude - prelon));
              break;

            case "latitude":
              latitude = Double.parseDouble(str);
              s = s.latitude(latitude)
                  .latitudeDifference(Math.abs(latitude - prelon));
              break;
          }
        }

        Swallow swallow = s.distance(formula.formula(longitude, latitude, prelon, prelat)).build();

        prelon = swallow.getLongitude();
        prelat = swallow.getLatitude();

        swallowList.add(swallow);
      } catch (NumberFormatException e) {
        nanNumber += 1;
      }
    }

    log.info(String.valueOf(nanNumber));

    return swallowList;
  }
}
