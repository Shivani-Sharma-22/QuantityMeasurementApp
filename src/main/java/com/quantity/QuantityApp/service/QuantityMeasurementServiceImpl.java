package com.quantity.QuantityApp.service;

import com.quantity.QuantityApp.core.Quantity;
import com.quantity.QuantityApp.entity.OperationHistoryEntity;
import com.quantity.QuantityApp.entity.QuantityMeasurementEntity;
import com.quantity.QuantityApp.repository.OperationHistoryRepository;
import com.quantity.QuantityApp.repository.QuantityMeasurementRepository;
import com.quantity.QuantityApp.units.IMeasurable;
import org.springframework.stereotype.Service;

@Service
public class QuantityMeasurementServiceImpl implements QuantityService{
    private final QuantityMeasurementRepository repo;

    private final OperationHistoryRepository historyRepo;


    public QuantityMeasurementServiceImpl(QuantityMeasurementRepository repo, OperationHistoryRepository historyRepo) {
        this.repo = repo;
        this.historyRepo = historyRepo;
    }

    private void save(Quantity<?> q) {
        repo.save(new QuantityMeasurementEntity(
                q.getValue(),
                q.getUnit().getUnitName(),
                q.getUnit().getClass().getSimpleName()
        ));
    }
    private void saveHistory(String op, Quantity<?> q1, Quantity<?> q2, Object result) {

        OperationHistoryEntity history = new OperationHistoryEntity(
                op,
                q1.toString(),
                q2 != null ? q2.toString() : null,
                result.toString()
        );

        historyRepo.save(history);
    }

    @Override
    public Quantity<?> add(Quantity<?> q1, Quantity<?> q2) throws Exception {
        Quantity<?> result = q1.add(q2);
        save(q1);
        save(q2);
        saveHistory("ADD", q1, q2, result);
        return result;
    }

    @Override
    public Quantity<?> subtract(Quantity<?> q1, Quantity<?> q2) throws Exception {
        Quantity<?> result = q1.subtract(q2);
        save(q1);
        save(q2);
        saveHistory("SUBTRACT",q1,q2,result);
        return result;
    }

    @Override
    public double divide(Quantity<?> q1, Quantity<?> q2) throws Exception {
        double result = q1.divide(q2);
        save(q1);
        save(q2);
        saveHistory("DIVIDE",q1,q2,result);
        return result;
    }

    @Override
    public boolean checkEquality(Quantity<?> q1, Quantity<?> q2) throws Exception {
        boolean result = q1.equals(q2);
        save(q1);
        save(q2);
        saveHistory("COMPARE", q1, q2, result);
        return q1.equals(q2);
    }

    @Override
    public Quantity<?> convert(Quantity<?> quantity, IMeasurable targetUnit) {
        Quantity<?> result = quantity.convertTo(targetUnit);
        save(quantity);
        saveHistory("CONVERT",quantity,null,result);
        return result;
    }

}