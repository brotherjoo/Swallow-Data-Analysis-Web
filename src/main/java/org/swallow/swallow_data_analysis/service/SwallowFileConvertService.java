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
        List<Swallow> swallowList = new LinkedList<Swallow>();
        String[] headers = null;
        double prelon = 0, prelat = 0;

        if (scanner.hasNext()) {
            headers = scanner.next().split("\t");
            int j = 0;

             for (int i = 0; i < headers.length; i++) {
                 if (header.contains(headers[i].toLowerCase())) {
                     log.info("check");
                     index[j] = i;
                     j++;
                 }
             }

             Arrays.stream(index)
                     .forEach(x -> log.info(String.valueOf(x)));

        } else {
            throw new NotWriteFileAnyThingException("Not Write File Anything");
        }

        while (scanner.hasNext()) {
            String[] text = scanner.next().split("\t");
            Arrays.stream(text)
                    .forEach(log::info);
            Swallow s = new Swallow();
            double longitude = 0, latitude = 0;

            for (int i : index) {
                String str = text[i];

                log.info(String.valueOf(i));

                log.info(headers[i].toLowerCase());

                switch (headers[i].toLowerCase()) {
                    case "first":
                        s.setDate(str);
                        break;

                    case "index":
                        log.info(str);
                        s.setNumber(Integer.parseInt(str));
                        break;

                    case "longitude":
                        log.info(str);
                        longitude = Double.parseDouble(str);
                        log.info(String.valueOf(longitude));
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

            swallowList.add(s);
        }

        return swallowList;
    }

//    private Swallow getSwallow(Integer i, String[] text, String[] headers, double prelon, double prelat) {
//        int num = i;
//        String str = text[num];
//        Swallow s = new Swallow();
//        double longitude = 0, latitude = 0;
//
//        Arrays.stream(text)
//                .forEach(log::info);
//
//        switch (headers[num].toLowerCase()) {
//            case "first":
//                s.setDate(str);
//            case "index":
//                log.info(str);
//                s.setNumber(Integer.getInteger(str));
//            case "longitude":
//                log.info(str);
//                longitude = Double.parseDouble(str);
//                log.info(String.valueOf(longitude));
//                s.setLongitude(longitude);
//                s.setLongitudeDifference(Math.abs(longitude - prelon));
//            case "latitude":
//                latitude = Double.parseDouble(str);
//                s.setLatitude(latitude);
//                s.setLatitudeDifference(Math.abs(latitude - prelat));
//        }
//
//        s.setDistance(formula.formula(longitude, latitude, prelon, prelat));
//        log.info(String.valueOf(longitude));
//
//        return s;
//    }

    @Override
    public void MakeCsvFile(String filename) {

    }
}
