package edu.ntnu.idatt2105.callumg.dto;

import edu.ntnu.idatt2105.callumg.model.Equation;
import lombok.Data;
import java.util.List;

@Data
public class UserData {
    
    private String username;

    private String password;
    
    private List<Equation> calculations;
}
