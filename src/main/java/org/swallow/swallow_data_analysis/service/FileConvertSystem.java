package org.swallow.swallow_data_analysis.service;

import org.swallow.swallow_data_analysis.model.Swallow;

import java.io.File;
import java.util.List;

public interface FileConvertSystem {

    String root_dir = new File("").getAbsolutePath();

    List<Swallow> ReadFile(String fileName);
    void MakeCsvFile(String fileName);
}
