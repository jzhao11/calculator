/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operators;

import Evaluator.Operand;

/**
 *
 * @author Jianfei Zhao
 * @email jzhao11@mail.sfsu.edu
 */
public class PowerOperator extends BinaryOperator {

    @Override
    public int priority() {
        return 3;
    }

    @Override
    public Operand execute(Operand op1, Operand op2) {
        int base = op1.getValue();
        int exp = op2.getValue();
        int power = 1;
        for (int i = 1; i <= exp; ++i) {
            power *= base;
        }
        Operand opNew = new Operand(power);
        return opNew;
    }
}
