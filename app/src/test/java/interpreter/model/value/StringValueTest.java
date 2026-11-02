package interpreter.model.value;

import interpreter.model.type.StringType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringValueTest {

    @Test
    void typeIsString() {
        assertEquals(new StringType(), new StringValue("a").getType());
    }

    @Test
    void equalStringValuesAreEqualAndHaveTheSameHashCode() {
        StringValue a = new StringValue("test.in");
        StringValue b = new StringValue("test.in");
        assertNotSame(a, b);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void differentStringValuesAreNotEqual() {
        assertNotEquals(new StringValue("a"), new StringValue("b"));
    }

    @Test
    void toStringAddsQuotes() {
        assertEquals("\"test.in\"", new StringValue("test.in").toString());
    }
}