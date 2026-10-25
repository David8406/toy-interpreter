package interpreter.model.value;

import interpreter.model.type.IntType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntValueTest {
    @Test
    void typeIsInt() {
        assertEquals(new IntType(), new IntValue(5).getType());
    }

    @Test
    void valuesWithSameNumberAreEqual() {
        assertEquals(new IntValue(5), new IntValue(5));
    }

    @Test
    void valuesWithDifferentNumbersAreNotEqual() {
        assertNotEquals(new IntValue(5), new IntValue(6));
    }

    @Test
    void toStringIsTheNumber() {
        assertEquals("5", new IntValue(5).toString());
    }
}