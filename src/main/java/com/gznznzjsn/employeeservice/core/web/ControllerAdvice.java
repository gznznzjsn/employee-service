package com.gznznzjsn.employeeservice.core.web;

import com.gznznzjsn.employeeservice.core.model.exception.ResourceNotFoundException;
import com.gznznzjsn.employeeservice.core.web.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.AccessDeniedException;
import java.util.Map;
import java.util.stream.Collectors;

//@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handleResourceNotFound(
            ResourceNotFoundException e) {
        return ExceptionDto.builder()
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionDto handleAccessDenied() {
        return ExceptionDto.builder()
                .message("Access denied!")
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        Map<String, String> otherInfo = e.getBindingResult()
                .getFieldErrors().stream()
                .collect(Collectors.toMap(
                                (FieldError::getField),
                                (fieldError ->
                                        fieldError.getDefaultMessage() == null ? "No message" : fieldError.getDefaultMessage()
                                )
                        )
                );
        return ExceptionDto.builder()
                .message("One or more of arguments are invalid!")
                .otherInfo(otherInfo)
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDto handleOther() {
        return ExceptionDto.builder()
                .message("Please, try later!")
                .build();
    }

}