package org.swallow.swallow_data_analysis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.swallow.swallow_data_analysis.model.Entity.Swallow;
import org.swallow.swallow_data_analysis.model.Entity.SwallowTable;

import java.util.List;

@Repository
public interface SwallowRepository extends JpaRepository<Swallow, Long> {

    List<Swallow> findSwallowBySwallow(SwallowTable swallowTable);
}
