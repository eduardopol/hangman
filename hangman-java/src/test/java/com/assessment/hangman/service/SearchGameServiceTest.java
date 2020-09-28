package com.assessment.hangman.service;

import com.assessment.hangman.entity.Game;
import com.assessment.hangman.entity.enums.StatusGame;
import com.assessment.hangman.entity.factory.WordFactory;
import com.assessment.hangman.repository.GameRepository;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SearchGameServiceTest {

	@Test
	public void searchByName() {
		Game game = new Game(1L, "Eduardo", "DELL", "____", StatusGame.IN_PROGRESS,
				0, null);

		GameRepository gameRepository = mock(GameRepository.class);
		when(gameRepository.findFirstByPlayerNameAndStatus(any(), any())).thenReturn(Optional.of(game));

		SearchGameService service = new SearchGameService(gameRepository);

		Optional<Game> response = service.searchGameByName("Eduardo");

		assertEquals(new Long(1), response.get().getId());
		assertEquals("Eduardo", response.get().getPlayerName());
		assertEquals(new Integer(0), response.get().getIncorrectGuesses());
		assertEquals("DELL", response.get().getWord());
		assertEquals("____", response.get().getWordGuessed());
		assertEquals(StatusGame.IN_PROGRESS, response.get().getStatus());
		assertNull(response.get().getGuesses());
	}

	@Test
	public void searchById() {
		Game game = new Game(1L, "Eduardo", "DELL", "____", StatusGame.IN_PROGRESS,
				0, null);

		GameRepository gameRepository = mock(GameRepository.class);
		when(gameRepository.findById(any())).thenReturn(Optional.of(game));

		SearchGameService service = new SearchGameService(gameRepository);

		Optional<Game> response = service.searchGameById(1L);

		assertEquals(new Long(1), response.get().getId());
		assertEquals("Eduardo", response.get().getPlayerName());
		assertEquals(new Integer(0), response.get().getIncorrectGuesses());
		assertEquals("DELL", response.get().getWord());
		assertEquals("____", response.get().getWordGuessed());
		assertEquals(StatusGame.IN_PROGRESS, response.get().getStatus());
		assertNull(response.get().getGuesses());
	}

	@Test
	public void searchGameForAction() {
		Game game = new Game(1L, "Eduardo", "DELL", "____", StatusGame.IN_PROGRESS,
				0, null);

		GameRepository gameRepository = mock(GameRepository.class);
		when(gameRepository.findById(any())).thenReturn(Optional.of(game));

		SearchGameService service = new SearchGameService(gameRepository);

		Game response = service.searchGameForAction(1L);

		assertEquals(new Long(1), response.getId());
		assertEquals("Eduardo", response.getPlayerName());
		assertEquals(new Integer(0), response.getIncorrectGuesses());
		assertEquals("DELL", response.getWord());
		assertEquals("____", response.getWordGuessed());
		assertEquals(StatusGame.IN_PROGRESS, response.getStatus());
		assertNull(response.getGuesses());
	}
}