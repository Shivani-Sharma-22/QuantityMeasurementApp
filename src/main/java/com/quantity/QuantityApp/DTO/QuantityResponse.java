package com.quantity.QuantityApp.DTO;

public class QuantityResponse {
    private Object result;
    private String message;

    public QuantityResponse(Object result, String message) {
        this.result = result;
        this.message = message;
    }
}
