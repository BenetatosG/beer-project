package com.benet.demo.beer;

import com.benet.demo.beer.dto.ErrorResponseDTO;
import com.benet.demo.common.excpetion.BusinessException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BeerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> internal(RuntimeException e) {
        return ResponseEntity.internalServerError().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<String> messages = result.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        ErrorResponseDTO errorMessage = new ErrorResponseDTO("Request data is not valid", messages);
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDTO> businessException(BusinessException e) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(e.getMessage(), null);
        return ResponseEntity.badRequest().body(errorResponseDTO);
    }
}
