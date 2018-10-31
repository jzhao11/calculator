package Evaluator;

public class Operand {

    private int operandValue;

    public Operand(String token) {
        operandValue = Integer.parseInt(token);
    }

    public Operand(int value) {
        operandValue = value;
    }

    public int getValue() {
        return operandValue;
    }

    public static boolean check(String token) {
        for (int i = 0; i < token.length(); ++i) {
            if (!Character.isDigit(token.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
