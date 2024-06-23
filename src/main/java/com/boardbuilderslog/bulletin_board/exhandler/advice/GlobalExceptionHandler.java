package com.boardbuilderslog.bulletin_board.exhandler.advice;

import com.boardbuilderslog.bulletin_board.exhandler.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .build();

        BindingResult bindingResult = ex.getBindingResult();
        for (FieldError error : bindingResult.getFieldErrors()) {
            response.addValidation(error.getField(), error.getDefaultMessage());
        }
        for (ObjectError error : bindingResult.getGlobalErrors()) {
            response.addValidation("global", error.getDefaultMessage());
        }
        return response;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code("400")
                .message(ex.getMessage())
                .build();
        return errorResponse;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ErrorResponse  handleHandlerMethodValidationException(HandlerMethodValidationException ex){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code("400")
                .message("Validation error")
                .build();

        List<ParameterValidationResult> allValidationResults = ex.getAllValidationResults();
        for (ParameterValidationResult validationResult : allValidationResults) {
            for(MessageSourceResolvable error : validationResult.getResolvableErrors()){
                String fieldName = validationResult.getMethodParameter().getParameterName();
                errorResponse.addValidation(fieldName, error.getDefaultMessage());
            }
        }
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResponse exHandle(Exception e) {
        log.error("[exceptionHandle] ex", e);
        return ErrorResponse.builder()
                .code("500")
                .message("Internal Server Error")
                .build();
    }
}
