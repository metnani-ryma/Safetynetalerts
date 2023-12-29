package com.safetynet.alert.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.safetynet.alert.exception.SafetyNetExceptions.DuplicateFirestationException;
import com.safetynet.alert.exception.SafetyNetExceptions.DuplicateMedicalRecordException;
import com.safetynet.alert.exception.SafetyNetExceptions.DuplicatedPersonException;
import com.safetynet.alert.exception.SafetyNetExceptions.FirestationNotFoundException;
import com.safetynet.alert.exception.SafetyNetExceptions.IncompleteRequestException;
import com.safetynet.alert.exception.SafetyNetExceptions.MedicalRecordNotFoundException;
import com.safetynet.alert.exception.SafetyNetExceptions.PersonNotFoundException;

@ControllerAdvice
public class CustomExceptionHandler {

    private static final String MESSAGE = "message";
    private static final String TIMESTAMP = "timestamp";

    @ExceptionHandler(DuplicateFirestationException.class)
    public ResponseEntity<Object> handleDuplicateFirestationException(DuplicateFirestationException dfe) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, dfe.getMessage());
    }

    @ExceptionHandler(FirestationNotFoundException.class)
    public ResponseEntity<Object> handleFirestationNotFoundException(FirestationNotFoundException fnfe) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, fnfe.getMessage());
    }

    @ExceptionHandler(DuplicateMedicalRecordException.class)
    public ResponseEntity<Object> handleDuplicateMedicalRecordException(DuplicateMedicalRecordException dmre) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, dmre.getMessage());
    }

    @ExceptionHandler(MedicalRecordNotFoundException.class)
    public ResponseEntity<Object> handleMedicalRecordNotFountException(MedicalRecordNotFoundException mrnfe) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, mrnfe.getMessage());
    }

    @ExceptionHandler(DuplicatedPersonException.class)
    public ResponseEntity<Object> handleDuplicatePersonException(DuplicatedPersonException dpe) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, dpe.getMessage());
    }

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<Object> handlePersonNotFoundException(PersonNotFoundException pnfe) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, pnfe.getMessage());
    }

    @ExceptionHandler(IncompleteRequestException.class)
    public ResponseEntity<Object> handleIncompleteRequestException(IncompleteRequestException ire) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, ire.getMessage());
    }

    private ResponseEntity<Object> buildResponseEntity(HttpStatus status, String message) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put(MESSAGE, message);
        responseBody.put(TIMESTAMP, LocalDateTime.now());

        return ResponseEntity.status(status).body(responseBody);
    }
}