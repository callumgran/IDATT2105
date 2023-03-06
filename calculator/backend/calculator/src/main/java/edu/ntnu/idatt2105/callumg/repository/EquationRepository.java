package edu.ntnu.idatt2105.callumg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ntnu.idatt2105.callumg.model.Equation;

@Repository
public interface EquationRepository extends JpaRepository<Equation, Long> {}
