package com.assessment.hangman.service;

import com.assessment.hangman.entity.Game;
import com.assessment.hangman.entity.Guess;
import com.assessment.hangman.entity.enums.StatusGame;
import com.assessment.hangman.repository.GameRepository;
import com.assessment.hangman.repository.GuessRepository;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SearchGuessServiceTest {

	@Test
	public void searchByName() {
		Game game = new Game(1L, "Eduardo", "DELL", "____", StatusGame.IN_PROGRESS,
				0, null);

		GuessRepository guessRepository = mock(GuessRepository.class);
		when(guessRepository.findByGame_IdAndAndGuess(any(), any())).thenReturn(Optional.empty());

		SearchGuessService service = new SearchGuessService(guessRepository);

		Optional<Guess> response = service.searchGuessByGame_idAndGuess(game, "F");

		assertFalse(response.isPresent());
	}
}