package com.quantity.QuantityApp.repository;

import com.quantity.QuantityApp.entity.OperationHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationHistoryRepository extends JpaRepository<OperationHistoryEntity, Long> {
}
