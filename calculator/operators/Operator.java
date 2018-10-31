package operators;

import java.util.HashMap;

public abstract class Operator {
    // The Operator class should contain an instance of a HashMap
    // This map will use keys as the tokens we're interested in,
    // and values will be instances of the Operators.
    // ALL subclasses of operator MUST be in their own file.
    // Example:
    // Where does this declaration go? What should its access level be?
    // Class or instance variable? Is this the right declaration?
    // HashMap operators = new HashMap();
    // operators.put( "+", new AdditionOperator() );
    // operators.put( "-", new SubtractionOperator() );

    private static HashMap<String, Operator> operators;

    static {
        // initialize the HashMap only once
        operators = new HashMap<>();
        operators.put("+", new AdditionOperator());
        operators.put("-", new SubtractionOperator());
        operators.put("*", new MultiplicationOperator());
        operators.put("/", new DivisionOperator());
        operators.put("^", new PowerOperator());
        operators.put("(", new LeftParenthesisOperator());
        operators.put(")", new RightParenthesisOperator());
        operators.put("!", new FactorialOperator());
    }

    public abstract int priority();

    // this abstract method execute() has been moved to its subclasses: UnaryOperator and BinaryOperator
    // it has been split into two versions in its subclasses
    // BinaryOperator still has two operands in parameter list, whereas UnaryOperator has only one
    // the reason for such design is listed in the documentation
    // public abstract Operand execute(Operand op1, Operand op2);

    public static boolean check(String token) {
        return operators.containsKey(token);
    }

    public static Operator getOperator(String token) {
        return operators.get(token);
    }
}
