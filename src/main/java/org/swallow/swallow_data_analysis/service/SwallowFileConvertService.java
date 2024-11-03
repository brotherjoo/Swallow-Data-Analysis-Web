package org.swallow.swallow_data_analysis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.swallow.swallow_data_analysis.formula.Formula;
import org.swallow.swallow_data_analysis.model.Swallow;
import org.swallow.swallow_data_analysis.storage.NotWriteFileAnyThingException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

@Slf4j
@Service
public class SwallowFileConvertService implements FileConvertSystem {

    private final Formula formula;
    private final List<String> header;

    @Autowired
    public SwallowFileConvertService(Formula formula, @Value("${spring.text.header.list}") String[] header) {
        this.formula = formula;
        this.header = Arrays.asList(header);
    }

    @Override
    public List<Swallow> readLine(String fileRoot) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(fileRoot));
        scanner.useDelimiter("\n");
        int[] index = new int[header.size()];
        List<Swallow> swallowList = new LinkedList<>();
        String[] headers = null;
        double prelon = 0, prelat = 0;

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

        while (scanner.hasNext()) {
            try {
                String[] text = scanner.next().split("\t");
                Swallow s = new Swallow();
                double longitude = 0, latitude = 0;

                for (int i : index) {
                    String str = text[i];

                    switch (headers[i].toLowerCase()) {
                        case "first":
                            s.setDate(str);
                            break;

                        case "index":
                            s.setNumber(Integer.parseInt(str));
                            break;

                        case "longitude":
                            longitude = Double.parseDouble(str);
                            s.setLongitude(longitude);
                            s.setLongitudeDifference(Math.abs(longitude - prelon));
                            break;

                        case "latitude":
                            latitude = Double.parseDouble(str);
                            s.setLatitude(latitude);
                            s.setLatitudeDifference(Math.abs(latitude - prelat));
                            break;
                    }
                }
                s.setDistance(formula.formula(longitude, latitude, prelon, prelat));

                prelon = s.getLongitude();
                prelat = s.getLatitude();

                ;

                swallowList.add(s);
            } catch (NumberFormatException e) {
                log.info("nan");
            }
        }

        return swallowList;
    }

    @Override
    public List<Swallow> preprocessing(List<Swallow> swallowList) {
        double sum = 0;

        for (Swallow swallow:swallowList) {
            sum += swallow.getDistance();
        }

        double percent = (sum * 0.95) / swallowList.size();
        log.info("percent: " + percent);

        swallowList.removeIf(swallow -> swallow.getDistance() > percent);

        return  swallowList;
    }

    @Override
    public void MakeCsvFile(String filename) {

    }
}
