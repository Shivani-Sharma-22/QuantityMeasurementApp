package com.quantity.QuantityApp.repository;

import java.util.List;

import com.quantity.QuantityApp.entity.QuantityMeasurementEntity;

public interface IQuantityMeasurementRepository {
	void save(QuantityMeasurementEntity entity);
	List<QuantityMeasurementEntity> getAllMeasurements();
}
