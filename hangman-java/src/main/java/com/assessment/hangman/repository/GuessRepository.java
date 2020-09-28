package com.assessment.hangman.repository;

import com.assessment.hangman.entity.Guess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GuessRepository extends JpaRepository<Guess, Long> {
    Optional<Guess> findByGame_IdAndAndGuess(Long game_id, String guess);
}
