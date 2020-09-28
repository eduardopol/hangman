package com.assessment.hangman.controller.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuessRequest {

    private String guess;

}
