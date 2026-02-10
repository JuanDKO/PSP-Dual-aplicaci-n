package com.aplicacion.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
// Clase para manejar excepciones de forma global en toda la aplicación
public class GlobalExceptionHandler {

    // Maneja las excepciones de validación (@Valid) cuando fallan los DTOs
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        // Recorre todos los errores de validación y los guarda en un mapa
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            // Obtiene el nombre del campo que falló la validación
            String fieldName = ((FieldError) error).getField();
            // Obtiene el mensaje de error definido en la anotación del DTO
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        // Devuelve un mapa con los errores y el estado HTTP 400 (Bad Request)
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
