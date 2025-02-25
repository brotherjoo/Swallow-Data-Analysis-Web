package org.swallow.swallow_data_analysis.service;

import java.io.FileNotFoundException;
import org.springframework.web.multipart.MultipartFile;

@Deprecated
public interface EntityResisterService {

  void registerEntity(MultipartFile file, String title) throws FileNotFoundException;
}
