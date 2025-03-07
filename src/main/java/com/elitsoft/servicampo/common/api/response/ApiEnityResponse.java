package com.elitsoft.servicampo.common.api.response;

import java.util.List;

public class ApiEnityResponse<T> {

    private T data;
    private String errorCode;
    private String errorMessage;
    private List<ApiError> errors; // For multiple errors

    // Constructors
    public ApiEnityResponse(T data) {
        this.data = data;
        this.errorCode = "";
        this.errorMessage = "";
        this.errors = null;
    }

    public ApiEnityResponse(T data, String errorCode, String errorMessage) {
        this.data = data;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errors = null;
    }


    public ApiEnityResponse(T data, List<ApiError> errors) {
        this.data = data;
        this.errors = errors;
        this.errorCode = null;
        this.errorMessage = null;
    }

    // Getters and setters
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<ApiError> getErrors() {
        return errors;
    }

    public void setErrors(List<ApiError> errors) {
        this.errors = errors;
    }

    public static class ApiError {
        private String errorCode;
        private String errorMessage;

        public ApiError(String errorCode, String errorMessage) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}