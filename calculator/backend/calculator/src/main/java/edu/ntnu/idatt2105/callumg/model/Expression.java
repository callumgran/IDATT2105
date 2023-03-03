package edu.ntnu.idatt2105.callumg.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "expression")
public class Expression {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expression_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "expression", nullable = false)
    private String expression;

    private List<Character> operators;
    private List<Double> operands;

    public Expression(String expression) {
        this.expression = expression;
        this.operators = new ArrayList<>();
        this.operands = new ArrayList<>();
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void setOperators() {
        this.operators = new ArrayList<>();
        String[] splitExpression = this.expression.split(" ");
        for (int i = 1; i < splitExpression.length; i += 2) {
            operators.add(splitExpression[i].charAt(0));
        }
    }

    public void setOperands() {
        this.operands = new ArrayList<>();
        String[] splitExpression = this.expression.split(" ");
        for (int i = 0; i < splitExpression.length; i += 2) {
            operands.add(Double.parseDouble(splitExpression[i]));
        }
    }

    @Override
    public String toString() {
        return "Expression [operators=" + operators.toString() + ", operands=" + operands.toString() + "]";
    }
}
