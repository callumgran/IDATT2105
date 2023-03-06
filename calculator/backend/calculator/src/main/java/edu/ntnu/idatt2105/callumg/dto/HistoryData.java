package edu.ntnu.idatt2105.callumg.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class HistoryData {
    
    private List<ResultData> history;
    private int pages;
}
