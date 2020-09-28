package com.assessment.hangman.service;

import com.assessment.hangman.entity.Game;
import com.assessment.hangman.entity.enums.StatusGame;
import com.assessment.hangman.exception.GameNotInProgressException;
import com.assessment.hangman.exception.IdGameNotFoundException;
import com.assessment.hangman.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SearchGameService {

    private GameRepository gameRepository;

    public Optional<Game> searchGameByName(String playerName){
        Optional<Game> game = gameRepository.findFirstByPlayerNameAndStatus(playerName, StatusGame.IN_PROGRESS);
        return game;
    }

    public Optional<Game> searchGameById(Long idGame){
        return gameRepository.findById(idGame);
    }

    public Game searchGameForAction(Long idGame){
        Game game = gameRepository.findById(idGame).orElseThrow(() -> new IdGameNotFoundException(idGame));

        if (!game.isInProgress()) {
            throw new GameNotInProgressException(idGame);
        }

        return game;
    }

}
