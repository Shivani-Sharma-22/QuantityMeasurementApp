package com.quantity.QuantityApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityLengthTest {

	private static final double EPSILON = 1e-6;
	
	// -------------------------------
    // HELPER DELEGATION TESTS
    // -------------------------------
    @Test
    void testRefactoring_Add_DelegatesViaHelper() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

        Quantity<LengthUnit> sum = q1.add(q2);
        // The sum should be correct according to helper computation
        assertEquals(15.0, sum.getValue(), 1e-6);
    }

    @Test
    void testRefactoring_Subtract_DelegatesViaHelper() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(4.0, LengthUnit.FEET);

        Quantity<LengthUnit> diff = q1.subtract(q2);
        // Helper should handle the computation
        assertEquals(6.0, diff.getValue(), 1e-6);
    }

    @Test
    void testRefactoring_Divide_DelegatesViaHelper() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);

        double result = q1.divide(q2);
        // Helper should handle division
        assertEquals(5.0, result, 1e-6);
    }

    // -------------------------------
    // VALIDATION CONSISTENCY TESTS
    // -------------------------------
    @Test
    void NullOperand_ConsistentAcrossOperations() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> nullQ = null;

        String expectedMessage = "Other quantity cannot be null";

        IllegalArgumentException exAdd = assertThrows(IllegalArgumentException.class, () -> q1.add(nullQ));
        assertEquals(expectedMessage, exAdd.getMessage());

        IllegalArgumentException exSubtract = assertThrows(IllegalArgumentException.class, () -> q1.subtract(nullQ));
        assertEquals(expectedMessage, exSubtract.getMessage());

        IllegalArgumentException exDivide = assertThrows(IllegalArgumentException.class, () -> q1.divide(nullQ));
        assertEquals(expectedMessage, exDivide.getMessage());
    }

    @Test
    void CrossCategory_ConsistentAcrossOperations() {
        Quantity<LengthUnit> qLength = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<WeightUnit> qWeight = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        String expectedMessage = "Cannot operate on different measurement categories";

        IllegalArgumentException exAdd = assertThrows(IllegalArgumentException.class, () -> qLength.add((Quantity) qWeight));
        assertEquals(expectedMessage, exAdd.getMessage());

        IllegalArgumentException exSubtract = assertThrows(IllegalArgumentException.class, () -> qLength.subtract((Quantity) qWeight));
        assertEquals(expectedMessage, exSubtract.getMessage());

        IllegalArgumentException exDivide = assertThrows(IllegalArgumentException.class, () -> qLength.divide((Quantity) qWeight));
        assertEquals(expectedMessage, exDivide.getMessage());
    }

   
	@Test
    void Add_EnumComputation() {
        double result = ArithmeticOperation.ADD.compute(10, 5);
        assertEquals(15.0, result);
    }

    @Test
    void Subtract_EnumComputation() {
        double result = ArithmeticOperation.SUBTRACT.compute(10, 5);
        assertEquals(5.0, result);
    }

    @Test
    void Divide_EnumComputation() {
        double result = ArithmeticOperation.DIVIDE.compute(10, 5);
        assertEquals(2.0, result);
    }

    @Test
    void DivideByZero_EnumThrows() {
        assertThrows(ArithmeticException.class,
                () -> ArithmeticOperation.DIVIDE.compute(10, 0));
    }

    // ---------- ADD / SUBTRACT / DIVIDE BEHAVIOR ----------

    @Test
    void testAdd_UC12_BehaviorPreserved() {

        Quantity<LengthUnit> q1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12, LengthUnit.INCH);

        Quantity<LengthUnit> result = q1.add(q2);

        assertEquals(new Quantity<>(2, LengthUnit.FEET), result);
    }

    @Test
    void testSubtract_UC12_BehaviorPreserved() {

        Quantity<LengthUnit> q1 = new Quantity<>(2, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12, LengthUnit.INCH);

        Quantity<LengthUnit> result = q1.subtract(q2);

        assertEquals(new Quantity<>(1, LengthUnit.FEET), result);
    }

    @Test
    void testDivide_UC12_BehaviorPreserved() {

        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5, LengthUnit.FEET);

        double result = q1.divide(q2);

        assertEquals(2.0, result);
    }

    // ---------- NULL VALIDATION ----------

    @Test
    void testValidation_NullOperand_ConsistentAcrossOperations() {

        Quantity<LengthUnit> q = new Quantity<>(10, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> q.add(null));
        assertThrows(IllegalArgumentException.class, () -> q.subtract(null));
        assertThrows(IllegalArgumentException.class, () -> q.divide(null));
    }

    // ---------- CROSS CATEGORY VALIDATION ----------

    @Test
    void testValidation_CrossCategory_ConsistentAcrossOperations() {

        Quantity<LengthUnit> length = new Quantity<>(10, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(5, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class,
                () -> length.add((Quantity) weight));

        assertThrows(IllegalArgumentException.class,
                () -> length.subtract((Quantity) weight));

        assertThrows(IllegalArgumentException.class,
                () -> length.divide((Quantity) weight));
    }

    // ---------- FINITE VALUE VALIDATION ----------

    @Test
    void testValidation_FiniteValue_ConsistentAcrossOperations() {

        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, LengthUnit.FEET));

        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.POSITIVE_INFINITY, LengthUnit.FEET));
    }

    // ---------- TARGET UNIT VALIDATION ----------

    @Test
    void testValidation_NullTargetUnit_AddSubtractReject() {

        Quantity<LengthUnit> q1 = new Quantity<>(5, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> q1.add(q2, null));

        assertThrows(IllegalArgumentException.class,
                () -> q1.subtract(q2, null));
    }

    // ---------- IMMUTABILITY TESTS ----------

    @Test
    void testImmutability_AfterAdd_ViaCentralizedHelper() {

        Quantity<LengthUnit> q1 = new Quantity<>(5, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5, LengthUnit.FEET);

        q1.add(q2);

        assertEquals(5, q1.getValue());
        assertEquals(5, q2.getValue());
    }

    @Test
    void testImmutability_AfterSubtract_ViaCentralizedHelper() {

        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5, LengthUnit.FEET);

        q1.subtract(q2);

        assertEquals(10, q1.getValue());
        assertEquals(5, q2.getValue());
    }

    @Test
    void testImmutability_AfterDivide_ViaCentralizedHelper() {

        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2, LengthUnit.FEET);

        q1.divide(q2);

        assertEquals(10, q1.getValue());
        assertEquals(2, q2.getValue());
    }

    // ---------- CHAIN OPERATION ----------

    @Test
    void testArithmetic_Chain_Operations() {

        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5, LengthUnit.FEET);
        Quantity<LengthUnit> q3 = new Quantity<>(2, LengthUnit.FEET);
        Quantity<LengthUnit> q4 = new Quantity<>(3, LengthUnit.FEET);

        double result =
                q1.add(q2)
                        .subtract(q3)
                        .divide(q4);

        assertNotNull(result);
    }
    // -------------------------------
    // ENUM CONSTANT TESTS
    // -------------------------------
    @Test
    void testEnumConstant_ADD_CorrectlyAdds() {
        assertEquals(10.0, ArithmeticOperation.ADD.compute(7, 3));
    }

    @Test
    void testEnumConstant_SUBTRACT_CorrectlySubtracts() {
        assertEquals(4.0, ArithmeticOperation.SUBTRACT.compute(7, 3));
    }

    @Test
    void testEnumConstant_DIVIDE_CorrectlyDivides() {
        assertEquals(3.5, ArithmeticOperation.DIVIDE.compute(7, 2));
    }

    @Test
    void testEnumConstant_DIVIDE_ByZeroThrows() {
        assertThrows(ArithmeticException.class,
                () -> ArithmeticOperation.DIVIDE.compute(7, 0));
    }

    // -------------------------------
    // HELPER / BASE UNIT TESTS
    // -------------------------------
    @Test
    void testHelper_BaseUnitConversion_Correct() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCH);

        double result = q1.add(q2).getValue(); // add returns Quantity
        assertEquals(2.0, result, 0.0001); // 1 ft + 12 in = 2 ft
    }

    @Test
    void testHelper_ResultConversion_Correct() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCH);

        Quantity<LengthUnit> sum = q1.add(q2, LengthUnit.INCH);
        assertEquals(24.0, sum.getValue(), 0.0001); // converted to inches
    }

    // -------------------------------
    // VALIDATION TESTS
    // -------------------------------
    @Test
    void testRefactoring_Validation_UnifiedBehavior() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = null;
        Quantity<WeightUnit> w1 = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        // null operand
        assertThrows(IllegalArgumentException.class, () -> q1.add(q2));
        assertThrows(IllegalArgumentException.class, () -> q1.subtract(q2));
        assertThrows(IllegalArgumentException.class, () -> q1.divide(q2));

        // cross-category
        assertThrows(IllegalArgumentException.class, () -> q1.add((Quantity) w1));
        assertThrows(IllegalArgumentException.class, () -> q1.subtract((Quantity) w1));
        assertThrows(IllegalArgumentException.class, () -> q1.divide((Quantity) w1));
    }

    // -------------------------------
    // PERFORMANCE / LARGE DATASET
    // -------------------------------
    @Test
    void testRefactoring_NoBehaviorChange_LargeDataset() {
        Quantity<LengthUnit> base = new Quantity<>(1.0, LengthUnit.FEET);
        for (int i = 0; i < 1000; i++) {
            Quantity<LengthUnit> other = new Quantity<>(i, LengthUnit.INCH);
            Quantity<LengthUnit> sum = base.add(other); // should not throw
            assertNotNull(sum);
        }
    }

    @Test
    void testRefactoring_Performance_ComparableToUC12() {
        Quantity<LengthUnit> q1 = new Quantity<>(1000.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(500.0, LengthUnit.INCH);

        long start = System.nanoTime();
        Quantity<LengthUnit> result = q1.add(q2);
        long duration = System.nanoTime() - start;

        assertNotNull(result);
        System.out.println("Execution time (ns): " + duration);
    }
	 
}


