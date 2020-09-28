package com.assessment.hangman.service;

import com.assessment.hangman.entity.Game;
import com.assessment.hangman.entity.Guess;
import com.assessment.hangman.entity.enums.StatusGame;
import com.assessment.hangman.entity.factory.WordFactory;
import com.assessment.hangman.repository.GameRepository;
import com.assessment.hangman.repository.GuessRepository;
import org.junit.Test;
import org.mockito.ArgumentMatchers;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MakeGuessServiceTest {

	@Test
	public void makeGuess() {
		Game game = new Game(1L, "Eduardo", "DELL", "____", StatusGame.IN_PROGRESS,
				0, null);
		Guess guess = new Guess(game, "F");
		game.setGuesses(Collections.singletonList(guess));

		GameRepository gameRepository = mock(GameRepository.class);
		when(gameRepository.findById(any())).thenReturn(Optional.of(game));
		when(gameRepository.save(any(Game.class))).thenReturn(game);

		GuessRepository guessRepository = mock(GuessRepository.class);
		when(guessRepository.findByGame_IdAndAndGuess(any(), any())).thenReturn(Optional.empty());
		when(guessRepository.save(any(Guess.class))).thenReturn(guess);

		SearchGameService searchGameService = new SearchGameService(gameRepository);
		SearchGuessService searchGuessService = new SearchGuessService(guessRepository);

		MakeGuessService service = new MakeGuessService(gameRepository, guessRepository, searchGameService, searchGuessService);

		Game response = service.makeGuess(1L, "F");

		assertEquals("Eduardo", response.getPlayerName());
		assertEquals(new Integer(1), response.getIncorrectGuesses());
		assertEquals("DELL", response.getWord());
		assertEquals("____", response.getWordGuessed());
		assertEquals(StatusGame.IN_PROGRESS, response.getStatus());
		assertNotNull(response.getGuesses());
		assertEquals("F", response.getGuesses().get(0).getGuess());
		assertNotNull(response.getGuesses().get(0).getGame());
	}
}