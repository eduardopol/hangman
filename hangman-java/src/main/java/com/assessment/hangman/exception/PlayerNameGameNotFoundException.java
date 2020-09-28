package com.assessment.hangman.exception;

public class PlayerNameGameNotFoundException extends RuntimeException {

    public PlayerNameGameNotFoundException(String playerName) {
        super("The player " + playerName + " has no active game.");
    }
}