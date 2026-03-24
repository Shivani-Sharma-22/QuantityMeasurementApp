package com.quantity.QuantityApp.service;

import com.quantity.QuantityApp.Core.Quantity;
import com.quantity.QuantityApp.units.IMeasurable;
import org.springframework.stereotype.Service;

@Service
public interface QuantityService {
    Quantity<?> add(Quantity<?> q1, Quantity<?> q2) throws Exception;

    Quantity<?> subtract(Quantity<?> q1, Quantity<?> q2) throws Exception;

    double divide(Quantity<?> q1, Quantity<?> q2) throws Exception;

    boolean checkEquality(Quantity<?> q1, Quantity<?> q2) throws Exception;

    Quantity<?> convert(Quantity<?> quantity, IMeasurable targetUnit);
}
