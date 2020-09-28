package com.assessment.hangman.controller;

import com.assessment.hangman.controller.request.CreateGameRequest;
import com.assessment.hangman.controller.request.GuessRequest;
import com.assessment.hangman.entity.Game;
import com.assessment.hangman.exception.IdGameNotFoundException;
import com.assessment.hangman.exception.PlayerNameGameNotFoundException;
import com.assessment.hangman.service.CancelGameService;
import com.assessment.hangman.service.CreateGameService;
import com.assessment.hangman.service.MakeGuessService;
import com.assessment.hangman.service.SearchGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HangmanController {

    @Autowired
    private CreateGameService createGameService;

    @Autowired
    private SearchGameService searchGameService;

    @Autowired
    private CancelGameService cancelGameService;

    @Autowired
    private MakeGuessService makeGuessService;

    @PostMapping(path = "/hangman/games", headers = {"content-type=*/*", "Access-Control-Allow-Origin=*"} )
    @CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
    public ResponseEntity<Game> newGame(@RequestBody CreateGameRequest createGameRequest) {
        Game game = createGameService.createGame(createGameRequest.getPlayerName());

        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }

    @PostMapping(path = "/hangman/games/{idGame}/guess", headers = {"content-type=*/*", "Access-Control-Allow-Origin=*"} )
    @CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
    public ResponseEntity<Game> makeGuess(@PathVariable Long idGame,
                                          @RequestBody GuessRequest guessRequest) {
        Game game = makeGuessService.makeGuess(idGame, guessRequest.getGuess());

        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @GetMapping(path = "/hangman/games", headers = {"content-type=*/*", "Access-Control-Allow-Origin=*"} )
    @CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
    public ResponseEntity<Game>  getActiveGameByName(@RequestParam(name = "playerName", required = true) String playerName) {
        return searchGameService.searchGameByName(playerName)
                .map(game -> ResponseEntity.ok().body(game))
                .orElseThrow(() -> new PlayerNameGameNotFoundException(playerName));
    }

    @GetMapping(path = "/hangman/games/{idGame}", headers = {"content-type=*/*", "Access-Control-Allow-Origin=*"} )
    @CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
    public ResponseEntity<Game>  getById(@PathVariable Long idGame) {
        return searchGameService.searchGameById(idGame)
                .map(game -> ResponseEntity.ok().body(game))
                .orElseThrow(() -> new IdGameNotFoundException(idGame));
    }

    @PostMapping(path = "/hangman/games/{idGame}/cancel", headers = {"content-type=*/*", "Access-Control-Allow-Origin=*"} )
    @CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
    public ResponseEntity<Void> getActiveGameByName(@PathVariable Long idGame) {
        cancelGameService.cancelGame(idGame);
        return ResponseEntity.noContent().build();
    }

}
