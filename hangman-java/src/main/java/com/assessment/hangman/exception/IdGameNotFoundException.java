package com.assessment.hangman.exception;

public class IdGameNotFoundException extends RuntimeException {

    public IdGameNotFoundException(Long id) {
        super("Game id " + id + " not found");
    }
}