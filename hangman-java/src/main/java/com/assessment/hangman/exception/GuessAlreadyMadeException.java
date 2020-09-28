package com.assessment.hangman.exception;

public class GuessAlreadyMadeException extends RuntimeException {

    public GuessAlreadyMadeException(String guess) {
        super("The letter " + guess + " has already been tried");
    }
}