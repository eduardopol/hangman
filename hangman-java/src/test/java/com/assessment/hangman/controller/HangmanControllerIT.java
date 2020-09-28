package com.assessment.hangman.controller;

import com.assessment.hangman.HangmanApplication;
import com.assessment.hangman.controller.request.CreateGameRequest;
import com.assessment.hangman.controller.request.GuessRequest;
import com.assessment.hangman.entity.Game;
import com.assessment.hangman.entity.enums.StatusGame;
import com.assessment.hangman.entity.factory.WordFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HangmanApplication.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HangmanControllerIT {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private WordFactory wordFactory;

	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        when(wordFactory.generateWord()).thenReturn("DELL");
    }

    @Test
    public void test01NewGame() throws Exception {
        MvcResult result = postGame(createGame("Eduardo"));

        Game response = mapper.readValue(result.getResponse().getContentAsString(), Game.class);

        Long id = 1L;

        assertEquals(id, response.getId());
        assertEquals("Eduardo", response.getPlayerName());
    }

    @Test
    public void test02DoNotCreateNewGameSamePlayer() throws Exception {
        MvcResult result = postGame(createGame("Eduardo"));

        Game response = mapper.readValue(result.getResponse().getContentAsString(), Game.class);

        Long id = 1L;

        assertEquals(id, response.getId());
        assertEquals("Eduardo", response.getPlayerName());
    }

    @Test
    public void test03GetGameById() throws Exception {
        MvcResult result = getGame(1L);

        Game response = mapper.readValue(result.getResponse().getContentAsString(), Game.class);

        Long id = 1L;

        assertEquals(id, response.getId());
    }

    @Test
    public void test04GetGameNotFound() throws Exception {
        MvcResult result = getGameNotFound(2L);
    }

    @Test
    public void test05WinGame() throws Exception {
        Long idGame = 1L;

        makeGuess(idGame, createGuess("E"));
        makeGuess(idGame, createGuess("L"));
        MvcResult result = makeGuess(idGame, createGuess("D"));

        Game response = mapper.readValue(result.getResponse().getContentAsString(), Game.class);

        assertEquals(new Long(1), response.getGuesses().get(0).getId());
        assertEquals("E", response.getGuesses().get(0).getGuess());
        assertEquals(StatusGame.WON, response.getStatus());
    }

    @Test
    public void test06NewGame() throws Exception {
        MvcResult result = postGame(createGame("Eduardo"));

        Game response = mapper.readValue(result.getResponse().getContentAsString(), Game.class);

        Long id = 2L;

        assertEquals(id, response.getId());
    }

    @Test
    public void test07LoseGame() throws Exception {
        Long idGame = 2L;

        makeGuess(idGame, createGuess("F"));
        makeGuess(idGame, createGuess("C"));
        makeGuess(idGame, createGuess("A"));
        makeGuess(idGame, createGuess("E"));
        makeGuess(idGame, createGuess("M"));
        makeGuess(idGame, createGuess("J"));
        MvcResult result = makeGuess(idGame, createGuess("Q"));

        Game response = mapper.readValue(result.getResponse().getContentAsString(), Game.class);

        assertEquals(StatusGame.LOST, response.getStatus());
    }

    @Test
    public void test08GuessGameNotFound() throws Exception {
        Long idGame = 3L;

        makeGuessGameNotFound(idGame, createGuess("Q"));
    }

    @Test
    public void test09GuessGameNotInProgress() throws Exception {
        Long idGame = 1L;

        makeGuessWithError(idGame, createGuess("Q"));
    }

    @Test
    public void test10NewGame() throws Exception {
        MvcResult result = postGame(createGame("Eduardo"));

        Game response = mapper.readValue(result.getResponse().getContentAsString(), Game.class);

        Long id = 3L;

        assertEquals(id, response.getId());
    }

    @Test
    public void test11GetInProgressGameByName() throws Exception {
        MvcResult result = getGameByName("Eduardo");

        Game response = mapper.readValue(result.getResponse().getContentAsString(), Game.class);

        Long id = 3L;

        assertEquals(id, response.getId());
    }

    @Test
    public void test12MakeGuess() throws Exception {
        Long idGame = 3L;

        MvcResult result = makeGuess(idGame, createGuess("F"));
        Game response = mapper.readValue(result.getResponse().getContentAsString(), Game.class);

        assertEquals("F", response.getGuesses().get(0).getGuess());

    }

    @Test
    public void test13GuessAlreadyGuessedLetter() throws Exception {
        Long idGame = 3L;

        makeGuessWithError(idGame, createGuess("F"));

    }

    @Test
    public void test14CancelGame() throws Exception {
        Long idGame = 3L;

        cancelGame(idGame);
    }

    @Test
    public void test15DoNotCancelGameNotFound() throws Exception {
        Long idGame = 5L;

        cancelGameNotFound(idGame);
    }

    @Test
    public void test16DoNotCancelPreConditionFailed() throws Exception {
        Long idGame = 3L;

        cancelGameWithError(idGame);
    }

    @Test
    public void test17GetGameByNameNotFound() throws Exception {
        MvcResult result = getGameByNameNotFound("Eduardo");
    }

    private String createGame(String playerName) throws Exception {
        CreateGameRequest request = CreateGameRequest.builder().playerName(playerName).build();
        String jsonPost = mapper.writeValueAsString(request);

        return jsonPost;
    }

    private String createGuess(String guessedLetter) throws Exception {
        GuessRequest request = GuessRequest.builder().guess(guessedLetter).build();
        String jsonPost = mapper.writeValueAsString(request);

        return jsonPost;
    }

    private MvcResult postGame(String jsonPost) throws Exception {
        return mockMvc
                .perform(post("/hangman/games")
                        .header("Content-Type", "application/json")
                        .header("Access-Control-Allow-Origin", "*")
                        .content(jsonPost))
                .andExpect(status().isCreated()).andReturn();
    }

    private MvcResult makeGuess(Long idGame, String jsonPost) throws Exception {
        return mockMvc
                .perform(post("/hangman/games/{0}/guess", idGame)
                        .header("Content-Type", "application/json")
                        .header("Access-Control-Allow-Origin", "*")
                        .content(jsonPost))
                .andExpect(status().isOk()).andReturn();
    }

    private MvcResult makeGuessGameNotFound(Long idGame, String jsonPost) throws Exception {
        return mockMvc
                .perform(post("/hangman/games/{0}/guess", idGame)
                        .header("Content-Type", "application/json")
                        .header("Access-Control-Allow-Origin", "*")
                        .content(jsonPost))
                .andExpect(status().isNotFound()).andReturn();
    }

    private MvcResult makeGuessWithError(Long idGame, String jsonPost) throws Exception {
        return mockMvc
                .perform(post("/hangman/games/{0}/guess", idGame)
                        .header("Content-Type", "application/json")
                        .header("Access-Control-Allow-Origin", "*")
                        .content(jsonPost))
                .andExpect(status().isPreconditionFailed()).andReturn();
    }

    private MvcResult cancelGame(Long idGame) throws Exception {
        return mockMvc
                .perform(post("/hangman/games/{0}/cancel", idGame)
                        .header("Content-Type", "application/json")
                        .header("Access-Control-Allow-Origin", "*"))
                .andExpect(status().isNoContent()).andReturn();
    }

    private MvcResult cancelGameNotFound(Long idGame) throws Exception {
        return mockMvc
                .perform(post("/hangman/games/{0}/cancel", idGame)
                        .header("Content-Type", "application/json")
                        .header("Access-Control-Allow-Origin", "*"))
                .andExpect(status().isNotFound()).andReturn();
    }

    private MvcResult cancelGameWithError(Long idGame) throws Exception {
        return mockMvc
                .perform(post("/hangman/games/{0}/cancel", idGame)
                        .header("Content-Type", "application/json")
                        .header("Access-Control-Allow-Origin", "*"))
                .andExpect(status().isPreconditionFailed()).andReturn();
    }

    private MvcResult getGame(Long idGame) throws Exception {
        return mockMvc
                .perform(get("/hangman/games/{0}", idGame)
                        .header("Content-Type", "application/json")
                        .header("Access-Control-Allow-Origin", "*"))
                .andExpect(status().isOk()).andReturn();
    }

    private MvcResult getGameNotFound(Long idGame) throws Exception {
        return mockMvc
                .perform(get("/hangman/games/{0}", idGame)
                        .header("Content-Type", "application/json")
                        .header("Access-Control-Allow-Origin", "*"))
                .andExpect(status().isNotFound()).andReturn();
    }

    private MvcResult getGameByName(String playerName) throws Exception {
        MockHttpServletRequestBuilder getBuilder = get("/hangman/games").param("playerName", playerName);

        MvcResult result = mockMvc.perform(getBuilder.header("Content-Type", "application/json")
                .header("Access-Control-Allow-Origin", "*")).andExpect(status().isOk()).andReturn();
        return result;
    }

    private MvcResult getGameByNameNotFound(String playerName) throws Exception {
        MockHttpServletRequestBuilder getBuilder = get("/hangman/games").param("playerName", playerName);

        MvcResult result = mockMvc.perform(getBuilder.header("Content-Type", "application/json")
                .header("Access-Control-Allow-Origin", "*")).andExpect(status().isNotFound()).andReturn();
        return result;
    }

}