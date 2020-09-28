package com.assessment.hangman.repository;

import com.assessment.hangman.entity.Game;
import com.assessment.hangman.entity.enums.StatusGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {

    Optional<Game> findFirstByPlayerNameAndStatus(String playerName, StatusGame status);

}
