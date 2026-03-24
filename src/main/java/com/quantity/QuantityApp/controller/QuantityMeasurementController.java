package com.quantity.QuantityApp.controller;

import com.quantity.QuantityApp.DTO.QuantityRequestDTO;
import com.quantity.QuantityApp.DTO.TwoQuantityRequestDTO;
import com.quantity.QuantityApp.service.QuantityMeasurementServiceImpl;
import com.quantity.QuantityApp.units.*;
import com.quantity.QuantityApp.units.IMeasurable;
import com.quantity.QuantityApp.Core.Quantity;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementController {

	private final QuantityMeasurementServiceImpl service;

	public QuantityMeasurementController(QuantityMeasurementServiceImpl service) {
		this.service = service;
	}

	// 🔹 Convert DTO → Domain
	private Quantity<?> toDomain(QuantityRequestDTO dto) {
		IMeasurable unit = getUnit(dto.getMeasurementType(), dto.getUnit());
		return new Quantity<>(dto.getValue(), unit);
	}

	// 🔹 SIMPLE unit logic (no Map)
	private IMeasurable getUnit(String type, String unit) {
		if (unit == null) {
			throw new IllegalArgumentException("Unit cannot be null");
		}
		switch (type) {
			case "LengthUnit":
				return LengthUnit.valueOf(unit.toUpperCase());
			case "WeightUnit":
				return WeightUnit.valueOf(unit.toUpperCase());
			case "VolumeUnit":
				return VolumeUnit.valueOf(unit.toUpperCase());
			case "TemperatureUnit":
				return TemperatureUnit.valueOf(unit.toUpperCase());
			default:
				throw new IllegalArgumentException("Invalid type");
		}
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody TwoQuantityRequestDTO dto) throws Exception {
		Quantity<?> q1 = toDomain(dto.getQuantity1());
		Quantity<?> q2 = toDomain(dto.getQuantity2());


		return ResponseEntity.ok(service.add(q1, q2));
	}

	@PostMapping("/subtract")
	public ResponseEntity<?> subtract(@RequestBody TwoQuantityRequestDTO dto) throws Exception {
		Quantity<?> q1 = toDomain(dto.getQuantity1());
		Quantity<?> q2 = toDomain(dto.getQuantity2());

		return ResponseEntity.ok(service.subtract(q1, q2));
	}

	@PostMapping("/convert")
	public ResponseEntity<?> convert(@RequestBody QuantityRequestDTO dto) {

		Quantity<?> q = toDomain(dto);
		IMeasurable target = getUnit(dto.getMeasurementType(), dto.getTargetUnit());

		return ResponseEntity.ok(service.convert(q, target));
	}

	@PostMapping("/equals")
	public ResponseEntity<?> equals(@RequestBody TwoQuantityRequestDTO dto) throws Exception {
		return ResponseEntity.ok(
				service.checkEquality(
						toDomain(dto.getQuantity1()),
						toDomain(dto.getQuantity2())
				)
		);
	}

	@PostMapping("/divide")
	public ResponseEntity<?> divide(@RequestBody TwoQuantityRequestDTO dto) throws Exception {
		return ResponseEntity.ok(
				service.divide(
						toDomain(dto.getQuantity1()),
						toDomain(dto.getQuantity2())
				)
		);
	}
}