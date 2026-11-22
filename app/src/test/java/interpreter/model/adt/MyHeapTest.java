package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MyHeapTest {
    private IHeap heap;

    @BeforeEach
    void setUp() { heap = new MyHeap(); }

    @Test
    void allocateReturnsOneTwoThree() {
        assertEquals(1, heap.allocate(new IntValue(10)));
        assertEquals(2, heap.allocate(new IntValue(20)));
        assertEquals(3, heap.allocate(new IntValue(30)));
    }

    @Test
    void getReturnsTheStoredValue() throws ADTException {
        int a = heap.allocate(new IntValue(20));
        assertEquals(new IntValue(20), heap.get(a));
    }

    @Test
    void getOfMissingAddressThrows() {
        assertThrows(ADTException.class, () -> heap.get(1));
    }

    @Test
    void updateChangesTheValue() throws ADTException {
        int a = heap.allocate(new IntValue(20));
        heap.update(a, new IntValue(30));
        assertEquals(new IntValue(30), heap.get(a));
    }

    @Test
    void updateOfMissingAddressThrows() {
        assertThrows(ADTException.class, () -> heap.update(1, new IntValue(5)));
    }

    @Test
    void isDefinedOnlyForAllocatedAddresses() {
        heap.allocate(new IntValue(20));
        assertTrue(heap.isDefined(1));
        assertFalse(heap.isDefined(0));
        assertFalse(heap.isDefined(2));
    }

    @Test
    void getContentIsReadOnly() {
        heap.allocate(new IntValue(20));
        assertThrows(UnsupportedOperationException.class,
                () -> heap.getContent().put(2, new IntValue(1)));
    }

    @Test
    void setContentReplacesTheHeapButAddressesAreNotReused() throws ADTException {
        heap.allocate(new IntValue(10));
        heap.allocate(new IntValue(20));
        heap.setContent(Map.of(2, new IntValue(20)));
        assertFalse(heap.isDefined(1));
        assertEquals(new IntValue(20), heap.get(2));
        assertEquals(3, heap.allocate(new IntValue(30)));
    }
}