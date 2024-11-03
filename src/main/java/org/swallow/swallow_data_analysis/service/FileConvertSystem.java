package org.swallow.swallow_data_analysis.service;

import org.swallow.swallow_data_analysis.model.Swallow;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface FileConvertSystem {

    String root_dir = new File("").getAbsolutePath();

    List<Swallow> readLine(String fileName) throws FileNotFoundException;
    List<Swallow> preprocessing(List<Swallow> swallowList);
    void MakeCsvFile(String fileName);
}
