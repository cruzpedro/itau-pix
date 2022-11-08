package com.itau.pix.handler;

import com.itau.pix.exception.PixException;
import com.itau.pix.exception.PixValidationException;
import com.itau.pix.handler.payload.ApiError;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({PixValidationException.class})
    protected ResponseEntity<Object> handleException(PixValidationException ex) {
        return buildResponseEntity(ApiError.builder()
                .message(ex.getMessage())
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler({PixException.class})
    protected ResponseEntity<Object> handleException(PixException ex) {
        return buildResponseEntity(ApiError.builder()
                .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .timestamp(LocalDateTime.now())
                .build());
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity(apiError, apiError.getStatus());
    }

}
