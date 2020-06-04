package com.example.demo.exception;

public class AnswerNotFoundException extends RuntimeException {
        public AnswerNotFoundException(Long id) {
            super("Could not find answer " + id);
        }
        public AnswerNotFoundException() {
        super("Could not find answer ");
    }
    }
