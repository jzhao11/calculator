package Evaluator;

import operators.*;
import java.util.*;

public class Evaluator {

    private Stack<Operand> operandStack;
    private Stack<Operator> operatorStack;

    private StringTokenizer tokenizer;
    private static final String DELIMITERS = "+-*^/!() ";

    public Evaluator() {
        operandStack = new Stack<>();
        operatorStack = new Stack<>();
    }

    public int eval(String expression) {
        String token;

        // The 3rd argument is true to indicate that the delimiters should be used
        // as tokens, too. But, we'll need to remember to filter out spaces.
        this.tokenizer = new StringTokenizer(expression, DELIMITERS, true);

        // initialize operator stack - necessary with operator priority schema
        // the priority of any operator in the operator stack other than
        // the usual mathematical operators - "+-*/" - should be less than the priority
        // of the usual operators
        // TODO Operator is abstract - this will need to be fixed:
        while (this.tokenizer.hasMoreTokens()) {
            // filter out spaces
            if (!(token = this.tokenizer.nextToken()).equals(" ")) {
                // check if token is an operand
                if (Operand.check(token)) {
                    operandStack.push(new Operand(token));
                } else {
                    if (!Operator.check(token)) {
                        System.out.println("*****invalid token******");
                        throw new RuntimeException("*****invalid token******");
                    }

                    // TODO Operator is abstract - these two lines will need to be fixed:
                    // The Operator class should contain an instance of a HashMap,
                    // and values will be instances of the Operators.  See Operator class
                    // skeleton for an example.
                    // Operator newOperator = new Operator( token );
                    Operator newOpr = Operator.getOperator(token);

                    if (operatorStack.empty() || operatorStack.peek().priority() < newOpr.priority() || token.equals("(")) {
                        operatorStack.push(newOpr);
                    } else if (token.equals(")")) {
                        Operator oldOpr;
                        while ((oldOpr = operatorStack.pop()) != Operator.getOperator("(")) {
                            process(oldOpr);
                        }
                    } else {
                        while (!operatorStack.empty() && operatorStack.peek().priority() >= newOpr.priority()) {
                            // note that when we eval the expression 1 - 2 we will
                            // push the 1 then the 2 and then do the subtraction operation
                            // This means that the first number to be popped is the
                            // second operand, not the first operand - see the following code
                            process(operatorStack.pop());
                        }
                        operatorStack.push(newOpr);
                    }
                }
            }
        }

        // Control gets here when we've picked up all of the tokens; you must add
        // code to complete the evaluation - consider how the code given here
        // will evaluate the expression 1+2*3
        // When we have no more tokens to scan, the operand stack will contain 1 2
        // and the operator stack will have + * with 2 and * on the top;
        // In order to complete the evaluation we must empty the stacks (except
        // the init operator on the operator stack); that is, we should keep
        // evaluating the operator stack until it only contains the init operator;
        // Suggestion: create a method that takes an operator as argument and
        // then executes the while loop.
        while (!operatorStack.empty()) {
            process(operatorStack.pop());
        }

        return operandStack.pop().getValue();
    }

    // this is extracted as an individual function from the above eval() method
    private void process(Operator oldOpr) {
        // pop an operand at first
        // if oldOpr is a factorial operator (unary), then compute with the only operand popped up
        // if not factorial, the oldOpr is a binary operator
        // if oldOpr is a binary operator, then pop the second operand and compute with two operands
        // push the computing result to the operandStack
        Operand op2 = operandStack.pop();
        if (oldOpr == Operator.getOperator("!")) {
            operandStack.push(((UnaryOperator) oldOpr).execute(op2));
        } else {
            Operand op1 = operandStack.pop();
            operandStack.push(((BinaryOperator) oldOpr).execute(op1, op2));
        }
    }
}
