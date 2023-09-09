package com.example.udemy.exceptions;

public enum ErrorMessages {
    MISSING_REQUIRED_FIELD("Please check the fields"),
    RECORD_DOES_NOT_EXISTS("Record not exists");
    private String errorMessage;
    ErrorMessages(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
