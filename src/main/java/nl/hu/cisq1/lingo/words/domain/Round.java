package nl.hu.cisq1.lingo.words.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@NoArgsConstructor
@Getter
@Setter
public class Round {
	private String word;
	private ArrayList<Guess> guesses;
}
