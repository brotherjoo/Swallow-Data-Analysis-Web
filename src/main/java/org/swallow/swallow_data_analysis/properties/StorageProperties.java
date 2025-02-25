package org.swallow.swallow_data_analysis.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Data
@Service
@ConfigurationProperties("storage")
public class StorageProperties {

  private String location = "upload_dir";
  private String csvLocation = "csv_dir";

}
