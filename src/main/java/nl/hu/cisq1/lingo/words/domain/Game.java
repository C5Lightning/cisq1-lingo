package nl.hu.cisq1.lingo.words.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.util.ArrayList;

@NoArgsConstructor
@Getter
@Setter
public class Game {
	@Id
	private int id;
	private ArrayList<Round> rounds;

	public Game(int id, ArrayList<Round> rounds) {
		this.id = id;
		this.rounds = rounds;
	}
}
