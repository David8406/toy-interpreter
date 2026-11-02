package interpreter.model.type;

import interpreter.model.value.StringValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringTypeTest {

    @Test
    void defaultValueIsEmptyString() {
        assertEquals(new StringValue(""), new StringType().defaultValue());
    }

    @Test
    void twoStringTypesAreEqual() {
        assertEquals(new StringType(), new StringType());
    }

    @Test
    void stringTypeIsNotEqualToIntType() {
        assertNotEquals(new StringType(), new IntType());
    }

    @Test
    void toStringIsString() {
        assertEquals("string", new StringType().toString());
    }
}