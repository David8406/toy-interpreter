package interpreter.model.value;

import interpreter.model.type.IntType;
import interpreter.model.type.RefType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RefValueTest {

    @Test
    void typeIsRefOfTheLocationType() {
        assertEquals(new RefType(new IntType()), new RefValue(1, new IntType()).getType());
    }

    @Test
    void toStringShowsAddressAndType() {
        assertEquals("(1, int)", new RefValue(1, new IntType()).toString());
    }

    @Test
    void sameAddressAndTypeAreEqual() {
        assertEquals(new RefValue(2, new IntType()), new RefValue(2, new IntType()));
    }
}