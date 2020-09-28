package com.assessment.hangman.service;

import com.assessment.hangman.entity.Game;
import com.assessment.hangman.entity.enums.StatusGame;
import com.assessment.hangman.entity.factory.WordFactory;
import com.assessment.hangman.repository.GameRepository;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CancelGameServiceTest {

	@Test
	public void cancelGame() {
		Game game = new Game(1L, "Eduardo", "DELL", "____", StatusGame.IN_PROGRESS,
				0, null);

		GameRepository gameRepository = mock(GameRepository.class);
		when(gameRepository.findById(any())).thenReturn(Optional.of(game));

		SearchGameService searchGameService = new SearchGameService(gameRepository);

		CancelGameService service = new CancelGameService(gameRepository, searchGameService);

		service.cancelGame(1L);
	}
}