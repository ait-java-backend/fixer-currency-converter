package ait.currency2.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ConvertResponseDto {
    private boolean success;
    private QueryDto query;
    private double result;
}
