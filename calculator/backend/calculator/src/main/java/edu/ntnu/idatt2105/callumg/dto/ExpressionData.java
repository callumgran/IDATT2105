package edu.ntnu.idatt2105.callumg.dto;

import io.micrometer.common.lang.NonNull;
import lombok.Data;

@Data
public class ExpressionData {

    @NonNull
    private String expression;
}
