package com.example.demo.exception;

public class TestNotFoundException extends RuntimeException {
    public TestNotFoundException(Long id) {
        super("Could not find test " + id);
    }
}
