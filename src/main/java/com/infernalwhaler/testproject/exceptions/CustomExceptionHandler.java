package com.infernalwhaler.testproject.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Custom Exception Handler
 *
 * @author sDeseure
 * @project TestProject
 * @date 22/08/2021
 */

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders httpHeaders, final HttpStatus httpStatus, WebRequest request) {
        final CustomErrorResponse errorResponse = new CustomErrorResponse();
        errorResponse.setStatusCode(httpStatus.value());
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setDescription(request.getDescription(false));
        final List<String> errorMessages = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        final Map<String, Object> body = new LinkedHashMap<>();
        body.put("Timestamp", errorResponse.getTimestamp());
        body.put("status", errorResponse.getStatusCode());
        body.put("errors", errorMessages);

        return new ResponseEntity<>(body, BAD_REQUEST);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> accountNotFoundException(final AccountNotFoundException ex, final WebRequest request) {
        final CustomErrorResponse errorResponse = new CustomErrorResponse();
        errorResponse.setStatusCode(NOT_FOUND.value());
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setDescription(request.getDescription(false));

        return new ResponseEntity<>(errorResponse, NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<CustomErrorResponse> badCredentialException(final BadCredentialsException ex, final WebRequest request) {
        final CustomErrorResponse errorResponse = new CustomErrorResponse();
        errorResponse.setStatusCode(BAD_REQUEST.value());
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setDescription(request.getDescription(false));

        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }
}


