package edu.ntnu.idatt2105.callumg.service;

import edu.ntnu.idatt2105.callumg.model.Expression;

public class CalculatorService {
    
    public double calculate(Expression expression) {
        double result = expression.getOperands().get(0);
        for (int i = 1; i < expression.getOperands().size(); i++) {
            result = operate(result, expression.getOperands().get(i), expression.getOperators().get(i - 1));
        }
        return result;
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
