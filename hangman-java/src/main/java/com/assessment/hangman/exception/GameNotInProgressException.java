package com.assessment.hangman.exception;

public class GameNotInProgressException extends RuntimeException {

    public GameNotInProgressException(Long id) {
        super("The game " + id + " is not in progress. Action not allowed ");
    }
}