package com.quantity.QuantityApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.quantity.QuantityApp.Core.Quantity;
import com.quantity.QuantityApp.DTO.QuantityDTO;
import com.quantity.QuantityApp.controller.QuantityMeasurementController;
import com.quantity.QuantityApp.entity.QuantityMeasurementEntity;
import com.quantity.QuantityApp.exception.QuantityMeasurementException;
import com.quantity.QuantityApp.repository.IQuantityMeasurementRepository;
import com.quantity.QuantityApp.repository.QuantityMeasurementCacheRepository;
import com.quantity.QuantityApp.service.IQuantityMeasurementService;
import com.quantity.QuantityApp.service.QuantityMeasurementServiceImpl;
import com.quantity.QuantityApp.units.IMeasurable;
import com.quantity.QuantityApp.units.LengthUnit;
import com.quantity.QuantityApp.units.TemperatureUnit;
import com.quantity.QuantityApp.units.VolumeUnit;
import com.quantity.QuantityApp.units.WeightUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class QuantityMeasurementAppTest {

    private QuantityMeasurementServiceImpl service;
    private FakeRepository repository;

    // Fake repo to test communication
    static class FakeRepository implements IQuantityMeasurementRepository {
        QuantityMeasurementEntity lastSaved;

        List<QuantityMeasurementEntity> data = new ArrayList<>();
        @Override
        public void save(QuantityMeasurementEntity entity) {
            this.lastSaved = entity;
        }

		@Override
		public List<QuantityMeasurementEntity> getAllMeasurements() {
			return data;
		}
    }
    @BeforeEach
    void setup() {
        repository = new FakeRepository();
        service = new QuantityMeasurementServiceImpl(repository);
        controller = new QuantityMeasurementController();
    }
    
    private QuantityMeasurementController controller;
    
    
    static class FakeRepositoryy implements IQuantityMeasurementRepository{

    	QuantityMeasurementEntity saved;
    	List<QuantityMeasurementEntity> info = new ArrayList<>();
		@Override
		public void save(QuantityMeasurementEntity entity) {
			this.saved = entity;
			
		}

		@Override
		public List<QuantityMeasurementEntity> getAllMeasurements() {
		
			return info;
		}
    	
    }
	
	

	@Test
    void testQuantityEntity_SingleOperandConstruction() {
        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("CONVERT", "10 FEET", null, "120 INCH");

        assertEquals("CONVERT", entity.getOperation());
        assertEquals("10 FEET", entity.getOperand1());
        assertEquals("120 INCH", entity.getResult());
    }
	@Test 
	void test() {
		 QuantityMeasurementEntity entity =
	                new QuantityMeasurementEntity("ADD", "10 FEET", "10 FEET", "20 FEET");
		 assertEquals("ADD",entity.getOperation());
		 assertEquals("10 FEET",entity.getOperand1());
		 assertEquals("10 FEET",entity.getOperand2());
		 assertEquals("20 FEET",entity.getResult());
	}
	@Test
	void Entity_ErrorConstruction() {
		 QuantityMeasurementEntity entity =
	                new QuantityMeasurementEntity("ERROR", "Something went worng");
		 
		 assertTrue(entity.hasError());
	}
	@Test 
	void Entity_ToString_Success() {
		QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("ADD", "10 FEET", "10 FEET", "20 FEET");
		assertTrue(entity.toString().contains("20 FEET"));
		
	}
	@Test
	void QuantityEntity_ToString_Error() {
		QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("ADD", "10 FEET", "10 FEET", "20 FEET");
		assertFalse(entity.toString().contains("30 FEET"));
	}
	@Test
	void testService_CompareEquality_SameUnit_Success() {
		QuantityDTO q1 = new QuantityDTO(1.0,"FEET","LengthUnit");
		QuantityDTO q2 = new QuantityDTO(1.0,"FEET","LengthUnit");
		
		boolean result = service.compare(q1, q2);

        assertTrue(result);
        
	}
	@Test
    void testService_CompareEquality_DifferentUnit_Success() {
        QuantityDTO q1 = new QuantityDTO(1, "FEET", "LengthUnit");
        QuantityDTO q2 = new QuantityDTO(12, "INCHES", "LengthUnit");

        boolean result = service.compare(q1, q2);

        assertTrue(result);
    }
	@Test
	void testService_CompareEquality_CrossCategory_Error() {
		QuantityDTO q1 = new QuantityDTO(5.0, "KILOGRAM", "WeighthUnit");
        QuantityDTO q2 = new QuantityDTO(15.0, "GALLON", "VolumeUnit");
        
        assertThrows(QuantityMeasurementException.class,
                () -> service.compare(q1, q2));
	}@Test
    void testService_Convert_Success() {
        QuantityDTO input = new QuantityDTO(1, "FEET", "LengthUnit");

        QuantityDTO result = service.convert(input, "INCHES");

        assertEquals(12, result.getValue());
        assertEquals("INCHES", result.getUnit());
    }
    @Test
    void testService_Add_Success() {
        QuantityDTO q1 = new QuantityDTO(5, "FEET", "LengthUnit");
        QuantityDTO q2 = new QuantityDTO(7, "FEET", "LengthUnit");

        QuantityDTO result = service.add(q1, q2);

        assertEquals(12, result.getValue());
    }
    
    @Test
    void testService_Add_UnsupportedOperation_Error() {
    	 QuantityDTO q1 = new QuantityDTO(5, "FEET", "LengthUnit");
         QuantityDTO q2 = new QuantityDTO(7, "GALLON", "VolumeUnit");
         
         assertThrows(Exception.class,
                 () -> service.add(q1, q2));
         
    }
    
    @Test
    void testService_Subtract_Success() {

        QuantityDTO q1 = new QuantityDTO(10.0, "KILOGRAM", "WeightUnit");
        QuantityDTO q2 = new QuantityDTO(3000.0, "GRAM", "WeightUnit");

        QuantityDTO result = service.subtract(q1, q2);

        // Expected: 10 kg - 3000 g = 7 kg
        assertEquals(7.0, result.getValue(), 0.0001);
        assertEquals("KILOGRAM", result.getUnit());
        assertEquals("WeightUnit", result.getMeasurementType());
    }
    
    @Test
    void testService_Divide_Success() {

        QuantityDTO q1 = new QuantityDTO(6000.0, "KILOGRAM", "WeightUnit");
        QuantityDTO q2 = new QuantityDTO(3000.0, "KILOGRAM", "WeightUnit");

        double result = service.divide(q1, q2);

        // Expected: 6000 / 3000 = 2
        assertEquals(2.0,result);
       
    }
    @Test
    void testService_Divide_ByZero_Error() {
    	QuantityDTO q1 = new QuantityDTO(6000.0,"KILOGRAM","WeigthUnit");
    	QuantityDTO q2 = new QuantityDTO(0.0,"KILOGRAM","WeightUnit");
    	
    	
    	assertThrows(Exception.class,()->service.divide(q1, q2));
    }
    @Test
    void testController_DemonstrateEquality_Success() {
    	QuantityDTO q1 = new QuantityDTO(6000.0, "KILOGRAM", "WeightUnit");
        QuantityDTO q2 = new QuantityDTO(3000.0, "KILOGRAM", "WeightUnit");
        
        boolean result = controller.performComparison(q1, q2);
        
        assertFalse(result);
        
    }
    @Test
    void testController_DemonstrateConversion_Success() {
    	QuantityDTO q1 = new QuantityDTO(6.0, "KILOGRAM", "WeightUnit");
        
    	QuantityDTO convert = controller.performConversion(q1, "GRAM");
    	
    	assertEquals(6000.0,convert.getValue());
    	assertEquals("GRAM",convert.getUnit());
        
    }
    @Test
    void testController_DemonstrateAddition_Success() {
    	QuantityDTO q1 = new QuantityDTO(6000.0, "KILOGRAM", "WeightUnit");
        QuantityDTO q2 = new QuantityDTO(3000.0, "KILOGRAM", "WeightUnit");
        
        QuantityDTO addition = controller.performAddition(q1, q2);
        assertEquals(9000.0,addition.getValue());
        assertEquals("KILOGRAM",addition.getUnit());
    }
    @Test
    void testController_DemonstrateAddition_Error() {
    	QuantityDTO q1 = new QuantityDTO(6000.0, "GALLON", "VolumeUnit");
        QuantityDTO q2 = new QuantityDTO(3000.0, "KILOGRAM", "WeightUnit");
        
        assertThrows(Exception.class,()->{
        	controller.performAddition(q1, q2);
        });
    }
    @Test
    void testLayerSeparation_ServiceIndependence() {
    	QuantityDTO q1 = new QuantityDTO(6000.0, "KILOGRAM", "WeightUnit");
        QuantityDTO q2 = new QuantityDTO(3000.0, "KILOGRAM", "WeightUnit");
        
        QuantityDTO addition = service.add(q1, q2);
        assertEquals(9000.0,addition.getValue());
        assertEquals("KILOGRAM",addition.getUnit());
    }
    @Test
    void testService_AllUnitImplementations() {
    	
    }
	
}



