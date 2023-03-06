package edu.ntnu.idatt2105.callumg.service;

import edu.ntnu.idatt2105.callumg.model.Equation;

public class CalculatorServiceImpl implements CalculatorService {
    
    @Override
    public void calculate(Equation equation) {
        double result = equation.getOperands().get(0);
        for (int i = 1; i < equation.getOperands().size(); i++) {
            result = operate(result, equation.getOperands().get(i), equation.getOperators().get(i - 1));
        }
        equation.setResult(result);
    }

    public double operate(double operand1, double operand2, char operator) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                return operand1 / operand2;
            default:
                return 0;
        }
    }
}
