package ait.currency2.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class QueryDto {
    private String from;
    private String to;
    private double amount;
}
