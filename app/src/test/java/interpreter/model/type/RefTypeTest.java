package interpreter.model.type;

import interpreter.model.value.RefValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RefTypeTest {

    @Test
    void defaultValueIsAddressZero() {
        assertEquals(new RefValue(0, new IntType()), new RefType(new IntType()).defaultValue());
    }

    @Test
    void nestedRefTypesAreEqual() {
        assertEquals(new RefType(new RefType(new IntType())),
                new RefType(new RefType(new IntType())));
    }

    @Test
    void refRefIntDiffersFromRefInt() {
        assertNotEquals(new RefType(new RefType(new IntType())),
                new RefType(new IntType()));
    }

    @Test
    void refIntDiffersFromRefBool() {
        assertNotEquals(new RefType(new IntType()), new RefType(new BoolType()));
    }

    @Test
    void toStringShowsTheInnerType() {
        assertEquals("Ref(Ref(int))", new RefType(new RefType(new IntType())).toString());
    }
}