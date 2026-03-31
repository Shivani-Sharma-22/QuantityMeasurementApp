package com.quantity.QuantityApp.repository;


import com.quantity.QuantityApp.entity.QuantityMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuantityMeasurementRepository
        extends JpaRepository<QuantityMeasurementEntity, Long> {
    List<QuantityMeasurementEntity> findByMeasurementTypeIgnoreCase(String measurementType);
}
