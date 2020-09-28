package com.assessment.hangman.service;

import com.assessment.hangman.entity.Game;
import com.assessment.hangman.entity.Guess;
import com.assessment.hangman.entity.enums.StatusGame;
import com.assessment.hangman.exception.GameNotInProgressException;
import com.assessment.hangman.exception.IdGameNotFoundException;
import com.assessment.hangman.repository.GameRepository;
import com.assessment.hangman.repository.GuessRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SearchGuessService {

    private GuessRepository guessRepository;

    public Optional<Guess> searchGuessByGame_idAndGuess(Game game, String guess){
        Optional<Guess> guessOptional = guessRepository.findByGame_IdAndAndGuess(game.getId(), guess);
        return guessOptional;
    }

}
