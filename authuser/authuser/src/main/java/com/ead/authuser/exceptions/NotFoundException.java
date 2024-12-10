package com.ead.authuser.exceptions;

public class NotFoundException extends RuntimeException { //RuntimeExceptions são exceções não verificadas (unchecked exceptions). Isso significa que o compilador não exige que você as trate com blocos try-catch ou declare explicitamente no método com throws. Isso pode facilitar o código, especialmente quando a exceção representa um erro de programação (como um NullPointerException ou um IndexOutOfBoundsException).

    public NotFoundException(String message) {
        super(message);
    }
}