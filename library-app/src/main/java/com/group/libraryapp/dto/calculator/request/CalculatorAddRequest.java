package com.group.libraryapp.dto.calculator.request;

import lombok.Getter;

public class CalculatorAddRequest {

    @Getter
    private final int number1;

    @Getter
    private final int number2;

    public CalculatorAddRequest(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;
    }
}
