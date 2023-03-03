package edu.ntnu.idatt2105.callumg.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;

import lombok.NonNull;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Class User.
 * @author Callum G.
 * @version 1.0 3.3.2023
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
public class User {
    
    @Id
    @Column(name = "username", length = 64, nullable = false)
    @NonNull
    private String username;

    @Column(name = "password", nullable = false)
    @NonNull
    private String hashedPassword;

    @Column(name = "salt", nullable = false)
    @NonNull
    private byte[] salt;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="username", referencedColumnName = "username")
    @NonNull
    private List<Calculation> calculations;

    /**
     * Constructor for User.
     * @param username The username of the user.
     * @param hash The hash of the user.
     * @param salt The salt of the user.
     * @throws NullPointerException if the username, hash or salt is null.
     * @throws IllegalArgumentException if the username or hash is empty.
     */
    public User(String username, String hashedPassword, byte[] salt) throws NullPointerException {
        if (username.isBlank()) throw new IllegalArgumentException("The username cannot be empty.");
        if (hashedPassword.isBlank()) throw new IllegalArgumentException("The hashed password cannot be empty.");
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.salt = salt;
        this.calculations = new ArrayList<>();
    }
    
    /**
     * Method addCalculation.
     * Adds a calculation to the list of calculations.
     * @param calculation The calculation to add.
     * @throws NullPointerException if the calculation is null.
     */
    public void addCalculation(@NonNull Calculation calculation) throws NullPointerException {
        calculations.add(calculation);
    }

    /**
     * Method removeCalculation.
     * Removes a calculation from the list of calculations.
     * @param calculation The calculation to remove.
     * @throws NullPointerException if the calculation is null.
     */
    public void removeCalculation(@NonNull Calculation calculation) throws NullPointerException {
        calculations.remove(calculation);
    }
}
