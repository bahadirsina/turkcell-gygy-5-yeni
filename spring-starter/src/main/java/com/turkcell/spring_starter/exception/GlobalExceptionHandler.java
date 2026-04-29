package com.turkcell.spring_starter.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Bilindik hata türleri için yönetimi düzgünleştir.
    // RuntimeException çok genel olduğu için, kendimize özel Exception türleri yaratıp onları yakalamak. (BusinessException gibi)
    // ErrorResponse - (title,type,message)
    // ValidationErrorResponse - (arguments: [])

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBusinessException(BusinessException exception) {
        return new ErrorResponse(exception.getTitle(), exception.getType(), exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<String> arguments = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        return new ValidationErrorResponse(
                "Validation Error",
                "MethodArgumentNotValidException",
                "Request validation failed",
                arguments);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleRuntimeException(RuntimeException exception) {
        return new ErrorResponse("Runtime Error", "RuntimeException", exception.getMessage());
    }

    public static class BusinessException extends RuntimeException {
        private final String title;
        private final String type;

        public BusinessException(String title, String type, String message) {
            super(message);
            this.title = title;
            this.type = type;
        }

        public BusinessException(String message) {
            this("Business Error", "BusinessException", message);
        }

        public String getTitle() {
            return title;
        }

        public String getType() {
            return type;
        }
    }

    public static class ErrorResponse {
        private final String title;
        private final String type;
        private final String message;

        public ErrorResponse(String title, String type, String message) {
            this.title = title;
            this.type = type;
            this.message = message;
        }

        public String getTitle() {
            return title;
        }

        public String getType() {
            return type;
        }

        public String getMessage() {
            return message;
        }
    }

    public static class ValidationErrorResponse extends ErrorResponse {
        private final List<String> arguments;

        public ValidationErrorResponse(String title, String type, String message, List<String> arguments) {
            super(title, type, message);
            this.arguments = arguments;
        }

        public List<String> getArguments() {
            return arguments;
        }
    }
}

