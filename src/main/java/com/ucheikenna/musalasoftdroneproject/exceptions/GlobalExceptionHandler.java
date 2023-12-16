package com.ucheikenna.musalasoftdroneproject.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(DroneException.class)
    public ResponseEntity<?> handleDroneNotFoundEx(DroneException ex, WebRequest request){
        ApiErrorDetails errorDetail = new ApiErrorDetails(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExcessWeightException.class)
    public ResponseEntity<?> handleExcessWeightsException(ExcessWeightException ex, WebRequest request){
        ApiErrorDetails errorDetail = new ApiErrorDetails(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetail,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MedicationException.class)
    public ResponseEntity<?> handleMedicationNotFoundEx(MedicationException ex, WebRequest request){
        ApiErrorDetails errorDetail = new ApiErrorDetails(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetail,HttpStatus.NOT_FOUND);
    }

}
