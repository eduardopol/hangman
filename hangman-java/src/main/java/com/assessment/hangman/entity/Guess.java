package com.assessment.hangman.entity;

import com.assessment.hangman.entity.enums.StatusGame;
import com.assessment.hangman.entity.factory.WordFactory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Guess {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "guess_seq")
    @SequenceGenerator(name = "guess_seq", sequenceName = "guess_seq", allocationSize = 1)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Game game;

    private String guess;

    public Guess(Game game, String guess){
        this.game = game;
        this.guess = guess;
    }

}
