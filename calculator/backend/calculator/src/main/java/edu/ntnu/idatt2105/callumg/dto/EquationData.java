package edu.ntnu.idatt2105.callumg.dto;

import io.micrometer.common.lang.NonNull;
import lombok.Data;

@Data
public class EquationData {

    @NonNull
    private String username;

    @NonNull
    private String equation;
}
