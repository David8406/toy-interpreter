package interpreter.model.exp;

import interpreter.exceptions.ExpressionException;
import interpreter.exceptions.MyException;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.IHeap;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyHeap;
import interpreter.model.type.IntType;
import interpreter.model.type.RefType;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import interpreter.model.value.RefValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReadHeapExpTest {
    private final IDictionary<String, IValue> symTable = new MyDictionary<>();
    private final IHeap heap = new MyHeap();

    @Test
    void readsTheValueAtTheAddress() throws MyException {
        int a = heap.allocate(new IntValue(20));
        symTable.put("v", new RefValue(a, new IntType()));
        assertEquals(new IntValue(20), new ReadHeapExp(new VarExp("v")).eval(symTable, heap));
    }

    @Test
    void readsThroughTwoReferences() throws MyException {
        heap.allocate(new IntValue(20));
        heap.allocate(new RefValue(1, new IntType()));
        symTable.put("a", new RefValue(2, new RefType(new IntType())));
        IExp e = new ReadHeapExp(new ReadHeapExp(new VarExp("a")));
        assertEquals(new IntValue(20), e.eval(symTable, heap));
    }

    @Test
    void readingANonReferenceThrows() {
        IExp e = new ReadHeapExp(new ValueExp(new IntValue(5)));
        assertThrows(ExpressionException.class, () -> e.eval(symTable, heap));
    }

    @Test
    void readingAnAddressNotInTheHeapThrows() {
        symTable.put("v", new RefValue(0, new IntType()));
        IExp e = new ReadHeapExp(new VarExp("v"));
        var ex = assertThrows(ExpressionException.class, () -> e.eval(symTable, heap));
        assertEquals("rH: address 0 is not in the heap", ex.getMessage());
    }

    @Test
    void toStringShowsRH() {
        assertEquals("rH(v)", new ReadHeapExp(new VarExp("v")).toString());
    }
}