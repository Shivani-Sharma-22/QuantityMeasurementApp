package com.quantity.QuantityApp.service;

import com.quantity.QuantityApp.DTO.QuantityDTO;
import com.quantity.QuantityApp.entity.QuantityMeasurementEntity;
import com.quantity.QuantityApp.exception.QuantityMeasurementException;
import com.quantity.QuantityApp.repository.IQuantityMeasurementRepository;
import com.quantity.QuantityApp.repository.QuantityMeasurementCacheRepository;

import com.quantity.QuantityApp.Core.Quantity;
import com.quantity.QuantityApp.units.*;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private final IQuantityMeasurementRepository repository;
    

    public QuantityMeasurementServiceImpl() {
    	this.repository = QuantityMeasurementCacheRepository.getInstance();
    }

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    private void validateType(QuantityDTO q1, QuantityDTO q2) {
        if (!q1.getMeasurementType().equalsIgnoreCase(q2.getMeasurementType())) {
            throw new QuantityMeasurementException("Measurement types must match");
        }
    }

    private String format(QuantityDTO q) {
        return q.getValue() + " " + q.getUnit();
    }
    //getUnits
    private IMeasurable getUnit(String type, String unit) {

        switch (type.toLowerCase()) {

            case "lengthunit":
                return LengthUnit.valueOf(unit.toUpperCase());

            case "weightunit":
                return WeightUnit.valueOf(unit.toUpperCase());

            case "volumeunit":
                return VolumeUnit.valueOf(unit.toUpperCase());

            case "temperatureunit":
                return TemperatureUnit.valueOf(unit.toUpperCase());

            default:
                throw new QuantityMeasurementException("Unsupported Measurement Type");
        }
    }
    private QuantityDTO toDTO(Quantity<?> q, String type) {
        return new QuantityDTO(q.getValue(), q.getUnit().getUnitName(), type);
    }
    
    private Quantity<IMeasurable> DTOToQuantity(QuantityDTO quantityDto){
    	IMeasurable u = getUnit(quantityDto.getMeasurementType(),quantityDto.getUnit());
    	Quantity<IMeasurable> quantity = new Quantity<>(quantityDto.getValue(),u);
    	
    	return quantity;
    }
   

    // ---------------- CONVERT ----------------
    @Override
    public QuantityDTO convert(QuantityDTO input, String targetUnit) {

        try {
            IMeasurable from = getUnit(input.getMeasurementType(), input.getUnit());
            IMeasurable to = getUnit(input.getMeasurementType(), targetUnit);

            Quantity<IMeasurable> q = new Quantity<>(input.getValue(), from);
            Quantity<IMeasurable> converted = q.convertTo(to);

            QuantityDTO result = toDTO(converted, input.getMeasurementType());

            repository.save(new QuantityMeasurementEntity(
                    "CONVERT", format(input), null, format(result)
            ));

            return result;

        } catch (Exception e) {
            repository.save(new QuantityMeasurementEntity("CONVERT", e.getMessage()));
            throw new QuantityMeasurementException("Conversion failed", e);
        }
    }

    // ---------------- ADD ----------------
    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {
        try {
            validateType(q1, q2);

            Quantity<IMeasurable> quantity1 = DTOToQuantity(q1);
            Quantity<IMeasurable> quantity2 = DTOToQuantity(q2);
            
            QuantityDTO result = toDTO(quantity1.add(quantity2),q1.getMeasurementType());

            repository.save(new QuantityMeasurementEntity(
                    "ADD", format(q1), format(q2), format(result)
            ));

            return result;

        } catch (Exception e) {
            repository.save(new QuantityMeasurementEntity("ADD", e.getMessage()));
            throw new QuantityMeasurementException("Addition failed", e);
        }
    }

    // ---------------- SUBTRACT ----------------
    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {
        try {
            validateType(q1, q2);

            Quantity<IMeasurable> quantity1 = DTOToQuantity(q1);
            Quantity<IMeasurable> quantity2 = DTOToQuantity(q2);
            
            QuantityDTO result = toDTO(quantity1.subtract(quantity2),q1.getMeasurementType());

            repository.save(new QuantityMeasurementEntity(
                    "SUBTRACT", format(q1), format(q2), format(result)
            ));

            return result;

        } catch (Exception e) {
            repository.save(new QuantityMeasurementEntity("SUBTRACT", e.getMessage()));
            throw new QuantityMeasurementException("Subtraction failed", e);
        }
    }

    // ---------------- COMPARE ----------------
    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {
        try {
            validateType(q1, q2);

            Quantity<IMeasurable> quantity1 = DTOToQuantity(q1);
            Quantity<IMeasurable> quantity2 = DTOToQuantity(q2);
            
            boolean result = quantity1.equals(quantity2);

            return result;

        } catch (Exception e) {
        	throw new QuantityMeasurementException(e.getMessage());
        }
    }


	// ---------------- DIVIDE ----------------
    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2) {
        try {

        	Quantity<IMeasurable> quantity1 = DTOToQuantity(q1);
            Quantity<IMeasurable> quantity2 = DTOToQuantity(q2);
            
            double result = quantity1.divide(quantity2);

            return result;

        } catch (Exception e) {
            repository.save(new QuantityMeasurementEntity("DIVIDE", e.getMessage()));
            throw new QuantityMeasurementException("Division failed", e);
        }
    }
}