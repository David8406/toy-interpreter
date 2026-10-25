package interpreter.model.type;

import interpreter.model.value.IntValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntTypeTest {

    @Test
    void defaultValueIsZero() {
        assertEquals(new IntValue(0), new IntType().defaultValue());
    }

    @Test
    void twoIntTypesAreEqual() {
        assertEquals(new IntType(), new IntType());
    }

    @Test
    void intTypeIsNotEqualToBoolType() {
        assertNotEquals(new IntType(), new BoolType());
    }

    @Test
    void toStringIsInt() {
        assertEquals("int", new IntType().toString());
    }
}