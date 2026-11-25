package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.exp.ValueExp;
import interpreter.model.exp.VarExp;
import interpreter.model.type.BoolType;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WhileStmtTest {
    private PrgState state;

    @BeforeEach
    void setUp() throws MyException {
        state = PrgState.of(new NopStmt());
        state.getExeStack().pop();
    }

    @Test
    void falseConditionEndsTheLoop() throws MyException {
        var loop = new WhileStmt(new ValueExp(new BoolValue(false)), new NopStmt());
        loop.execute(state);
        assertTrue(state.getExeStack().isEmpty());
    }

    @Test
    void trueConditionPushesTheBodyAndTheLoopAgain() throws MyException {
        IStmt body = new PrintStmt(new ValueExp(new IntValue(1)));
        var loop = new WhileStmt(new ValueExp(new BoolValue(true)), body);
        loop.execute(state);
        assertEquals(body, state.getExeStack().pop());
        assertEquals(loop, state.getExeStack().pop());
    }

    @Test
    void runsOnce() throws MyException {
        // bool b; b = true; while (b) (print(b); b = false)
        new VarDeclStmt("b", new BoolType()).execute(state);
        new AssignStmt("b", new ValueExp(new BoolValue(true))).execute(state);
        state.getExeStack().push(new WhileStmt(new VarExp("b"),
                new CompStmt(new PrintStmt(new VarExp("b")),
                        new AssignStmt("b", new ValueExp(new BoolValue(false))))));
        while (!state.getExeStack().isEmpty())
            state.getExeStack().pop().execute(state);
        assertEquals(1, state.getOut().size());
    }

    @Test
    void nonBooleanConditionThrows() {
        var loop = new WhileStmt(new ValueExp(new IntValue(1)), new NopStmt());
        assertThrows(StatementException.class, () -> loop.execute(state));
    }
}