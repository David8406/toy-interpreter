# toy-interpreter

Interpreter for a small imperative language, written in Java. I built it for my Advanced Programming
Methods course and I am extending it every couple of weeks with new language features.

It runs a program one step at a time and prints the program state after each step: the execution
stack, the variables, the output and the open files. Types are checked at run time, and errors like
an undeclared variable or a division by zero are reported as messages instead of crashing the program.

## Example

```
int a; int b; a = 2 + 3 * 5; b = a + 1; print(b)
```

prints `18`. The first steps of a smaller program, `int v; v = 2; print(v)`:

```
ExeStack: {(int v; (v = 2; print(v)))}   SymTable: {}         Out: {}
ExeStack: {int v | (v = 2; print(v))}    SymTable: {}         Out: {}
ExeStack: {(v = 2; print(v))}            SymTable: {v -> 0}   Out: {}
...
ExeStack: {}                             SymTable: {v -> 2}   Out: {2}
```

## What the language supports

- `int`, `bool` and `string` variables
- arithmetic (`+ - * /`), logic (`and`, `or`) and comparisons (`< <= == != > >=`)
- assignment, `if ... then ... else`, `print`
- reading integers from a text file (`openRFile`, `readFile`, `closeRFile`)

There's no parser yet, so programs are built directly as Java objects (see `view/Examples.java`).

## Structure

The code follows MVC:

- `model`: types, values, expressions, statements, the program state and the generic stack,
  dictionary and list it's made of
- `repository`: stores the program state
- `controller`: executes the program step by step
- `view`: the text menu

A few choices worth mentioning:

- Each statement and expression is a record implementing one interface (`IStmt.execute`,
  `IExp.eval`), so a new statement can be added without touching the controller.
- Operators are enums, and record patterns (`instanceof IntValue(int n)`) check operand types.
- All interpreter errors extend one checked exception, `MyException`. Java exceptions such as
  `IOException` are caught inside the statements and turned into interpreter errors.

## Running it

You need JDK 25. Gradle itself isn't needed, the wrapper is included.

```
./gradlew build                 # compile + run the tests
./gradlew run --console=plain   # start the menu
```

## Tests

JUnit 5 tests cover the data structures, every expression and statement (error cases included)
and complete runs of the example programs. File tests use `@TempDir`, so they don't depend on
files in the repository.
