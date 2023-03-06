package edu.ntnu.idatt2105.callumg.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ntnu.idatt2105.callumg.dto.EquationData;
import edu.ntnu.idatt2105.callumg.dto.HistoryData;
import edu.ntnu.idatt2105.callumg.dto.ResultData;
import edu.ntnu.idatt2105.callumg.model.Equation;
import edu.ntnu.idatt2105.callumg.model.User;
import edu.ntnu.idatt2105.callumg.repository.UserRepository;
import edu.ntnu.idatt2105.callumg.service.CalculatorServiceImpl;
import edu.ntnu.idatt2105.callumg.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CalculatorController {

    private static final Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(CalculatorController.class); 

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private Equation dtoToEquation(EquationData dto) {
        Equation equation = new Equation(dto.getEquation());
        equation.setOperands();
        equation.setOperators();
        return equation;
    }

    private ResultData equationToDto(Equation equation) {
        ResultData dto = new ResultData();
        dto.setResult(equation.getResult());
        dto.setEquation(equation.getEquation());
        return dto;
    }

    @PostMapping(value = "/calculate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ResultData> calculate(@RequestBody EquationData dto, HttpServletRequest request) {
        LOGGER.info("Received:" + dto.getEquation());

        Equation equation = dtoToEquation(dto);

        LOGGER.info("Equation operands: " + equation.getOperands() + " operators: " + equation.getOperators());
        
        CalculatorServiceImpl calculatorService = new CalculatorServiceImpl();

        LOGGER.info("Calculating equation: " + equation);

        calculatorService.calculate(equation);

        LOGGER.info("Result: " + equation.getResult());

        User user = userRepository.findByUsername(jwtService.extractUsername(request.getHeader("Authorization").substring(7))).orElseThrow();

        user.addEquation(equation);

        userRepository.save(user);

        ResultData resultDto = equationToDto(equation);
        
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    @GetMapping(value = "/calculate", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<HistoryData> getCalculations(HttpServletRequest request) {
        User user = userRepository.findByUsername(jwtService.extractUsername(request.getHeader("Authorization").substring(7))).orElseThrow();

        List<ResultData> resultDtos = new ArrayList<>();

        HistoryData historyData = new HistoryData();
        
        List<Equation> equations = user.getEquations();

        int pages = equations.size() / 10;

        if (pages >= 1)
            equations = equations.subList(equations.size() - 10, equations.size());

        for (Equation equation : equations) {
            resultDtos.add(equationToDto(equation));
        }

        historyData.setHistory(resultDtos);
        historyData.setPages(pages);

        return new ResponseEntity<>(historyData, HttpStatus.OK);
    }

    @GetMapping(value = "/calculate/{offset}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<ResultData>> getCalculationsPage(@PathVariable(value="offset") int offset, HttpServletRequest request) {
        User user = userRepository.findByUsername(jwtService.extractUsername(request.getHeader("Authorization").substring(7))).orElseThrow();
        
        List<ResultData> resultDtos = new ArrayList<>();

        List<Equation> equations = user.getEquations();

        if (equations.size() < offset * 10) {
            for (Equation equation : equations) {
                resultDtos.add(equationToDto(equation));
            }
        } else {
            if (equations.size() < offset * 10 + 10) {
                for (Equation equation : equations.subList(0, equations.size() - offset * 10)) {
                    resultDtos.add(equationToDto(equation));
                }
            } else {
                for (Equation equation : equations.subList(equations.size() - offset * 10 - 10, equations.size() - offset * 10)) {
                    resultDtos.add(equationToDto(equation));
                }
            }   
        }
        
        return new ResponseEntity<>(resultDtos, HttpStatus.OK);
    }
    
}