package com.quantity.QuantityApp.entity;


import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "quantity_measurements")

public class QuantityMeasurementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="quantity_value", nullable=false)
    private double quantityValue;

    @Column(name="unit", nullable=false)
    private String unit;

    @Column(name="measurement_type", nullable=false)
    private String measurementType;

    public QuantityMeasurementEntity() {
    }

    public QuantityMeasurementEntity(double value, String unit, String measurementType) {
        this.quantityValue = value;
        this.unit = unit;
        this.measurementType = measurementType;
    }

    public double getQuantityValue() {
        return quantityValue;
    }

    public void setQuantityValue(double value) {
        this.quantityValue = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", quantityValue, unit);
    }

}