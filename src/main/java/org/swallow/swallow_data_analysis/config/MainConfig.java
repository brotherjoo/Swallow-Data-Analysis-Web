package org.swallow.swallow_data_analysis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.swallow.swallow_data_analysis.storage.StorageProperties;

@Configuration
public class MainConfig {

    @Bean
    StorageProperties StorageProperties() {
        return new StorageProperties();
    }
}
