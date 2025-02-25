package org.swallow.swallow_data_analysis.service;

import java.io.FileNotFoundException;
import java.util.List;
import org.swallow.swallow_data_analysis.model.Entity.Swallow;

@Deprecated
public interface FileConvertSystem {

  //  String root_dir = new File("").getAbsolutePath();
  List<Swallow> readLine(String fileName) throws FileNotFoundException;

  List<Swallow> preprocessing(List<Swallow> swallowList);

  void MakeCsvFile(String fileName);
}
