package org.swallow.swallow_data_analysis.service.implement;

import java.io.FileNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.swallow.swallow_data_analysis.model.Entity.Swallow;
import org.swallow.swallow_data_analysis.model.Entity.SwallowTable;
import org.swallow.swallow_data_analysis.repository.SwallowRepository;
import org.swallow.swallow_data_analysis.repository.SwallowTableRepository;
import org.swallow.swallow_data_analysis.service.EntityResisterService;
import org.swallow.swallow_data_analysis.service.FileConvertSystem;
import org.swallow.swallow_data_analysis.service.FileInputSystem;

@Service
@Deprecated
public class SwallowRegisterService implements EntityResisterService {

  private final FileInputSystem fileInputSystem;
  private final FileConvertSystem fileConvertSystem;
  private final SwallowRepository swallowRepository;
  private final SwallowTableRepository swallowTableRepository;

  public SwallowRegisterService(FileInputSystem fileInputSystem,
      FileConvertSystem fileConvertSystem, SwallowRepository swallowRepository,
      SwallowTableRepository swallowTableRepository) {
    this.fileConvertSystem = fileConvertSystem;
    this.fileInputSystem = fileInputSystem;
    this.swallowRepository = swallowRepository;
    this.swallowTableRepository = swallowTableRepository;

    fileInputSystem.init();
  }

  @Override
  public void registerEntity(MultipartFile file, String title)
      throws FileNotFoundException {
    String fileRoot = fileInputSystem.store(file);
    List<Swallow> swallowList = fileConvertSystem.readLine(fileRoot);

    swallowList = fileConvertSystem.preprocessing(swallowList);

    SwallowTable swallowTable = new SwallowTable();
    swallowTable.setName(title);
    swallowTableRepository.save(swallowTable);

    swallowList.forEach(x -> x.setSwallow(swallowTable));
    swallowRepository.saveAll(swallowList);
  }
}
