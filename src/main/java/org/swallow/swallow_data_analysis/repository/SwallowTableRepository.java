package org.swallow.swallow_data_analysis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.swallow.swallow_data_analysis.model.SwallowTable;

import java.util.Optional;

public interface SwallowTableRepository extends JpaRepository<SwallowTable, String> {

    Optional<SwallowTable> findSwallowTableByName(String tableName);
}
