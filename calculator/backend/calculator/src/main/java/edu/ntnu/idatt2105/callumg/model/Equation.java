package edu.ntnu.idatt2105.callumg.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "equation")
public class Equation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equation_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "equation", nullable = false)
    private String equation;

    @Column(name = "result", nullable = false)
    private double result;
    
    @Transient
    private List<Character> operators;

    @Transient
    private List<Double> operands;

    public Equation(String equation) {
        this.equation = equation;
        this.operators = new ArrayList<>();
        this.operands = new ArrayList<>();
        this.result = 0;
    }

    public void setEquation(String equation) {
        this.equation = equation;
    }

    public void setOperators() {
        this.operators = new ArrayList<>();
        String[] splitEquation = this.equation.split(" ");
        for (int i = 1; i < splitEquation.length; i += 2) {
            operators.add(splitEquation[i].charAt(0));
        }
    }

    public void setOperands() {
        this.operands = new ArrayList<>();
        String[] splitEquation = this.equation.split(" ");
        for (int i = 0; i < splitEquation.length; i += 2) {
            operands.add(Double.parseDouble(splitEquation[i]));
        }
    }

    public void setResult(double result) {
        this.result = result;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "equation [operators=" + operators.toString() + ", operands=" + operands.toString() + "]";
    }
}
