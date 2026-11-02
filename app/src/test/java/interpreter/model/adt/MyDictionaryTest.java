package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MyDictionaryTest {
    private IDictionary<String, Integer> dict;

    @BeforeEach
    void setUp() { dict = new MyDictionary<>(); }

    @Test
    void putThenLookupReturnsValue() throws ADTException {
        dict.put("a", 5);
        assertTrue(dict.isDefined("a"));
        assertEquals(5, dict.lookup("a"));
    }

    @Test
    void missingKeyIsNotDefined() {
        assertFalse(dict.isDefined("x"));
    }

    @Test
    void updateChangesExistingValue() throws ADTException {
        dict.put("a", 5);
        dict.update("a", 7);
        assertEquals(7, dict.lookup("a"));
    }

    @Test
    void lookupOfMissingKeyThrows() {
        assertThrows(ADTException.class, () -> dict.lookup("x"));
    }

    @Test
    void updateOfMissingKeyThrows() {
        assertThrows(ADTException.class, () -> dict.update("x", 1));
    }

    @Test
    void removeDeletesTheKey() throws ADTException {
        dict.put("a", 5);
        dict.remove("a");
        assertFalse(dict.isDefined("a"));
    }

    @Test
    void removeOfMissingKeyThrows() {
        assertThrows(ADTException.class, () -> dict.remove("x"));
    }

    @Test
    void toMapReturnsTheContent() {
        dict.put("a", 5);
        dict.put("b", 7);
        assertEquals(Map.of("a", 5, "b", 7), dict.toMap());
    }

    @Test
    void toMapIsReadOnly() {
        dict.put("a", 5);
        assertThrows(UnsupportedOperationException.class, () -> dict.toMap().put("b", 7));
    }
}