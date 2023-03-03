package edu.ntnu.idatt2105.callumg.controller;

import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ntnu.idatt2105.callumg.dto.ExpressionData;
import edu.ntnu.idatt2105.callumg.model.Expression;
import edu.ntnu.idatt2105.callumg.service.CalculatorServiceImpl;

@RestController
@CrossOrigin("*")
public class CalculatorController {

    private static final Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(CalculatorController.class); 

    @PostMapping(value = "/calculate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public ResponseEntity<String> calculate(@RequestBody ExpressionData dto) {
        LOGGER.info("Received:" + dto.getExpression());
        Expression expression = new Expression(dto.getExpression());
        expression.setOperands();
        expression.setOperators();
        CalculatorServiceImpl calculatorService = new CalculatorServiceImpl();
        double result = calculatorService.calculate(expression);
        LOGGER.info("Calculating expression: " + expression);
        LOGGER.info("Result: " + result);
        return new ResponseEntity<>(result + "", HttpStatus.OK);
    }
}