package interpreter.model.exp;

import interpreter.model.adt.IDictionary;
import interpreter.model.adt.MyDictionary;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValueExpTest {
    private final IDictionary<String, IValue> symTable = new MyDictionary<>();

    @Test
    void intConstantEvaluatesToItself() {
        assertEquals(new IntValue(2), new ValueExp(new IntValue(2)).eval(symTable));
    }

    @Test
    void boolConstantEvaluatesToItself() {
        assertEquals(new BoolValue(true), new ValueExp(new BoolValue(true)).eval(symTable));
    }
}