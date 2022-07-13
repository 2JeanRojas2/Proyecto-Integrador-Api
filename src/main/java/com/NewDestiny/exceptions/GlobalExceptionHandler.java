package com.NewDestiny.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e) {

        log.error("Global Error");
        log.error(e.getMessage());
        return ResponseEntity.status(500).body("Internal Server Error");
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> notFoundElement(NoSuchElementException e) {
        log.error("Not Found in the database", e);
        return ResponseEntity.status(404).body("Not Found in the database");
    }
}