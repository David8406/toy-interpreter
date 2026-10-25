package interpreter.model.value;

import interpreter.model.type.BoolType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoolValueTest {
    @Test
    void typeIsBool() {
        assertEquals(new BoolType(), new BoolValue(true).getType());
    }

    @Test
    void valuesWithSameBooleanAreEqual() {
        assertEquals(new BoolValue(true), new BoolValue(true));
    }

    @Test
    void toStringIsTrueOrFalse() {
        assertEquals("true", new BoolValue(true).toString());
        assertEquals("false", new BoolValue(false).toString());
    }

}