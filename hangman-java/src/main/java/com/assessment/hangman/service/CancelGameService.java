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
public class CancelGameService {

    private GameRepository gameRepository;
    private SearchGameService searchGameService;

    public void cancelGame(Long idGame){
        Game game = searchGameService.searchGameForAction(idGame);

        game.cancelGame();

        gameRepository.save(game);
    }
}
