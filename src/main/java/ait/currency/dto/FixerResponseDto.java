package ait.currency.dto;

import lombok.Getter;

import java.util.Map;

@Getter
public class FixerResponseDto {
    private boolean success;
    private String base;
    private String date;
    private Map<String, Double> rates;
}
