package com.assessment.hangman.service;

import com.assessment.hangman.entity.Game;
import com.assessment.hangman.entity.Guess;
import com.assessment.hangman.exception.GuessAlreadyMadeException;
import com.assessment.hangman.exception.IdGameNotFoundException;
import com.assessment.hangman.repository.GameRepository;
import com.assessment.hangman.repository.GuessRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MakeGuessService {

    private GameRepository gameRepository;
    private GuessRepository guessRepository;
    private SearchGameService searchGameService;
    private SearchGuessService searchGuessService;

    @Transactional
    public Game makeGuess(Long idGame, String guess){
        Game game = searchGameService.searchGameForAction(idGame);

        validateGuess(game, guess);

        game.makeGuess(transformGuess(guess));

        Game gameSaved = gameRepository.save(game);

        Guess guessSave = new Guess(game, guess);
        guessRepository.save(guessSave);

        return gameSaved;
    }

    private void validateGuess(Game game, String guess) {
        Optional<Guess> guessOptional = searchGuessService.searchGuessByGame_idAndGuess(game, guess);
        if (guessOptional.isPresent()) {
            throw new GuessAlreadyMadeException(guess);
        }
    }

    private Character transformGuess(String guess) {
        return guess.toUpperCase().charAt(0);
    }
}
