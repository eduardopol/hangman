package com.assessment.hangman.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GameExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(IdGameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String gameNotFoundHandler(IdGameNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(PlayerNameGameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String gameNotFoundHandler(PlayerNameGameNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(GameNotInProgressException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public String gameNotInProgressHandler(GameNotInProgressException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(GuessAlreadyMadeException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public String guessAlreadyMadeHandler(GuessAlreadyMadeException ex) {
        return ex.getMessage();
    }

}