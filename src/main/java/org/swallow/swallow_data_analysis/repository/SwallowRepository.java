package org.swallow.swallow_data_analysis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.swallow.swallow_data_analysis.model.Swallow;

import java.util.Optional;

@Repository
public interface SwallowRepository extends JpaRepository<Swallow, String> {

    Optional<Swallow> findSwallowById(String id);
}
