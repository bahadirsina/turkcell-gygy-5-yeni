package com.turkcell.spring_starter.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Kütüohane projesine eklenecek.
    //Bilindik hata türleri için yönetimi düzgünleştir.
    // RuntimeException çok genel olduğu için, kendimize özel Exception türleri yaratıp onları yakalamak. (BusinessException gibi)
    // ErrorResponse - (title,type,message)
    // ValidationErrorResponse - (Arguments: [])
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleRuntimeException(RuntimeException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return exception.getMessage();
    }

}
