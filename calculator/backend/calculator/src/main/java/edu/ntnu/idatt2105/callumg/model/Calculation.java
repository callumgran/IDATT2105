package edu.ntnu.idatt2105.callumg.model;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class Calculation.
 * @author Callum G.
 * @version 1.0 3.3.2023
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "calculation")
public class Calculation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id", unique = true, nullable = false)
    Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "expression_id", referencedColumnName = "expression_id")
    @NonNull
    private Expression expression;

    @Column(name = "result", nullable = false)
    private double result;

    /**
     * Constructor for Calculation
     * @param expression
     * @param result
     * @throws NullPointerException if expression is null
     */
    public Calculation(@NonNull Expression expression, double result) throws NullPointerException {
        this.expression = expression;
        this.result = result;
    }
    
    /**
     * Returns a string representation of the calculation.
     * @return A string representation of the calculation.
     */
    @Override
    public String toString() {
        return "Calculation: " + expression + " = " + result;
    }
}
