package com.assessment.hangman.service;

import com.assessment.hangman.entity.Game;
import com.assessment.hangman.entity.factory.WordFactory;
import com.assessment.hangman.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CreateGameService {

    private GameRepository gameRepository;
    private SearchGameService searchGameService;
    private WordFactory wordFactory;


    public Game createGame(String playerName){
        Optional<Game> optionalGame = searchGameService.searchGameByName(playerName);
        if (optionalGame.isPresent()) {
            return optionalGame.get();
        }

        Game game = new Game(playerName, wordFactory);

        Game gameSaved = gameRepository.save(game);
        return gameSaved;
    }

}
