package com.assessment.hangman.service;

import com.assessment.hangman.controller.request.CreateGameRequest;
import com.assessment.hangman.entity.Game;
import com.assessment.hangman.entity.enums.StatusGame;
import com.assessment.hangman.entity.factory.WordFactory;
import com.assessment.hangman.repository.GameRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateGameServiceTest {

	@Test
	public void createGame() {
		Game game = new Game(1L, "Eduardo", "DELL", "____", StatusGame.IN_PROGRESS,
				0, null);

		GameRepository gameRepository = mock(GameRepository.class);
		when(gameRepository.save(any(Game.class))).thenReturn(game);
		when(gameRepository.findFirstByPlayerNameAndStatus(any(), any())).thenReturn(Optional.empty());

		SearchGameService searchGameService = new SearchGameService(gameRepository);

		CreateGameService service = new CreateGameService(gameRepository, searchGameService, new WordFactory());

		Game response = service.createGame("Eduardo");

		assertEquals(new Long(1), response.getId());
		assertEquals("Eduardo", response.getPlayerName());
		assertEquals(new Integer(0), response.getIncorrectGuesses());
		assertEquals("DELL", response.getWord());
		assertEquals("____", response.getWordGuessed());
		assertEquals(StatusGame.IN_PROGRESS, response.getStatus());
		assertNull(response.getGuesses());
	}
}