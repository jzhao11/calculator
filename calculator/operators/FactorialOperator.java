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
public class FactorialOperator extends UnaryOperator {

    @Override
    public int priority() {
        return 4;
    }

    @Override
    public Operand execute(Operand op) {
        int n = op.getValue();
        if (n < 0) {
            throw new UnsupportedOperationException("Factorial not Supported for Negative Number");
        } else {
            int fac = 1;
            for (int i = 1; i <= n; ++i) {
                fac *= i;
            }
            Operand newOp = new Operand(fac);
            return newOp;
        }
    }
}
