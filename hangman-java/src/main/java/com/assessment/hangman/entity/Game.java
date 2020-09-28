package com.assessment.hangman.entity;

import com.assessment.hangman.entity.enums.StatusGame;
import com.assessment.hangman.entity.factory.WordFactory;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_seq")
    @SequenceGenerator(name = "game_seq", sequenceName = "game_seq", allocationSize = 1)
    private Long id;
    private String playerName;
    private String word;
    private String wordGuessed;
    private StatusGame status;
    private Integer incorrectGuesses;
    private static final Integer MAX_GUESSES = 6;
    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
    private List<Guess> guesses;

    public Game(String playerName, WordFactory wordFactory){
        this.playerName = playerName;
        this.word = wordFactory.generateWord();
        this.incorrectGuesses = 0;
        this.wordGuessed = initGameString(this.word.length());
        this.status = StatusGame.IN_PROGRESS;
    }

    private static String initGameString(int word_len) {
        String word;
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i < word_len; i++) {
            stringBuilder.append("_");
        }
        word = stringBuilder.toString();
        return word;
    }

    public void setStatus() {
        if(word.equals(wordGuessed)) {
            this.status = StatusGame.WON;
        } else {
            if(incorrectGuesses < MAX_GUESSES) {
                this.status = StatusGame.IN_PROGRESS;
            }
            if(incorrectGuesses == MAX_GUESSES) {
                this.status = StatusGame.LOST;
            }
        }
    }

    public void makeGuess(Character c) {
        Boolean isCorrect = compareWords(c);
        if (isCorrect) {
            ArrayList<Integer> charInd = new ArrayList<>();

            for (int i = 0; i < this.word.length(); i++) {
                Character wc = this.word.charAt(i);
                if (wc.equals(c)) {
                    charInd.add(i);
                }
            }

            StringBuilder newGuessed = new StringBuilder();
            int numChars = this.wordGuessed.length();
            for (int i = 0; i < numChars; i++) {
                if (charInd.contains(i)) {
                    newGuessed.append(c);
                } else {
                    newGuessed.append(this.wordGuessed.charAt(i));
                }
            }

            this.wordGuessed = newGuessed.toString();
        } else {
            this.incorrectGuess();
        }

        this.setStatus();
    }

    private void incorrectGuess() {
        this.incorrectGuesses++;
    }

    private Boolean compareWords(char ch) {
        CharSequence cs = Character.toString(ch);

        if (this.word.contains(cs)) {
            return true;
        } else {
            return false;
        }
    }

    public void cancelGame() {
        this.status = StatusGame.LOST;
    }

    public boolean isInProgress() { return (this.status == StatusGame.IN_PROGRESS); }

}
