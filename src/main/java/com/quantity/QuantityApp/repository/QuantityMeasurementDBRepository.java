package com.quantity.QuantityApp.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import com.quantity.QuantityApp.entity.QuantityMeasurementEntity;
import com.quantity.QuantityApp.util.DBConnection;

public class QuantityMeasurementDBRepository implements IQuantityMeasurementRepository {
	private final List<QuantityMeasurementEntity> cache = new ArrayList<>();

	@Override
	public List<QuantityMeasurementEntity> getAllMeasurements() {
	    List<QuantityMeasurementEntity> list = new ArrayList<>();

	    try (Connection conn = DBConnection.getConnection()) {

	        String sql = "SELECT * FROM quantity_measurements";
	        PreparedStatement ps = conn.prepareStatement(sql);

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            String operation = rs.getString("operation");
	            String operand1 = rs.getString("operand1");
	            String operand2 = rs.getString("operand2");
	            String result = rs.getString("result");
	            String error = rs.getString("error");

	            QuantityMeasurementEntity entity;

	            if (error != null) {
	                entity = new QuantityMeasurementEntity(operation, error);
	            } else {
	                entity = new QuantityMeasurementEntity(operation, operand1, operand2, result);
	            }

	            list.add(entity);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}

	@Override
	public void save(QuantityMeasurementEntity entity) {
	    try (Connection conn = DBConnection.getConnection()) {

	        String sql = "INSERT INTO quantity_measurements " +
	                "(operation, operand1, operand2, result, error) VALUES (?, ?, ?, ?, ?)";

	        PreparedStatement ps = conn.prepareStatement(sql);
	        System.out.println(conn);

	        ps.setString(1, entity.getOperation());
	        ps.setString(2, entity.getOperand1());
	        ps.setString(3, entity.getOperand2());
	        ps.setString(4, entity.getResult());
	        ps.setString(5, entity.getError());

	        ps.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
}
